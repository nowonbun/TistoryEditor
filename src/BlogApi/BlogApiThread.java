package BlogApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.annotation.processing.FilerException;
import javax.json.JsonObject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import Common.FactoryDao;
import Common.JsonConverter;
import Common.LoggerManager;
import Common.PropertyMap;
import Common.Util;
import Common.IF.ActionExpression;
import Dao.BlogDao;
import Dao.BlogStatisticDao;
import Dao.CategoryDao;
import Dao.OauthInfoDao;
import Dao.PostDao;
import Dao.TistoryUserDao;
import Model.AccessToken;
import Model.Blog;
import Model.BlogStatistic;
import Model.Category;
import Model.OauthInfo;
import Model.Post;
import Model.TistoryUser;

public class BlogApiThread implements Runnable {
	private static BlogApiThread singleton = null;
	private Logger logger = LoggerManager.getLogger(BlogApiThread.class);
	private BlogStatus status = BlogStatus.wait;
	private String message = "";
	private int progress = 0;
	private BlogSyncType type = null;
	private String code;
	private final Map<String, String> parameterBuffer = new HashMap<>();
	private final List<Integer> countBuffer = new ArrayList<>(2);

	private BlogApiThread() {
		logger.info("[Constructor] This class will be allocated. : BlogApiConnectionBuilder");
		setCount(0, 0);
	}

	public static BlogApiThread instance() {
		if (singleton == null) {
			singleton = new BlogApiThread();
		}
		return singleton;
	}

	public static void start(String code, BlogSyncType type) {
		if (BlogApiThread.instance().status == BlogStatus.wait && code != null && type != null) {
			BlogApiThread.instance().code = code;
			BlogApiThread.instance().type = type;
			BlogApiThread.instance().run();
		}
	}

	public static BlogStatus status() {
		return BlogApiThread.instance().status;
	}

	public static String message() {
		return BlogApiThread.instance().message;
	}

	public static int progress() {
		return BlogApiThread.instance().progress;
	}

	@Override
	public void run() {
		if (status == BlogStatus.wait && code != null && type != null) {
			logger.info("[run] This thread will be started.");
			status = BlogStatus.start;
			Executors.newSingleThreadExecutor().execute(() -> {
				try {
					String client_id = PropertyMap.getInstance().getProperty("config", "client_id");
					String client_secret = PropertyMap.getInstance().getProperty("config", "client_secret");
					String redirect_uri = PropertyMap.getInstance().getProperty("config", "redirect_uri");
					parameterBuffer.clear();
					parameterBuffer.put("client_id", client_id);
					parameterBuffer.put("client_secret", client_secret);
					parameterBuffer.put("redirect_uri", redirect_uri);
					parameterBuffer.put("code", code);
					parameterBuffer.put("grant_type", "authorization_code");
					BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/oauth/access_token", parameterBuffer, HttpMethod.GET);
					String access_token = connection.getResponse().replace("access_token=", "").replace("\r", "").replace("\n", "");
					if (Util.StringIsEmptyOrNull(access_token)) {
						status = BlogStatus.error;
						logger.error("[run] It's failed that get the access_token from tistory.");
						this.message = "티스토리 블로그로부터 토큰 취득에 실패했습니다.";
						return;
					}
					logger.info("[run] Token : " + access_token);
					OauthInfo entity = FactoryDao.getDao(OauthInfoDao.class).getOauthInfo(client_id, client_secret, redirect_uri);
					if (entity == null) {
						entity = new OauthInfo();
						entity.setClientId(PropertyMap.getInstance().getProperty("config", "client_id"));
						entity.setClientSecret(PropertyMap.getInstance().getProperty("config", "client_secret"));
						entity.setRedirectUrl(PropertyMap.getInstance().getProperty("config", "redirect_uri"));
						entity.setResponseType("code");
						entity.setGrantType("authorization_code");
						entity.setCreateddate(new Date());
						entity.setIsdeleted(false);
					} else {
						entity.setLastupdateddate(new Date());
					}

					if (entity.getAccessTokens() == null) {
						entity.setAccessTokens(new ArrayList<>());
					} else {
						for (AccessToken token : entity.getAccessTokens()) {
							token.setIsdeleted(true);
						}
					}
					if (type == BlogSyncType.pull) {
						entity.setState("pull");
					} else if (type == BlogSyncType.push) {
						entity.setState("push");
					}
					AccessToken token = new AccessToken();
					entity.getAccessTokens().add(token);
					token.setOauthInfo(entity);
					token.setTokenKey(access_token);
					token.setIsdeleted(false);
					token.setCreateddate(new Date());
					FactoryDao.getDao(OauthInfoDao.class).update(entity);
					logger.info("[run] The oauthInfo table was updated.");
					this.message = "인증 테이블이 업데이트 되었습니다.";
					this.progress = 10;
					status = BlogStatus.token;
					if (type == BlogSyncType.pull) {
						logger.info("[run] The pull procedure will be started.");
						pull(access_token);
					} else if (type == BlogSyncType.push) {
						logger.info("[run] The push procedure will be started.");
						push(access_token);
					}
				} catch (Throwable e) {
					status = BlogStatus.error;
					logger.error("[run]", e);
				} finally {
					status = BlogStatus.wait;
					this.message = "";
					this.progress = 0;
					MenuBuilder.get().init();
					code = null;
					type = null;
				}
			});
		}
	}

