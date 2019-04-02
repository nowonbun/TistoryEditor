package BlogApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Bean.MenuBean;
import Common.FactoryDao;
import Common.Util;
import Common.IF.MenuType;
import Dao.BlogDao;
import Dao.CategoryDao;
import Model.Blog;
import Model.Category;

public class MenuBuilder {
	private static MenuBuilder singleton = null;

	public static MenuBuilder get() {
		if (singleton == null) {
			singleton = new MenuBuilder();
		}
		return singleton;
	}

	private MenuBuilder() {

	}

	private List<MenuBean> menulist = null;

	public List<MenuBean> getMenu() {
		if (menulist == null) {
			return init();
		}
		return menulist;
	}

	public List<MenuBean> init() {
		List<Blog> list = FactoryDao.getDao(BlogDao.class).selectAll();
		if (menulist == null) {
			menulist = new ArrayList<>();
		} else {
			menulist.clear();
		}
		for (Blog blog : list) {
			MenuBean bean = new MenuBean();
			bean.setType(MenuType.parent);
			bean.setName(blog.getName());
			bean.setUrl(blog.getUrl());
			List<Category> categoryList = blog.getCategories();
			for (Category category : blog.getCategories()) {
				if (bean.getChild() == null) {
					bean.setChild(new ArrayList<>());
				}
				MenuBean child = new MenuBean();
				if (category.getParent() == null) {
					child.setParent(bean);
				} else {
					Optional<MenuBean> search = menulist.stream().filter(x -> Util.StringEquals(x.getId(), category.getCategoryId())).findFirst();
					if (search.isPresent()) {
						child.setParent(search.get());
					} else {
						child.setParent(bean);
					}
				}

			}
			menulist.add(bean);
		}

		return menulist;
	}
}
