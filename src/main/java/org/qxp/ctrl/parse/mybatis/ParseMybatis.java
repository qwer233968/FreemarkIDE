package org.qxp.ctrl.parse.mybatis;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.qxp.ctrl.parse.DomParse;

public class ParseMybatis implements DomParse{

	public Object parseXML(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(fileName);
		Element root = doc.getRootElement();
		Element dom = root.element("dom");
		Element packageName = dom.element("package-name");
		Element interfaceName = dom.element("interface-name");
		List<Element> importList = dom.elements("import-list");
		List<Element> interfaceList = dom.elements("interface-list");
		
		
		for (Element imports : importList) {
			imports.getText();
		}
		for (Element interfaces : interfaceList) {
			interfaces.attributeValue("name");
			interfaces.attributeValue("result-type");
			List<Element> paramList= interfaces.elements("params");
			for (Element param : paramList) {
				param.attributeValue("name");
				param.attributeValue("type");
			}
		}
		return null;
	}

}