	private void defaultJsonStructor(String json, ActionExpression<JsonObject> func) {
		JsonConverter.parseObject(json, obj1 -> {
			if (!JsonConverter.JsonIsKey(obj1, "tistory")) {
				return;
			}
			JsonConverter.parseObject(obj1.get("tistory").toString(), obj2 -> {
				if (!JsonConverter.JsonIsKey(obj2, "item")) {
					return;
				}
				JsonConverter.parseObject(obj2.get("item").toString(), obj3 -> {
					func.run(obj3);
				});
			});
		});
	}

	private void setCount(int index, int count) {
		if (countBuffer.size() < 1) {
			countBuffer.add(0, index);
		} else {
			countBuffer.set(0, index);
		}
		if (countBuffer.size() < 2) {
			countBuffer.add(1, count);
		} else {
			countBuffer.set(1, count);
		}
	}

	private void pull(String token) {
		status = BlogStatus.init;
		FactoryDao.getDao(TistoryUserDao.class).deleteAll();
		this.message = "티스토리 유저 테이블이 업데이트 되었습니다.";
		logger.info("[pull] The tisotryuser table was initialize.");
		FactoryDao.getDao(BlogDao.class).deleteAll();
		this.message = "블로그 테이블이 업데이트 되었습니다.";
		logger.info("[pull] The blog table was initialize.");
		FactoryDao.getDao(BlogStatisticDao.class).deleteAll();
		this.message = "블로그 통계 테이블이 업데이트 되었습니다.";
		logger.info("[pull] The blogstatistic table was initialize.");
		FactoryDao.getDao(CategoryDao.class).deleteAll();
		this.message = "카테고리 테이블이 업데이트 되었습니다.";
		logger.info("[pull] The category table was initialize.");
		FactoryDao.getDao(PostDao.class).deleteAll();
		this.message = "포스트 테이블이 초기화 되었습니다.";
		logger.info("[pull] The post table was initialize.");
		this.progress = 30;
		TistoryUser tuser = getBlog(token);
		if (tuser == null) {
			return;
		}
		this.progress = 40;
		getCategory(tuser, token);
		this.progress = 50;
		getPostList(tuser, token);
		this.progress = 60;
		getPost(tuser, token);
		this.progress = 80;
		FactoryDao.getDao(TistoryUserDao.class).update(tuser);
		logger.info("[pull] The transaction table has been committed.");
		this.progress = 100;
	}

