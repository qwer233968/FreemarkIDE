package org.qxp.ctrl.test;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.mybatis.dao.po.DaoFile;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.parse.mybatis.ParseDao;
import org.qxp.ctrl.parse.mybatis.ParseMybatis;

public class MybatisLoadXML {

	@Test
	public void loadMybatisXml(){
		String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
		String outputFile = "UserMapper-config.xml";
		ParseMybatis parseMybatis = new ParseMybatis();
		try {
			Mapper m = parseMybatis.parseXML(outputPath + "/" +outputFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMybatisDao(){
		String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.DAO_PACKAGE_NAME;
		String outputFile = "UserDao-config.xml";
		ParseDao parseDao = new ParseDao();
		try {
			DaoFile d = parseDao.parseXML(outputPath + "/" + outputFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
