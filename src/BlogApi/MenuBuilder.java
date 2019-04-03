package BlogApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Bean.MenuBean;
import Common.Define;
import Common.FactoryDao;
import Common.Util;
import Dao.BlogDao;
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
			bean.setType(Define.MENU_TYPE_PARANT);
			bean.setName(blog.getName());
			bean.setId(blog.getBlogid());
			List<MenuBean> childbuffer = new ArrayList<>();
			for (Category category : blog.getCategories()) {
				if (bean.getChild() == null) {
					bean.setChild(new ArrayList<>());
				}
				MenuBean child = new MenuBean();
				childbuffer.add(child);
				if (Util.StringIsEmptyOrNull(category.getParent())) {
					child.setParent(bean.getId());
					child.setType(Define.MENU_TYPE_BLOG_SUB);
					bean.getChild().add(child);
					child.setChild(childbuffer.stream().filter(x -> Util.StringEquals(x.getParent(), category.getCategoryId()) && x.getType() == Define.MENU_TYPE_CATEGORY_SUB).collect(Collectors.toList()));
				} else {
					Optional<MenuBean> search = childbuffer.stream().filter(x -> Util.StringEquals(x.getId(), category.getParent()) && x.getType() == Define.MENU_TYPE_BLOG_SUB).findFirst();
					if (search.isPresent()) {
						MenuBean parent = search.get();
						if (parent.getChild() == null) {
							parent.setChild(new ArrayList<>());
						}
						parent.getChild().add(child);
					}
					child.setType(Define.MENU_TYPE_CATEGORY_SUB);
					child.setParent(category.getParent());
				}
				child.setName(category.getName());
				child.setId(category.getCategoryId());
			}
			menulist.add(bean);
		}
		return menulist;
	}
}