	private void push(String token) {
		status = BlogStatus.push;
		this.message = "데이터가 블로그로 업데이트 됩니다.";
		logger.info("[push] The tisotry contents will be push.");
		List<Post> posts = FactoryDao.getDao(PostDao.class).selectToWaitingPost();
		for (Post post : posts) {
			try {
				if ("-1".equals(post.getPostId())) {
					logger.info("[push] The post data will be written");
					writePost(token, post);
				} else if (post.getIsdeleted()) {
					logger.info("[push] The post data will be deleted");
					deletePost(token, post);
				} else {
					logger.info("[push] The post data will be modified");
					modifyPost(token, post);
				}
			} catch (Throwable e) {
				logger.error("[push]", e);
			}
			post.setIsmodified(false);
			FactoryDao.getDao(PostDao.class).update(post);
			logger.info("[pull] The transaction table has been committed.");
		}
		String isPull = PropertyMap.getInstance().getProperty("config", "auto_pull");
		if ("true".equals(isPull)) {
			logger.info("[pull] The auto pull procedure will be started.");
			pull(token);
		}
	}

	private Post writePost(String token, Post post) throws FileNotFoundException, IOException {
		return pushPost(token, post, true, false);
	}

	private Post modifyPost(String token, Post post) throws FileNotFoundException, IOException {
		return pushPost(token, post, false, false);
	}

	private Post deletePost(String token, Post post) throws FileNotFoundException, IOException {
		return pushPost(token, post, false, true);
	}

