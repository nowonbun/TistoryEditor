package Controller.Servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Bean.PostBean;
import Bean.SelectBean;
import Common.AbstractServletController;
import Common.Define;
import Common.FactoryDao;
import Common.JsonConverter;
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
		getLogger().info("[main] request!");
		try {
			modelmap.addAttribute("count", FactoryDao.getDao(PostDao.class).getCountToWaitingPost());
			modelmap.addAttribute("pageCount", Define.PAGE_MAX_COUNT);
			return "main";
		} catch (Throwable e) {
			getLogger().error("[main]", e);
			return error();
		}
	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public String admin(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[admin] request!");
		return "admin";
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String list(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[list] request!");
		try {
			String type = req.getParameter("type");
			String id = req.getParameter("id");
			if (type == null || id == null) {
				getLogger().info("[list] type :" + type);
				getLogger().info("[list] id :" + id);
				throw new RuntimeException();
			}
			if (type.trim().toLowerCase().equals("blog")) {
				Blog blog = FactoryDao.getDao(BlogDao.class).selectByBlogId(id);
				if (blog == null) {
					getLogger().error("[list] The blog is null.");
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
				getLogger().error("[list] The typw was not match.");
				throw new RuntimeException();
			}
			modelmap.addAttribute("pType", type);
			modelmap.addAttribute("pId", id);
			modelmap.addAttribute("pageCount", Define.PAGE_MAX_COUNT);
			return "list";
		} catch (Throwable e) {
			getLogger().error("[list]", e);
			return error();
		}
	}

	@RequestMapping(value = "/post.html", method = RequestMethod.GET)
	public String post(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[post] request!");
		try {
			String idx = req.getParameter("idx");
			String id = req.getParameter("id");
			getLogger().info("[post] idx :" + idx);
			getLogger().info("[post] id :" + id);
			if (idx == null || id == null) {
				throw new RuntimeException();
			}
			int pIdx = 0;
			pIdx = Integer.parseInt(idx);
			Post post = FactoryDao.getDao(PostDao.class).selectByIdx(pIdx, id);
			if (post == null) {
				getLogger().error("[post] The post is null.");
				throw new RuntimeException();
			}
			PostBean bean = new PostBean();
			bean.setIdx(post.getIdx());
			bean.setPostId(post.getPostId());
			bean.setPostUrl(post.getPostUrl());
			bean.setTitle(post.getTitle());
			bean.setContents(post.getContent().getContents());
			bean.setCategory(post.getCategory().getLabel());
			bean.setCategoryId(post.getCategory().getCategoryId());
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
					bean.setTags(sb.toString());
				}
			} catch (RuntimeException e) {
				getLogger().error("[post] The tag parse was error.", e);
			}
			bean.setStatus(post.getIsdeleted());
			bean.setDate(Util.convertDateFormat(post.getDate()));
			modelmap.addAttribute("post", bean);
			return "post";
		} catch (Throwable e) {
			getLogger().error("[post]", e);
			return error();
		}
	}

	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public String create(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[create] request!");
		try {
			List<Category> listbuffer = FactoryDao.getDao(CategoryDao.class).selectAll();
			List<Category> list = listbuffer.stream()
					.filter(x -> !Util.StringIsEmptyOrNull(x.getParent())
							|| (Util.StringIsEmptyOrNull(x.getParent()) && !listbuffer.stream().filter(y -> Util.StringEquals(y.getParent(), x.getCategoryId())).findAny().isPresent()))
					.collect(Collectors.toList());
			List<SelectBean> selectobject = new ArrayList<>();
			SelectBean option = new SelectBean();
			option.setValue("-1");
			option.setText("<-- 카테고리 선택해 주십시오. -->");
			selectobject.add(option);
			for (Category item : list) {
				option = new SelectBean();
				option.setValue(Integer.toString(item.getIdx()));
				option.setText(item.getLabel());
				selectobject.add(option);
			}
			modelmap.addAttribute("categoryselect", selectobject);
			return "create";
		} catch (Throwable e) {
			getLogger().error("[create]", e);
			return error();
		}
	}

	@RequestMapping(value = "/deleteList.html", method = RequestMethod.GET)
	public String deleteList(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[deleteList] request!");
		try {
			long count = FactoryDao.getDao(PostDao.class).getCountToDelete();
			modelmap.addAttribute("title", "삭제 리스트");
			modelmap.addAttribute("count", count);
			modelmap.addAttribute("pageCount", Define.PAGE_MAX_COUNT);
			return "deleteList";
		} catch (Throwable e) {
			getLogger().error("[deleteList]", e);
			return error();
		}
	}

	@RequestMapping(value = "/error.html", method = RequestMethod.GET)
	public String error(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[error] request!");
		modelmap.addAttribute("message", "에러가 발생했습니다.<br />관리자에게 문의해 주십시오.");
		return "error";
	}
}
