package org.qxp.ctrl.entry;

import java.util.List;

import org.qxp.ctrl.base.File;
import org.qxp.ctrl.mybatis.dao.po.Import;

public class EntryFile extends File{

	private String packageName;
	
	private List<EntryProperties> properties;
	
	private List<Import> importList;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<EntryProperties> getProperties() {
		return properties;
	}

	public void setProperties(List<EntryProperties> properties) {
		this.properties = properties;
	}

	public List<Import> getImportList() {
		return importList;
	}

	public void setImportList(List<Import> importList) {
		this.importList = importList;
	}
	
	
}
