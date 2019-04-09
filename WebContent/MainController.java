package Controller.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Bean.PostBean;
import Common.AbstractServletController;
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
				long count = FactoryDao.getDao(PostDao.class).getCountByBlog(blog);
				modelmap.addAttribute("title", blog.getTitle());
				modelmap.addAttribute("count", count);
			} else if (type.trim().toLowerCase().equals("category")) {
				Category category = FactoryDao.getDao(CategoryDao.class).getCategoryByCategoryId(id);
				if (category == null) {
					throw new RuntimeException();
				}
				long count = FactoryDao.getDao(PostDao.class).getCountByCategoryId(id);
				modelmap.addAttribute("title", category.getLabel());
				modelmap.addAttribute("count", count);
			} else {
				throw new RuntimeException();
			}
			modelmap.addAttribute("pType", type);
			modelmap.addAttribute("pId", id);
			modelmap.addAttribute("pageCount", Define.PAGE_MAX_COUNT);
		} catch (RuntimeException e) {
			res.setStatus(403);
			return null;
		}
		return "list";
	}

	@RequestMapping(value = "/post.html", method = RequestMethod.GET)
	public String post(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String idx = req.getParameter("idx");
		String id = req.getParameter("id");
		try {
			if (idx == null || id == null) {
				throw new RuntimeException();
			}
			int pIdx = 0;
			try {
				pIdx = Integer.parseInt(idx);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, id);
			if (post == null) {
				throw new RuntimeException();
			}
			PostBean bean = new PostBean();
			bean.setIdx(post.getIdx());
			bean.setPostId(post.getPostId());
			bean.setPostUrl(post.getPostUrl());
			bean.setTitle(post.getTitle());
			bean.setTags(post.getTags());

			File file = new File(post.getContentsPath());
			if (!file.exists()) {
				throw new RuntimeException();
			}
			byte[] contents = new byte[(int) file.length()];
			try (FileInputStream stream = new FileInputStream(post.getContentsPath())) {
				stream.read(contents, 0, contents.length);
			} catch (IOException e) {
				throw new RuntimeException();
			}
			bean.setContents(new String(contents));
			bean.setCategory(post.getCategory().getLabel());
			bean.setTags(post.getTags());
			bean.setDate(Util.convertDateFormat(post.getDate()));
			modelmap.addAttribute("post", bean);
		} catch (RuntimeException e) {
			res.setStatus(403);
			return null;
		}
		return "post";
	}
}
