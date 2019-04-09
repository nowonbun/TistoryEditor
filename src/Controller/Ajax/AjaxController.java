package Controller.Ajax;

import java.util.ArrayList;
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
		} catch (RuntimeException e) {
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
}
