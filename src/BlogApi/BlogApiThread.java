package BlogApi;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import javax.json.JsonObject;
import Common.FactoryDao;
import Common.JsonConverter;
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
	private BlogStatus status = BlogStatus.wait;
	private String message = "";
	private BlogSyncType type = null;
	private String code;
	private final Map<String, String> parameterBuffer = new HashMap<>();
	private final List<Integer> countBuffer = new ArrayList<>(2);

	private BlogApiThread() {
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

	@Override
	public void run() {
		if (status == BlogStatus.wait && code != null && type != null) {
			status = BlogStatus.init;
			FactoryDao.getDao(TistoryUserDao.class).deleteAll();
			this.message = "The tisotryuser table was initialize.";
			FactoryDao.getDao(BlogDao.class).deleteAll();
			this.message = "The blog table was initialize.";
			FactoryDao.getDao(BlogStatisticDao.class).deleteAll();
			this.message = "The blogstatistic table was initialize.";
			FactoryDao.getDao(CategoryDao.class).deleteAll();
			this.message = "The category table was initialize.";
			FactoryDao.getDao(PostDao.class).deleteAll();
			this.message = "The post table was initialize.";
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
					BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/oauth/access_token", parameterBuffer);
					String access_token = connection.getResponse().replace("access_token=", "").replace("\r", "").replace("\n", "");
					if (Util.StringIsEmptyOrNull(access_token)) {
						status = BlogStatus.error;
						this.message = "It's failed that get the access_token from tistory.";
						return;
					}

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
					this.message = "The oauthInfo table was updated.";
					status = BlogStatus.token;
					if (type == BlogSyncType.pull) {
						pull(access_token);
					} else if (type == BlogSyncType.push) {

					}
				} finally {
					status = BlogStatus.wait;
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
		countBuffer.set(1, count);
		countBuffer.set(0, index);
	}

	private void pull(String token) {
		TistoryUser tuser = getBlog(token);
		getCategory(tuser, token);
		getPostList(tuser, token);
		getPost(tuser, token);
		FactoryDao.getDao(TistoryUserDao.class).update(tuser);
	}

	private TistoryUser getBlog(String token) {
		this.status = BlogStatus.blog;
		final List<TistoryUser> memBuffer = new ArrayList<>(1);
		parameterBuffer.clear();
		parameterBuffer.put("access_token", token);
		parameterBuffer.put("output", "json");
		BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/blog/info", parameterBuffer);
		if (connection.getResponse() == null) {
			this.status = BlogStatus.error;
			this.message = "It's failed that get the blog info from tistory.";
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
					this.message = "The blog progress " + countBuffer.get(0) + "/" + countBuffer.get(1);
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
			BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/category/list", parameterBuffer);
			if (connection.getResponse() == null) {
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
						this.message = "The category progress " + countBuffer.get(0) + "/" + countBuffer.get(1);
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
		this.message = "The blog list is getting.";
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
				BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/post/list", parameterBuffer);
				if (connection.getResponse() == null) {
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
				this.message = "The post progress " + countBuffer.get(0) + "/" + countBuffer.get(1);
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
				BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/post/read", parameterBuffer);
				defaultJsonStructor(connection.getResponse(), obj1 -> {
					// contents
					String filepath = path + File.separator + Util.createCookieKey();
					String contents = JsonConverter.JsonString(obj1, "content");
					try (FileOutputStream stream = new FileOutputStream(filepath)) {
						stream.write(contents.getBytes("UTF-8"));
					} catch (Throwable e) {
						e.printStackTrace();
						return;
					}
					post.setUrl(JsonConverter.JsonString(obj1, "url"));
					post.setSecondaryUrl(JsonConverter.JsonString(obj1, "secondaryUrl"));
					post.setTitle(JsonConverter.JsonString(obj1, "title"));
					post.setContentsPath(filepath);
					post.setPostUrl(JsonConverter.JsonString(obj1, "postUrl"));
					String date = JsonConverter.JsonString(obj1, "date");
					try {
						post.setDate(formatter.parse(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					post.setTags(obj1.get("tags").toString());
				});
			}
		}
		return tuser;
	}
}
