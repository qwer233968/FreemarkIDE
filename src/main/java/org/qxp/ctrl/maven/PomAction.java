package org.qxp.ctrl.maven;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.qxp.ctrl.log.Logger;
import org.qxp.ctrl.maven.po.Dependency;
import org.qxp.ctrl.maven.po.PomProperty;
import org.qxp.ctrl.util.XmlUtil;

public class PomAction {
	
	private static final Logger logger = Logger.getLogger(PomAction.class);
	private static final String PRO_DOM_NAME = "properties";
	private static final String DEP_DOM_NAME = "dependencies";
	
	public static boolean addProperties(String path, PomProperty pomProperty){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_properties = XmlUtil.isNull(root, PRO_DOM_NAME);
			logger.debug("properties isNull : " + isNull_properties);
			if(isNull_properties){
		        Element properties = root.addElement(PRO_DOM_NAME);
		        Element spring = properties.addElement(pomProperty.getKey());
		        spring.setText(pomProperty.getValue());
			}else{
				boolean isNull_key = XmlUtil.isNull(root, PRO_DOM_NAME + "/" + pomProperty.getKey());
				logger.debug("pomProperty isNull : " + isNull_key);
				if(isNull_key){
			        Element properties = XmlUtil.selectSingleNode_namespace(root, PRO_DOM_NAME);
			        String key = pomProperty.getKey();
			        Element spring = properties.addElement(key);
			        spring.setText(pomProperty.getValue());
				}
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addDependency(String path, Dependency dependency){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_properties = XmlUtil.isNull(root, DEP_DOM_NAME);
			if(isNull_properties){
		        Element dependencies = root.addElement(DEP_DOM_NAME);
		        boolean isExist = checkDependency(dependencies, dependency);
		        if(!isExist){
		        	createElement(dependencies, dependency);
		        }
			}else{
				Element dependencies = XmlUtil.selectSingleNode_namespace(root, DEP_DOM_NAME);
				boolean isExist = checkDependency(dependencies, dependency);
		        if(!isExist){
		        	createElement(dependencies, dependency);
		        }
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addDependencys(String path, Dependency[] dependencyArray){
		try{
			Document doc = XmlUtil.getDocument(path);
			Element root = doc.getRootElement();
			boolean isNull_properties = XmlUtil.isNull(root, DEP_DOM_NAME);
			if(isNull_properties){
		        Element dependencies = root.addElement(DEP_DOM_NAME);
		        for(Dependency dependency : dependencyArray){
		        	boolean isExist = checkDependency(dependencies, dependency);
			        if(!isExist){
			        	createElement(dependencies, dependency);
			        }
		        }
			}else{
				Element dependencies = XmlUtil.selectSingleNode_namespace(root, DEP_DOM_NAME);
				for(Dependency dependency : dependencyArray){
		        	boolean isExist = checkDependency(dependencies, dependency);
			        if(!isExist){
			        	createElement(dependencies, dependency);
			        }
		        }
			}
			XmlUtil.writeXml(path, doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void createElement(Element dependencies, Dependency dependency){
		Element ele = dependencies.addElement("dependency");
        ele.addElement("groupId").setText(dependency.getGroupId());
        ele.addElement("artifactId").setText(dependency.getArtifactId());
        ele.addElement("version").setText(dependency.getVersion());
        ele.addElement("scope").setText(dependency.getScope());
	}
	
	/**
	 * 检查dependency 是否已存在
	 * @param dependencies
	 * @param dependency
	 * @return true: 已存在, false: 不存在
	 */
	public static boolean checkDependency(Element dependencies, Dependency dependency){
		List<Element> list = XmlUtil.selectNodes_namespace(dependencies, "dependency/artifactId");
		Iterator<Element> iter = list.iterator();  
		if (iter.hasNext()) {  
            Element artifactId = (Element) iter.next();  
            String text = artifactId.getTextTrim();
            if(text.equalsIgnoreCase(dependency.getArtifactId())){
            	return true;
            }
        }
		return false;
	}
	
}
