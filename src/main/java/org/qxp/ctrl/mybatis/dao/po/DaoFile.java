package org.qxp.ctrl.mybatis.dao.po;

import java.util.List;

public class DaoFile {

	private String fileName;
	private String suffix;
	private String packageName;
	private String interfaceName;
	private List<Import> importList;
	private List<Interface> interfaceList;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public List<Import> getImportList() {
		return importList;
	}
	public void setImportList(List<Import> importList) {
		this.importList = importList;
	}
	public List<Interface> getInterfaceList() {
		return interfaceList;
	}
	public void setInterfaceList(List<Interface> interfaceList) {
		this.interfaceList = interfaceList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
