package Bean;

import java.util.List;

import Common.IF.MenuType;

public class MenuBean {
	private MenuType type;
	private String name;
	private String url;
	private MenuBean parent;
	private List<MenuBean> child;
	private String id;

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuBean> getChild() {
		return child;
	}

	public void setChild(List<MenuBean> child) {
		this.child = child;
	}

	public MenuBean getParent() {
		return parent;
	}

	public void setParent(MenuBean parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
