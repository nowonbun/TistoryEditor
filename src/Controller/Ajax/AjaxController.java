package Controller.Ajax;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import Bean.ListBean;
import Bean.MenuBean;
import Bean.SyncStateBean;
import BlogApi.BlogApiThread;
import BlogApi.MenuBuilder;
import Common.AbstractAjaxController;
import Common.Define;
import Common.FactoryDao;
import Common.PropertyMap;
import Common.Util;
import Dao.BlogDao;
import Dao.CategoryDao;
import Dao.PostDao;
import Model.Blog;
import Model.Category;
import Model.Post;

@Controller
public class AjaxController extends AbstractAjaxController {
	@RequestMapping(value = "/sync.ajax")
	public void sync(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		BlogApiThread.instance().run();
		OKAjax(res);
	}

	@RequestMapping(value = "/syncStatus.ajax")
	public void syncStatus(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		SyncStateBean bean = new SyncStateBean();
		bean.setState(BlogApiThread.status().toString());
		bean.setMessgae(BlogApiThread.message());
		bean.setProgress(BlogApiThread.progress());
		returnJson(res, bean);
	}

	@RequestMapping(value = "/menu.ajax")
	public void menu(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		List<MenuBean> list = MenuBuilder.get().getMenu();
		returnJson(res, list);
	}

	@RequestMapping(value = "/list.ajax")
	public void list(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String type = req.getParameter("type");
		String id = req.getParameter("id");
		String page = req.getParameter("page");
		try {
			if (type == null || id == null || page == null) {
				throw new RuntimeException();
			}
			int pagenumber = 0;
			try {
				pagenumber = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
			List<Post> posts;
			if (type.trim().toLowerCase().equals("blog")) {
				Blog blog = FactoryDao.getDao(BlogDao.class).selectByBlogId(id);
				if (blog == null) {
					throw new RuntimeException();
				}
				posts = FactoryDao.getDao(PostDao.class).selectByBlog(blog, pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			} else if (type.trim().toLowerCase().equals("category")) {
				Category category = FactoryDao.getDao(CategoryDao.class).getCategoryByCategoryId(id);
				if (category == null) {
					throw new RuntimeException();
				}
				posts = FactoryDao.getDao(PostDao.class).selectByCategoryId(id, pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			} else {
				throw new RuntimeException();
			}
			returnJson(res, injectListBean(posts));
		} catch (Throwable e) {
			res.setStatus(403);
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
			ret.add(bean);
		}
		return ret;
	}

	@RequestMapping(value = "/modifyPost.ajax")
	public void modifyPost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String idx = req.getParameter("idx");
		String postId = req.getParameter("postId");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String tags = req.getParameter("tags");
		try {
			if (idx == null || postId == null || title == null || contents == null) {
				throw new RuntimeException();
			}
			int pIdx = 0;
			pIdx = Integer.parseInt(idx);
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, postId);
			if (post == null) {
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
			FactoryDao.getDao(PostDao.class).update(post);
			File file = new File(post.getContentsPath());
			File newFile = new File(post.getContentsPath() + Util.getTimeUnique() + ".bak");
			int index = 0;
			while (true) {
				if (newFile.exists()) {
					newFile = new File(post.getContentsPath() + Util.getTimeUnique() + "(" + (++index) + ").bak");
					continue;
				}
				break;
			}
			file.renameTo(newFile);
			try (FileOutputStream stream = new FileOutputStream(post.getContentsPath())) {
				stream.write(contents.getBytes("UTF-8"));
			}

			OKAjax(res, "포스트가 수정되었습니다.");
		} catch (Throwable e) {
			res.setStatus(403);
		}
	}

	@RequestMapping(value = "/deletePost.ajax")
	public void deletePost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String idx = req.getParameter("idx");
		String postId = req.getParameter("postId");
		try {
			if (idx == null || postId == null) {
				throw new RuntimeException();
			}
			int pIdx = 0;
			try {
				pIdx = Integer.parseInt(idx);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, postId);
			if (post == null) {
				throw new RuntimeException();
			}
			post.setIsmodified(true);
			post.setIsdeleted(true);
			post.setLastupdateddate(new Date());
			FactoryDao.getDao(PostDao.class).update(post);
			OKAjax(res, "포스트가 삭제되었습니다.");
		} catch (Throwable e) {
			res.setStatus(403);
		}
	}

	@RequestMapping(value = "/createPost.ajax")
	public void createPost(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String contents = req.getParameter("contents");
		String tags = req.getParameter("tags");
		try {
			if (title == null || category == null) {
				throw new RuntimeException();
			}
			int categoryIdx = 0;
			categoryIdx = Integer.parseInt(category);
			Category cate = FactoryDao.getDao(CategoryDao.class).getCategoryByIdx(categoryIdx);
			if (cate == null) {
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
			String filepath = "";
			String path = PropertyMap.getInstance().getProperty("config", "contents_path");
			File dir = new File(path);
			if (!(dir.exists() && dir.isDirectory())) {
				dir.mkdir();
			}
			if (Util.StringIsEmptyOrNull(post.getContentsPath())) {
				filepath = path + File.separator + Util.createCookieKey();
			} else {
				filepath = post.getContentsPath();
				File file = new File(filepath);
				if (file.exists()) {
					file.delete();
				}
			}
			try (FileOutputStream stream = new FileOutputStream(filepath)) {
				stream.write(contents.getBytes("UTF-8"));
			} catch (Throwable e) {
				e.printStackTrace();
				return;
			}

			post.setContentsPath(filepath);
			FactoryDao.getDao(PostDao.class).create(post);
			OKAjax(res, "포스트가 추가되었습니다.");
		} catch (Throwable e) {
			res.setStatus(403);
		}
	}

	@RequestMapping(value = "/getWaitlist.ajax")
	public void getWaitlist(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		try {
			if (page == null) {
				throw new RuntimeException();
			}
			int pagenumber = 0;
			try {
				pagenumber = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
			List<Post> posts = FactoryDao.getDao(PostDao.class).selectToWaitingPost(pagenumber * Define.PAGE_MAX_COUNT, Define.PAGE_MAX_COUNT);
			returnJson(res, injectListBean(posts));
		} catch (Throwable e) {
			res.setStatus(403);
		}
	}

	@RequestMapping(value = "/initWaitlist.ajax")
	public void initWaitlist(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
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
			res.setStatus(403);
		}
	}
}
