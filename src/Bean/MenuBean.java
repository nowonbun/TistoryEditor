package Bean;

import java.util.List;

public class MenuBean {
	private int type;
	private String name;
	private String parent;
	private List<MenuBean> child;
	private String id;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuBean> getChild() {
		return child;
	}

	public void setChild(List<MenuBean> child) {
		this.child = child;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
