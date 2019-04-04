package Controller.Servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Bean.ListBean;
import Common.AbstractServletController;
import Common.Define;
import Common.FactoryDao;
import Common.Util;
import Dao.BlogDao;
import Dao.PostDao;
import Model.Blog;
import Model.Post;

@Controller
public class MainController extends AbstractServletController {
	@RequestMapping(value = "/main.html", method = RequestMethod.GET)
	public String main(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return "main";
	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public String admin(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return "admin";
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String list(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String type = req.getParameter("type");
		String id = req.getParameter("id");
		try {
			if (type == null || id == null) {
				throw new RuntimeException();
			}
			if (type.trim().toLowerCase().equals("blog")) {
				Blog blog = FactoryDao.getDao(BlogDao.class).selectByBlogId(id);
				if (blog == null) {
					throw new RuntimeException();
				}
				List<Post> posts = FactoryDao.getDao(PostDao.class).selectByBlog(blog, 1, Define.PAGE_MAX_COUNT);
				modelmap.addAttribute("list", injectListBean(posts));
			} else if (type.trim().toLowerCase().equals("category")) {
				//TODO: Now working
				//List<Post> posts = FactoryDao.getDao(PostDao.class).selectByCategoryId(categoryId, start, count);
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			res.setStatus(403);
			return null;
		}
		return "list";
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
