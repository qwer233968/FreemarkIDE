package org.qxp.ctrl.mybatis.dao.po;

public class Param {

	private String name;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "@Param(\"" + this.name + "\") " + this.type +" " + this.name;
	}
	
	
}
