package org.qxp.ctrl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {

	private static final String ENCODING = "UTF-8";
	
	public static String getXPath(String xpathExpression){
		StringBuffer sb = new StringBuffer("/");
		String[] array = xpathExpression.split("/");
		for(String path : array){
			sb.append("/xmlns:").append(path);
		}
		return sb.toString();
	}
	
	/**
	 * 判断是否为空
	 * @param root
	 * @param xpathExpression
	 * @return true: 为null, false: 存在的
	 * @throws DocumentException 
	 */
	public static boolean isNull(Element root, String xpathExpression) throws DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		// 获得命名空间
		String nsURI = root.getNamespaceURI();
		map.put("xmlns", nsURI);
		String xpath = getXPath(xpathExpression);
		XPath x = root.createXPath(xpath);
		x.setNamespaceURIs(map);
        Element ele = (Element) x.selectSingleNode(root);
		if(ele == null){
			return true;
        }
		return false;
	}
	
	/**
	 * 向xml文件中写入新文档
	 * @param path xml文件地址
	 * @param doc 新文档
	 * @throws IOException
	 */
	public static void writeXml(String path, Document doc) throws IOException{
		OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding(ENCODING);
        FileOutputStream output = new FileOutputStream(new File(path));
        XMLWriter writer = new XMLWriter(output, format);
        writer.write(doc);
        writer.flush();
        writer.close();
	}
	
	/**
	 * 获取 Document 上下文
	 * @param path
	 * @return root 根节点
	 * @throws DocumentException
	 */
	public static Document getDocument(String path) throws DocumentException{
		SAXReader reader = new SAXReader();  
        reader.setEncoding(ENCODING);  
        Document doc = reader.read(path);
        return doc;
	}
	
	public static Element selectSingleNode_namespace(Element root, String xpathExpression){
		Map<String,String> map = new HashMap<String,String>();
		// 获得命名空间
		String nsURI = root.getNamespaceURI();
		map.put("xmlns", nsURI);
		String xpath = getXPath(xpathExpression);
		XPath x = root.createXPath(xpath);
		x.setNamespaceURIs(map);
        Element ele = (Element) x.selectSingleNode(root);
        return ele;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> selectNodes_namespace(Element ele,String xpathExpression){
		Map<String,String> map = new HashMap<String,String>();
		// 获得命名空间
		String nsURI = ele.getNamespaceURI();
		map.put("xmlns", nsURI);
		String xpath = getXPath(xpathExpression);
		XPath x = ele.createXPath(xpath);
		x.setNamespaceURIs(map);
        List<Element> list = x.selectNodes(ele);
        return list;
	}
}
