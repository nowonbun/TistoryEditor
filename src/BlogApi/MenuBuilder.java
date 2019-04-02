package BlogApi;

import java.util.ArrayList;
import java.util.List;

import Bean.MenuBean;
import Common.FactoryDao;
import Common.IF.MenuType;
import Dao.CategoryDao;
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
		if(menulist == null) {
			return init();
		}
		return menulist;
	}

	public List<MenuBean> init() {
		List<Category> list = FactoryDao.getDao(CategoryDao.class).selectAll();
		menulist = new ArrayList<>();
		for (Category item : list) {
			MenuBean bean = new MenuBean();
			bean.setType(MenuType.sub);
			bean.setName(item.getName());
			bean.setUrl(null);
			menulist.add(bean);
		}
		return menulist;
	}
}
