package Controller.Ajax;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import Bean.AttachmentBean;
import Bean.AttachmentListBean;
import Bean.ListBean;
import Bean.MenuBean;
import Bean.SyncStateBean;
import BlogApi.BlogApiThread;
import BlogApi.MenuBuilder;
import Common.AbstractAjaxController;
import Common.Define;
import Common.FactoryDao;
import Common.Util;
import Dao.AttachmentDao;
import Dao.BlogDao;
import Dao.CategoryDao;
import Dao.ContentDao;
import Dao.PostDao;
import Model.Attachment;
import Model.Blog;
import Model.Category;
import Model.Content;
import Model.Post;

@Controller
public class AjaxController extends AbstractAjaxController {
	@RequestMapping(value = "/sync.ajax", method = RequestMethod.POST)
	public void sync(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[sync] request!");
		BlogApiThread.instance().run();
		OKAjax(res);
	}

	@RequestMapping(value = "/syncStatus.ajax", method = RequestMethod.POST)
	public void syncStatus(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		// getLogger().info("[syncStatus] request!");
		SyncStateBean bean = new SyncStateBean();
		bean.setState(BlogApiThread.status().toString());
		bean.setMessgae(BlogApiThread.message());
		bean.setProgress(BlogApiThread.progress());
		returnJson(res, bean);
	}

	@RequestMapping(value = "/menu.ajax", method = RequestMethod.POST)
	public void menu(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[menu] request!");
		List<MenuBean> list = MenuBuilder.get().getMenu();
		returnJson(res, list);
	}