	private Post pushPost(String token, Post post, boolean newPost, boolean isDeleted) throws FileNotFoundException, IOException {
		parameterBuffer.clear();
		parameterBuffer.put("access_token", token);
		parameterBuffer.put("output", "json");
		if (!newPost) {
			parameterBuffer.put("postId", post.getPostId());
		}
		parameterBuffer.put("blogName", post.getBlog().getName());
		parameterBuffer.put("title", URLEncoder.encode(post.getTitle(), StandardCharsets.UTF_8.toString()));
		File file = new File(post.getContentsPath());
		if (!file.exists()) {
			throw new FilerException(post.getContentsPath());
		}
		byte[] contents = new byte[(int) file.length()];
		try (FileInputStream stream = new FileInputStream(post.getContentsPath())) {
			stream.read(contents, 0, contents.length);
		}
		parameterBuffer.put("content", URLEncoder.encode(new String(contents, StandardCharsets.UTF_8.toString()), StandardCharsets.UTF_8.toString()));
		if (!newPost && isDeleted) {
			parameterBuffer.put("visibility", "0");
		} else {
			parameterBuffer.put("visibility", "3");
		}
		parameterBuffer.put("category", post.getCategoryId());
		try {
			if (!Util.StringIsEmptyOrNull(post.getTags())) {
				Object[] tags = JsonConverter.parseObject(post.getTags(), (x) -> {
					return x.getJsonArray("tag").toArray();
				});
				StringBuffer sb = new StringBuffer();
				for (Object tag : tags) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(tag.toString().replace("\"", ""));
				}
				parameterBuffer.put("tag", URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8.toString()));
			}
		} catch (RuntimeException e) {
			logger.error("[pushPost]", e);
			logger.error("[postPost] tag data : " + post.getTags());
		}
		String url;
		if (newPost) {
			url = "https://www.tistory.com/apis/post/write";
		} else {
			url = "https://www.tistory.com/apis/post/modify";
		}

		BlogApiConnection connection = BlogApiConnectionBuilder.instance().build(url, parameterBuffer, HttpMethod.POST);
		JsonConverter.parseObject(connection.getResponse(), obj1 -> {
			if (!JsonConverter.JsonIsKey(obj1, "tistory")) {
				return;
			}
			JsonConverter.parseObject(obj1.get("tistory").toString(), obj2 -> {
				if (JsonConverter.JsonIsKey(obj2, "postId")) {
					post.setPostId(obj2.getString("postId"));
				}
				if (JsonConverter.JsonIsKey(obj2, "url")) {
					post.setPostUrl(obj2.getString("url"));
				}
			});
		});
		return post;
	}

	private TistoryUser getBlog(String token) {
		this.status = BlogStatus.blog;
		final List<TistoryUser> memBuffer = new ArrayList<>(1);
		parameterBuffer.clear();
		parameterBuffer.put("access_token", token);
		parameterBuffer.put("output", "json");
		BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/blog/info", parameterBuffer, HttpMethod.GET);
		if (connection.getResponse() == null) {
			this.status = BlogStatus.error;
			this.message = "Http 커넥션으로부터 Blog 데이터를 취득하지 못했습니다.";
			logger.error("[getBlog] It's failed that get the blog info from tistory.");
			return null;
		}
		defaultJsonStructor(connection.getResponse(), obj1 -> {
			String id = JsonConverter.JsonString(obj1, "id");
			String userId = JsonConverter.JsonString(obj1, "userId");
			if (id == null || userId == null) {
				return;
			}
			if (!JsonConverter.JsonIsKey(obj1, "blogs")) {
				return;
			}
			TistoryUser tuser = FactoryDao.getDao(TistoryUserDao.class).getTistoryUser(id, userId);
			if (tuser == null) {
				tuser = new TistoryUser();
				tuser.setId(id);
				tuser.setUserid(userId);
			}
			if (tuser.getBlogs() == null) {
				tuser.setBlogs(new ArrayList<>());
			}

			final TistoryUser tUserMem = tuser;
			JsonConverter.parseArray(obj1.get("blogs").toString(), obj2 -> {
				setCount(1, obj2.size());
				obj2.forEach(obj3 -> {
					this.message = "Blog 취득 진행률 " + countBuffer.get(0) + "/" + countBuffer.get(1);
					logger.info("[getBlog] The blog progress " + countBuffer.get(0) + "/" + countBuffer.get(1));
					setCount(countBuffer.get(0) + 1, countBuffer.get(1));
					JsonConverter.parseObject(obj3.toString(), obj4 -> {
						if (JsonConverter.JsonStringIsEmptyOrNull(obj4, "name")) {
							return;
						}
						Blog blog = null;
						for (Blog iter : tUserMem.getBlogs()) {
							if (Util.StringEquals(iter.getName(), JsonConverter.JsonString(obj4, "name"))) {
								blog = iter;
								break;
							}
						}
						if (blog == null) {
							blog = new Blog();
							blog.setCreateddate(new Date());
							blog.setName(JsonConverter.JsonString(obj4, "name"));
							tUserMem.addBlog(blog);
							blog.setTistoryUser(tUserMem);
						} else {
							blog.setLastupdateddate(new Date());
						}
						blog.setIsdeleted(false);
						blog.setUrl(JsonConverter.JsonString(obj4, "url"));
						blog.setSecondaryUrl(JsonConverter.JsonString(obj4, "secondaryUrl"));
						blog.setNickname(JsonConverter.JsonString(obj4, "nickname"));
						blog.setTitle(JsonConverter.JsonString(obj4, "title"));
						blog.setDescription(JsonConverter.JsonString(obj4, "description"));
						blog.setDefault_(JsonConverter.JsonString(obj4, "default"));
						blog.setBlogIconUrl(JsonConverter.JsonString(obj4, "blogIconUrl"));
						blog.setFaviconUrl(JsonConverter.JsonString(obj4, "faviconUrl"));
						blog.setProfileThumbnailImageUrl(JsonConverter.JsonString(obj4, "profileThumbnailImageUrl"));
						blog.setProfileImageUrl(JsonConverter.JsonString(obj4, "profileImageUrl"));
						blog.setRole(JsonConverter.JsonString(obj4, "role"));
						blog.setBlogid(JsonConverter.JsonString(obj4, "blogId"));
						if (!JsonConverter.JsonIsKey(obj4, "statistics")) {
							return;
						}
						final Blog blogMem = blog;
						JsonConverter.parseObject(obj4.get("statistics").toString(), obj5 -> {
							if (blogMem.getBlogStatistic() == null) {
								blogMem.setBlogStatistic(new BlogStatistic());
								blogMem.getBlogStatistic().setBlog(blogMem);
								blogMem.getBlogStatistic().setCreateddate(new Date());
							} else {
								blogMem.getBlogStatistic().setLastupdateddate(new Date());
							}
							blogMem.getBlogStatistic().setIsdeleted(false);
							blogMem.getBlogStatistic().setPost(Integer.parseInt(JsonConverter.JsonString(obj5, "post")));
							blogMem.getBlogStatistic().setComment(Integer.parseInt(JsonConverter.JsonString(obj5, "comment")));
							blogMem.getBlogStatistic().setTrackback(Integer.parseInt(JsonConverter.JsonString(obj5, "trackback")));
							blogMem.getBlogStatistic().setGuestbook(Integer.parseInt(JsonConverter.JsonString(obj5, "guestbook")));
						});
					});
				});
			});
			tuser.setCreateddate(new Date());
			tuser.setIsdeleted(false);
			memBuffer.add(tuser);
		});

		return memBuffer.remove(0);
	}

	private TistoryUser getCategory(TistoryUser tuser, String token) {
		this.status = BlogStatus.category;
		for (Blog blog : tuser.getBlogs()) {
			parameterBuffer.clear();
			parameterBuffer.put("access_token", token);
			parameterBuffer.put("output", "json");
			parameterBuffer.put("blogName", blog.getName());
			BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/category/list", parameterBuffer, HttpMethod.GET);
			if (connection.getResponse() == null) {
				this.message = "Http 커넥션으로부터 Category 데이터를 취득하지 못했습니다.";
				logger.error("[getCategory] It's failed that get the category info from tistory.");
				continue;
			}
			if (blog.getCategories() == null) {
				blog.setCategories(new ArrayList<>());
			}
			// category
			defaultJsonStructor(connection.getResponse(), obj1 -> {
				if (!JsonConverter.JsonIsKey(obj1, "categories")) {
					return;
				}
				JsonConverter.parseArray(obj1.get("categories").toString(), obj2 -> {
					setCount(1, obj2.size());
					obj2.forEach(obj3 -> {
						this.message = "Category 취득 진행률 " + countBuffer.get(0) + "/" + countBuffer.get(1);
						logger.info("[getCategory] The category progress " + countBuffer.get(0) + "/" + countBuffer.get(1));
						setCount(countBuffer.get(0) + 1, countBuffer.get(1));
						JsonConverter.parseObject(obj3.toString(), obj4 -> {
							String id = JsonConverter.JsonString(obj4, "id");
							if (id == null) {
								return;
							}
							Category category = null;
							for (Category c : blog.getCategories()) {
								if (Util.StringEquals(c.getCategoryId(), id)) {
									category = c;
									break;
								}
							}
							if (category == null) {
								category = new Category();
								category.setCreateddate(new Date());
								category.setBlog(blog);
								blog.addCategory(category);
								category.setCategoryId(id);
							} else {
								category.setLastupdateddate(new Date());
							}
							category.setIsdeleted(false);
							category.setName(JsonConverter.JsonString(obj4, "name"));
							category.setParent(JsonConverter.JsonString(obj4, "parent"));
							category.setLabel(JsonConverter.JsonString(obj4, "label"));
							category.setEntries(JsonConverter.JsonString(obj4, "entries"));
						});
					});
				});
			});
		}
		return tuser;
	}

	public static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private TistoryUser getPostList(TistoryUser tuser, String token) {
		this.status = BlogStatus.postlist;
		this.message = "포스트 리스트를 취득합니다.";
		logger.info("[getPostList] It will be get the post list.");
		for (Blog blog : tuser.getBlogs()) {
			int page = 0;
			final List<Boolean> continueMem = new ArrayList<>(1);
			continueMem.add(true);
			while (continueMem.get(0)) {
				page++;
				parameterBuffer.clear();
				parameterBuffer.put("access_token", token);
				parameterBuffer.put("output", "json");
				parameterBuffer.put("blogName", blog.getName());
				parameterBuffer.put("page", String.valueOf(page));
				BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/post/list", parameterBuffer, HttpMethod.GET);
				if (connection.getResponse() == null) {
					this.message = "Http 커넥션으로부터 Post 데이터를 취득하지 못했습니다.";
					logger.error("[getPostList] It's failed that get the post info from tistory.");
					continue;
				}
				if (blog.getPosts() == null) {
					blog.setPosts(new ArrayList<>());
				}
				defaultJsonStructor(connection.getResponse(), obj1 -> {
					if (!JsonConverter.JsonIsKey(obj1, "posts")) {
						continueMem.clear();
						continueMem.add(false);
						return;
					}
					JsonConverter.parseArray(obj1.get("posts").toString(), obj2 -> {
						obj2.forEach(obj3 -> {
							JsonConverter.parseObject(obj3.toString(), obj4 -> {
								String id = JsonConverter.JsonString(obj4, "id");
								if (id == null) {
									return;
								}

								Post post = null;
								for (Post p : blog.getPosts()) {
									if (Util.StringEquals(p.getPostId(), id)) {
										post = p;
										break;
									}
								}
								if (post == null) {
									post = new Post();
									post.setCreateddate(new Date());
									post.setBlog(blog);
									blog.addPost(post);
									post.setPostId(id);
								} else {
									if (post.getIsmodified()) {
										return;
									}
									post.setLastupdateddate(new Date());
								}
								post.setIsdeleted(false);
								String categoryId = JsonConverter.JsonString(obj4, "categoryId");
								if (categoryId != null) {
									post.setCategoryId(categoryId);
									for (Category c : blog.getCategories()) {
										if (Util.StringEqualsUpper(c.getCategoryId(), categoryId)) {
											post.setCategory(c);
										}
									}
								}
								post.setTitle(JsonConverter.JsonString(obj4, "title"));
								post.setPostUrl(JsonConverter.JsonString(obj4, "postUrl"));
								String date = JsonConverter.JsonString(obj4, "date");
								try {
									post.setDate(formatter.parse(date));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							});
						});
					});
				});
			}
		}

		return tuser;
	}

	private TistoryUser getPost(TistoryUser tuser, String token) {
		String path = PropertyMap.getInstance().getProperty("config", "contents_path");
		File dir = new File(path);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdir();
		}
		this.status = BlogStatus.post;
		for (Blog blog : tuser.getBlogs()) {
			setCount(1, blog.getPosts().size());
			for (Post post : blog.getPosts()) {
				this.message = "포스트 취득 진행률 " + countBuffer.get(0) + "/" + countBuffer.get(1);
				logger.info("[getPost] The post progress " + countBuffer.get(0) + "/" + countBuffer.get(1));
				setCount(countBuffer.get(0) + 1, countBuffer.get(1));
				if (post.getIsdeleted()) {
					continue;
				}
				if (post.getIsmodified()) {
					continue;
				}
				parameterBuffer.clear();
				parameterBuffer.put("access_token", token);
				parameterBuffer.put("output", "json");
				parameterBuffer.put("blogName", blog.getName());
				parameterBuffer.put("postId", post.getPostId());
				BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/post/read", parameterBuffer, HttpMethod.GET);
				defaultJsonStructor(connection.getResponse(), obj1 -> {
					String filepath = "";
					if (Util.StringIsEmptyOrNull(post.getContentsPath())) {
						filepath = path + File.separator + Util.createCookieKey();
					} else {
						filepath = post.getContentsPath();
						File file = new File(filepath);
						if (file.exists()) {
							file.delete();
						}
					}
					String contents = JsonConverter.JsonString(obj1, "content");
					try (FileOutputStream stream = new FileOutputStream(filepath)) {
						stream.write(contents.getBytes(StandardCharsets.UTF_8.toString()));
					} catch (Throwable e) {
						logger.error("[getPost]  ", e);
						return;
					}
					post.setUrl(JsonConverter.JsonString(obj1, "url"));
					post.setSecondaryUrl(JsonConverter.JsonString(obj1, "secondaryUrl"));
					post.setTitle(JsonConverter.JsonString(obj1, "title"));
					post.setContentsPath(filepath);
					post.setPostUrl(JsonConverter.JsonString(obj1, "postUrl"));
					if ("0".equals(JsonConverter.JsonString(obj1, "visibility"))) {
						post.setIsdeleted(true);
					}
					String date = JsonConverter.JsonString(obj1, "date");
					try {
						post.setDate(formatter.parse(date));
					} catch (ParseException e) {
						logger.error("[getPost]", e);
					}
					post.setTags(obj1.get("tags").toString());
					logger.info("[getPost] postId : " + post.getPostId());
					logger.info("[getPost] title : " + post.getTitle());
					logger.info("[getPost] visibility : " + JsonConverter.JsonString(obj1, "visibility"));
				});
			}
		}
		return tuser;
	}
}
