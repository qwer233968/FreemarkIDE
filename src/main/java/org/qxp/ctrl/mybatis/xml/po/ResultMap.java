package org.qxp.ctrl.mybatis.xml.po;

import java.util.List;

public class ResultMap {

	private String id;
	private String type;
	private List<MapProperties> mapProperties;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<MapProperties> getMapProperties() {
		return mapProperties;
	}
	public void setMapProperties(List<MapProperties> mapProperties) {
		this.mapProperties = mapProperties;
	}
	
}
