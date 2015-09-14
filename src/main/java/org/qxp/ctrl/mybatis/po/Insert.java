package org.qxp.ctrl.mybatis.po;

public class Insert {

	private String id;
	private String flushCache;
	private String parameterType;
	private String sql;
	private String statementType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlushCache() {
		return flushCache;
	}
	public void setFlushCache(String flushCache) {
		this.flushCache = flushCache;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	
}
