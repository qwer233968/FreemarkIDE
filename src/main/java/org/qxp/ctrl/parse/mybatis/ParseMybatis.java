package org.qxp.ctrl.parse.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.qxp.ctrl.mybatis.xml.po.Insert;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.mybatis.xml.po.Select;
import org.qxp.ctrl.mybatis.xml.po.Update;
import org.qxp.ctrl.parse.DomParse;

public class ParseMybatis implements DomParse{

	@SuppressWarnings("unchecked")
	public Mapper parseXML(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(fileName);
		Element root = doc.getRootElement();
		Element dom_mapper = root.element("mapper");
		
		Mapper mapper = new Mapper();
		
		String namespace = dom_mapper.attributeValue("namespace");
		List<Element> dom_selects = dom_mapper.elements("select");
		List<Element> dom_inserts = dom_mapper.elements("insert");
		List<Element> dom_updates = dom_mapper.elements("update");
		List<Select> selectList = new ArrayList<Select>();
		for (Element select : dom_selects) {
			Select s = new Select();
			s.setFlushCache(select.attributeValue("flushCache"));
			s.setId(select.attributeValue("id"));
			s.setParameterType(select.attributeValue("parameterType"));
			s.setResultMap(select.attributeValue("resultMap"));
			s.setResultType(select.attributeValue("resultType"));
			s.setSql(select.getText());
			s.setStatementType(select.attributeValue("statementType"));
			selectList.add(s);
		}
		List<Insert> insertList = new ArrayList<Insert>();
		for (Element insert : dom_inserts) {
			Insert i = new Insert();
			i.setFlushCache(insert.attributeValue("flushCache"));
			i.setId(insert.attributeValue("id"));
			i.setParameterType(insert.attributeValue("parameterType"));
			i.setSql(insert.getText());
			i.setStatementType(insert.attributeValue("statementType"));
			insertList.add(i);
		}
		List<Update> updateList = new ArrayList<Update>();
		for (Element update : dom_updates) {
			Update u = new Update();
			u.setFlushCache(update.attributeValue("flushCache"));
			u.setId(update.attributeValue("id"));
			u.setParameterType(update.attributeValue("parameterType"));
			u.setSql(update.getText());
			u.setStatementType(update.attributeValue("statementType"));
			updateList.add(u);
		}
		
		mapper.setNamespace(namespace);
		mapper.setInsertList(insertList);
		mapper.setSelectList(selectList);
		mapper.setUpdateList(updateList);
		return mapper;
	}

}
