package org.qxp.ctrl.mybatis.dao.po;

public class Import {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "import " + this.name + ";";
	}
	
	
}
