package org.qxp.ctrl.parse.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.qxp.ctrl.mybatis.dao.po.DaoFile;
import org.qxp.ctrl.mybatis.dao.po.Import;
import org.qxp.ctrl.mybatis.dao.po.Interface;
import org.qxp.ctrl.parse.DomParse;

public class ParseDao implements DomParse{

	public Object parseXML(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(fileName);
		Element root = doc.getRootElement();
		Element dom = root.element("dom");
		Element packageName = dom.element("package-name");
		Element interfaceName = dom.element("interface-name");
		List<Element> importList = dom.elements("import-list");
		List<Element> interfaceList = dom.elements("interface-list");
		DaoFile daoFile = new DaoFile();
		daoFile.setInterfaceName(interfaceName.getText());
		daoFile.setPackageName(packageName.getText());
		List<Import> _importList = new ArrayList<Import>();
		for (Element imports : importList) {
			Import i = new Import();
			i.setName(imports.getText());
			_importList.add(i);
		}
		daoFile.setImportList(_importList);
		List<Interface> _interfaceList = new ArrayList<Interface>();
		for (Element interfaces : interfaceList) {
			Interface i = new Interface();
			i.setName(interfaces.attributeValue("name"));
			i.setResultType(interfaces.attributeValue("result-type"));
			List<Element> paramList= interfaces.elements("params");
			for (Element param : paramList) {
				param.attributeValue("name");
				param.attributeValue("type");
			}
			_interfaceList.add(i);
		}
		daoFile.setInterfaceList(_interfaceList);
		return null;
	}

}
