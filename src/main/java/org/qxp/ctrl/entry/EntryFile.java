package org.qxp.ctrl.entry;

import java.util.List;

import org.qxp.ctrl.base.File;

public class EntryFile extends File{

	private String packageName;
	
	private List<EntryProperties> properties;

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
	
	
}
