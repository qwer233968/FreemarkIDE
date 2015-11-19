package org.qxp.ctrl.web.po;

public class Servlet {

	private InitParam initParam;
	private String servletName;
	private String servletClass;
	private Integer loadOnStartup;
	private String pattern;
	
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getServletClass() {
		return servletClass;
	}
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	public Integer getLoadOnStartup() {
		return loadOnStartup;
	}
	public void setLoadOnStartup(Integer loadOnStartup) {
		this.loadOnStartup = loadOnStartup;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public InitParam getInitParam() {
		return initParam;
	}
	public void setInitParam(InitParam initParam) {
		this.initParam = initParam;
	}
	
	
}