	@RequestMapping(value = "/list.ajax", method = RequestMethod.POST)
	public void list(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[list] request!");
		try {
			String type = req.getParameter("type");
			String id = req.getParameter("id");
			String page = req.getParameter("page");
			getLogger().info("[list] type :" + type);
			getLogger().info("[list] id :" + id);
			getLogger().info("[list] page :" + page);
			if (Util.StringIsEmptyOrNull(type) || Util.StringIsEmptyOrNull(id) || Util.StringIsEmptyOrNull(page)) {
				throw new RuntimeException();
			}
			int pagenumber = Integer.parseInt(page);
			List<Post> posts;
			if (type.trim().toLowerCase().equals("blog")) {
				Blog blog = FactoryDao.getDao(BlogDao.class).selectByBlogId(id);
				if (blog == null) {
					getLogger().error("[list] blog null ");
					throw new RuntimeException();
				}
				posts = FactoryDao.getDao(PostDao.class).selectByBlog(blog, pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			} else if (type.trim().toLowerCase().equals("category")) {
				Category category = FactoryDao.getDao(CategoryDao.class).getCategoryByCategoryId(id);
				if (category == null) {
					getLogger().error("[list] The category is null ");
					throw new RuntimeException();
				}
				posts = FactoryDao.getDao(PostDao.class).selectByCategoryId(id, pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			} else {
				getLogger().error("[list] The type was not match. ");
				throw new RuntimeException();
			}
			returnJson(res, injectListBean(posts));
		} catch (Throwable e) {
			getLogger().error("[list]", e);
			res.setStatus(406);
		}
	}

	private List<ListBean> injectListBean(List<Post> posts) {
		List<ListBean> ret = new ArrayList<>();
		for (Post post : posts) {
			ListBean bean = new ListBean();
			bean.setPostIdx(post.getIdx());
			bean.setPostId(post.getPostId());
			bean.setTitle(post.getTitle());
			bean.setPostUrl(post.getPostUrl());
			bean.setTags(post.getTags());
			bean.setDate(Util.convertDateFormat(post.getDate()));
			if ("-1".equals(post.getPostId()) && post.getIsmodified()) {
				bean.setStatus(1);
			} else if (post.getIsmodified() && post.getIsdeleted()) {
				bean.setStatus(3);
			} else if (post.getIsmodified()) {
				bean.setStatus(2);
			} else {
				bean.setStatus(0);
			}
			ret.add(bean);
		}
		return ret;
	}

	@RequestMapping(value = "/modifyPost.ajax", method = RequestMethod.POST)
	public void modifyPost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[modifyPost] request!");
		try {
			String idx = req.getParameter("idx");
			String postId = req.getParameter("postId");
			String title = req.getParameter("title");
			String contents = req.getParameter("contents");
			String tags = req.getParameter("tags");

			getLogger().info("[modifyPost] idx :" + idx);
			getLogger().info("[modifyPost] postId :" + postId);
			getLogger().info("[modifyPost] title :" + title);
			getLogger().info("[modifyPost] contents :" + contents);
			getLogger().info("[modifyPost] tags :" + tags);
			if (Util.StringIsEmptyOrNull(idx) || Util.StringIsEmptyOrNull(postId) || Util.StringIsEmptyOrNull(title) || Util.StringIsEmptyOrNull(contents)) {
				throw new RuntimeException();
			}
			int pIdx = Integer.parseInt(idx);
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, postId);
			if (post == null) {
				getLogger().error("[modifyPost] The post is null.");
				throw new RuntimeException();
			}
			post.setIsmodified(true);
			post.setLastupdateddate(new Date());
			post.setTitle(title);
			if (Util.StringIsEmptyOrNull(tags)) {
				post.setTags("");
			} else {
				StringBuffer sb = new StringBuffer();
				for (String i : tags.split(",")) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append("\"").append(i).append("\"");
				}
				post.setTags("{\"tag\":[" + sb.toString() + "]}");
			}
			Content content = post.getContent();
			if (content != null) {
				content.setIsdeleted(true);
				FactoryDao.getDao(ContentDao.class).update(content);
			}
			content = new Content();
			content.setPostId(post.getPostId());
			content.setContents(contents);
			content.setIsdeleted(false);
			content.setCreateddate(new Date());
			post.setContent(content);
			FactoryDao.getDao(PostDao.class).update(post);
			OKAjax(res, "포스트가 수정되었습니다.");
		} catch (Throwable e) {
			getLogger().error("[modifyPost]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/deletePost.ajax", method = RequestMethod.POST)
	public void deletePost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[deletePost] request!");
		try {
			String idx = req.getParameter("idx");
			String postId = req.getParameter("postId");
			getLogger().info("[deletePost] idx :" + idx);
			getLogger().info("[deletePost] postId :" + postId);
			if (Util.StringIsEmptyOrNull(idx) || Util.StringIsEmptyOrNull(postId)) {
				throw new RuntimeException();
			}
			int pIdx = Integer.parseInt(idx);
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, postId);
			if (post == null) {
				getLogger().error("[deletePost] The post is null.");
				throw new RuntimeException();
			}
			post.setIsmodified(true);
			post.setIsdeleted(true);
			post.setLastupdateddate(new Date());
			FactoryDao.getDao(PostDao.class).update(post);
			OKAjax(res, "포스트가 삭제 되었습니다.");
		} catch (Throwable e) {
			getLogger().error("deletePost[]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/cancelDeletePost.ajax", method = RequestMethod.POST)
	public void cancelDeletePost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[cancelDeletePost] request!");
		try {
			String idx = req.getParameter("idx");
			String postId = req.getParameter("postId");
			getLogger().info("[cancelDeletePost] idx :" + idx);
			getLogger().info("[cancelDeletePost] postId :" + postId);
			if (Util.StringIsEmptyOrNull(idx) || Util.StringIsEmptyOrNull(postId)) {
				throw new RuntimeException();
			}
			int pIdx = Integer.parseInt(idx);
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, postId);
			if (post == null) {
				getLogger().error("[cancelDeletePost] The post is null.");
				throw new RuntimeException();
			}
			post.setIsmodified(true);
			post.setIsdeleted(false);
			post.setLastupdateddate(new Date());
			FactoryDao.getDao(PostDao.class).update(post);
			OKAjax(res, "포스트가 삭제 취소 되었습니다.");
		} catch (Throwable e) {
			getLogger().error("[cancelDeletePost]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/createPost.ajax", method = RequestMethod.POST)
	public void createPost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[createPost] request!");
		try {
			String title = req.getParameter("title");
			String category = req.getParameter("category");
			String contents = req.getParameter("contents");
			String tags = req.getParameter("tags");
			getLogger().info("[createPost] title :" + title);
			getLogger().info("[createPost] category :" + category);
			getLogger().info("[createPost] contents :" + contents);
			getLogger().info("[createPost] tags :" + tags);
			if (Util.StringIsEmptyOrNull(title) || Util.StringIsEmptyOrNull(category)) {
				throw new RuntimeException();
			}
			int categoryIdx = Integer.parseInt(category);
			Category cate = FactoryDao.getDao(CategoryDao.class).getCategoryByIdx(categoryIdx);
			if (cate == null) {
				getLogger().error("[createPost] The category is null.");
				throw new RuntimeException();
			}

			Post post = new Post();
			post.setPostId("-1");
			post.setIsmodified(true);
			post.setIsdeleted(false);
			post.setCreateddate(new Date());
			post.setLastupdateddate(new Date());
			post.setTitle(title);
			if (Util.StringIsEmptyOrNull(tags)) {
				post.setTags("");
			} else {
				StringBuffer sb = new StringBuffer();
				for (String i : tags.split(",")) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append("\"").append(i).append("\"");
				}
				post.setTags("{\"tag\":[" + sb.toString() + "]}");
			}
			post.setCategory(cate);
			post.setCategoryId(cate.getCategoryId());
			post.setBlog(cate.getBlog());
			Content content = post.getContent();
			if (content != null) {
				content.setIsdeleted(true);
				FactoryDao.getDao(ContentDao.class).update(content);
			}
			content = new Content();
			content.setPostId(post.getPostId());
			content.setContents(contents);
			content.setIsdeleted(false);
			content.setCreateddate(new Date());
			post.setContent(content);
			FactoryDao.getDao(PostDao.class).create(post);
			OKAjax(res, "포스트가 추가되었습니다.");
		} catch (Throwable e) {
			getLogger().error("[createPost]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/getWaitlist.ajax", method = RequestMethod.POST)
	public void getWaitlist(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[getWaitlist] request!");
		try {
			String page = req.getParameter("page");
			getLogger().info("[getWaitlist] page :" + page);
			if (page == null) {
				getLogger().error("[getWaitlist] The page is null.");
				throw new RuntimeException();
			}
			int pagenumber = Integer.parseInt(page);
			List<Post> posts = FactoryDao.getDao(PostDao.class).selectToWaitingPost(pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			returnJson(res, injectListBean(posts));
		} catch (Throwable e) {
			getLogger().error("[getWaitlist]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/initWaitlist.ajax", method = RequestMethod.POST)
	public void initWaitlist(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[initWaitlist] request!");
		try {
			List<Post> posts = FactoryDao.getDao(PostDao.class).selectToWaitingPost();
			for (Post post : posts) {
				if ("-1".equals(post.getPostId())) {
					post.setIsdeleted(true);
				} else if (post.getIsdeleted()) {
					post.setIsdeleted(false);
				}
				post.setIsmodified(false);
				FactoryDao.getDao(PostDao.class).update(post);
			}
			OKAjax(res, "등록 대기 리스트가 초기화 되었습니다.");
		} catch (Throwable e) {
			getLogger().error("[initWaitlist]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/deleteList.ajax", method = RequestMethod.POST)
	public void deleteList(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[initWaitlist] request!");
		try {
			String page = req.getParameter("page");
			getLogger().info("[deleteList] page :" + page);
			if (Util.StringIsEmptyOrNull(page)) {
				getLogger().error("[deleteList] The page is null.");
				throw new RuntimeException();
			}
			int pagenumber = Integer.parseInt(page);
			List<Post> posts = FactoryDao.getDao(PostDao.class).selectToDelete(pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			returnJson(res, injectListBean(posts));
		} catch (Throwable e) {
			getLogger().error("[deleteList]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/attachmentList.ajax")
	public void attachmentList(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[attachmentList] request!");
		try {
			List<Attachment> list = FactoryDao.getDao(AttachmentDao.class).selectAll();
			AttachmentListBean ret = new AttachmentListBean();
			ret.setData(new ArrayList<>());
			for (Attachment item : list) {
				AttachmentBean bean = new AttachmentBean();
				ret.getData().add(bean);
				bean.setIdx(item.getIdx());
				bean.setLink(item.getLink());
				bean.setObjectData(String.valueOf(item.getIdx()));
				bean.setDate(Util.convertDateFormat(item.getCreateddate()));
				if (Util.StringIsEmptyOrNull(item.getLink()) || item.getData() == null) {
					bean.setState(String.valueOf(item.getIdx()));
				}
			}
			returnJson(res, ret);
		} catch (Throwable e) {
			getLogger().error("[attachmentList]", e);
			res.setStatus(406);
		}
	}

	@RequestMapping(value = "/applyAttachment.ajax", method = RequestMethod.POST)
	public void applyAttachment(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res, MultipartFile file) {
		getLogger().info("[applyAttachment] request!");
		try {
			String type = req.getParameter("type");
			String link = req.getParameter("link");
			String memo = req.getParameter("memo");
			String data = req.getParameter("data");

			if (Util.StringIsEmptyOrNull(type)) {
				getLogger().error("[applyAttachment] The type is null.");
				throw new RuntimeException();
			}
			if (Util.StringIsEmptyOrNull(link) && Util.StringIsEmptyOrNull(data)) {
				getLogger().error("[applyAttachment] The type is null.");
				throw new RuntimeException();
			}
			Attachment attach = new Attachment();
			attach.setLink(link);
			if (data != null) {
				String[] buffer = data.split(",");
				if (buffer.length == 2) {
					attach.setData(Base64.getDecoder().decode(buffer[1]));
				}
			}
			attach.setMemo(memo);
			attach.setIsdeleted(false);
			attach.setCreateddate(new Date());
			FactoryDao.getDao(AttachmentDao.class).create(attach);
			OKAjax(res, "등록 되었습니다.");
		} catch (Throwable e) {
			getLogger().error("[attachmentList]", e);
			res.setStatus(406);
		}
	}
}
