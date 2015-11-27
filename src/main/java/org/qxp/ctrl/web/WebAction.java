package org.qxp.ctrl.web;

import org.dom4j.Document;
import org.dom4j.Element;
import org.qxp.ctrl.log.Logger;
import org.qxp.ctrl.util.XmlUtil;
import org.qxp.ctrl.web.po.ContextParam;
import org.qxp.ctrl.web.po.Listener;
import org.qxp.ctrl.web.po.Servlet;

public class WebAction {

	private static final Logger logger = Logger.getLogger(WebAction.class);
	
	public static boolean addListener(String path, Listener listener){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_listener = XmlUtil.isNullByText(root, "listener/listener-class", listener.getListenerClass());
			logger.debug("listener isNull : " + isNull_listener);
			if(isNull_listener){
		        Element ele_listener = root.addElement("listener");
		        Element ele_listener_class = ele_listener.addElement("listener-class");
		        ele_listener_class.setText(listener.getListenerClass());
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addContextParam(String path, ContextParam contextParam){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_context = XmlUtil.isNullByText(root, "context-param/param-name", contextParam.getName());
			logger.debug("contextParam isNull : " + isNull_context);
			if(isNull_context){
		        Element ele_context = root.addElement("context-param");
		        Element ele_context_name = ele_context.addElement("param-name");
		        ele_context_name.setText(contextParam.getName());
		        Element ele_context_value = ele_context.addElement("param-value");
		        ele_context_value.setText(contextParam.getValue());
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addServlet(String path, Servlet servlet){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_servlet = XmlUtil.isNullByText(root, "servlet/servlet-name",servlet.getServletName());
			logger.debug("servlet isNull : " + isNull_servlet);
			if(isNull_servlet){
		        Element ele_servlet = root.addElement("servlet");
		        Element ele_servlet_name =  ele_servlet.addElement("servlet-name");
		        ele_servlet_name.setText(servlet.getServletName());
		        Element ele_servlet_class =  ele_servlet.addElement("servlet-class");
		        ele_servlet_class.setText(servlet.getServletClass());
		        Element ele_servlet_mapping = root.addElement("servlet-mapping");
		        Element ele_servlet_mapping_name =  ele_servlet_mapping.addElement("servlet-name");
		        ele_servlet_mapping_name.setText(servlet.getServletName());
		        Element ele_servlet_mapping_pattern =  ele_servlet_mapping.addElement("url-pattern");
		        ele_servlet_mapping_pattern.setText(servlet.getPattern());
		        if(null != servlet.getLoadOnStartup()){
		        	Element ele_servlet_startup =  ele_servlet.addElement("load-on-startup");
		        	ele_servlet_startup.setText(String.valueOf(servlet.getLoadOnStartup()));
		        }
		        if(null != servlet.getInitParam()){
		        	Element ele_servlet_init_param =  ele_servlet.addElement("init-param");
			        Element ele_servlet_init_param_name =  ele_servlet_init_param.addElement("param-name");
			        ele_servlet_init_param_name.setText(servlet.getInitParam().getName());
			        Element ele_servlet_init_param_value =  ele_servlet_init_param.addElement("param-value");
			        ele_servlet_init_param_value.setText(servlet.getInitParam().getValue());
		        }
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
