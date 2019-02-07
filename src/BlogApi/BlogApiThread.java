package BlogApi;

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
import Dao.OauthInfoDao;
import Dao.TistoryUserDao;
import Model.AccessToken;
import Model.Blog;
import Model.BlogStatistic;
import Model.Category;
import Model.OauthInfo;
import Model.TistoryUser;

public class BlogApiThread implements Runnable {
	private static BlogApiThread singleton = null;
	private BlogStatus status = BlogStatus.wait;
	private BlogSyncType type = null;
	private String code;
	private final Map<String, String> parameterBuffer = new HashMap<>();

	private BlogApiThread() {

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

	@Override
	public void run() {
		if (status == BlogStatus.wait && code != null && type != null) {
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

	private void pull(String token) {
		final List<TistoryUser> memBuffer = new ArrayList<>(1);
		parameterBuffer.clear();
		parameterBuffer.put("access_token", token);
		parameterBuffer.put("output", "json");
		BlogApiConnection connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/blog/info", parameterBuffer);
		if (connection.getResponse() == null) {
			status = BlogStatus.error;
			return;
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
				obj2.forEach(obj3 -> {
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
		status = BlogStatus.blog;
		for (Blog blog : memBuffer.get(0).getBlogs()) {
			parameterBuffer.clear();
			parameterBuffer.put("access_token", token);
			parameterBuffer.put("output", "json");
			parameterBuffer.put("blogName", blog.getName());
			connection = BlogApiConnectionBuilder.instance().build("https://www.tistory.com/apis/category/list", parameterBuffer);
			if (connection.getResponse() == null) {
				continue;
			}
			if (blog.getCategories() == null) {
				blog.setCategories(new ArrayList<>());
			}
			defaultJsonStructor(connection.getResponse(), obj1 -> {
				String id = JsonConverter.JsonString(obj1, "id");
				if(id == null) {
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
				category.setParent(JsonConverter.JsonString(obj1, "parent"));
				category.setLabel(JsonConverter.JsonString(obj1, "label"));
				category.setEntries(JsonConverter.JsonString(obj1, "entries"));
				//TODO: it can not insert the category data.
			});
		}
		FactoryDao.getDao(TistoryUserDao.class).update(memBuffer.get(0));
	}
}
