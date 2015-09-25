package org.qxp.ctrl.mybatis.xml.po;

import java.util.List;

public class Mapper {

	private String namespace;
	
	private List<Insert> insertList;
	
	private List<Update> updateList;
	
	private List<Select> selectList;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public List<Insert> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Insert> insertList) {
		this.insertList = insertList;
	}

	public List<Update> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Update> updateList) {
		this.updateList = updateList;
	}

	public List<Select> getSelectList() {
		return selectList;
	}

	public void setSelectList(List<Select> selectList) {
		this.selectList = selectList;
	}

	
}
