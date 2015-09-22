package org.qxp.ctrl.mybatis.dao.po;

import java.util.List;

public class Interface {

	private String name;
	private String resultType;
	private List<Param> params;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public List<Param> getParams() {
		return params;
	}
	public void setParams(List<Param> params) {
		this.params = params;
	}
	
}
