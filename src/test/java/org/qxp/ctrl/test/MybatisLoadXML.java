package org.qxp.ctrl.test;

import org.junit.Test;
import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.io.mybatis.ReaderDao;
import org.qxp.ctrl.io.mybatis.ReaderMapper;
import org.qxp.ctrl.mybatis.dao.po.DaoFile;
import org.qxp.ctrl.mybatis.xml.po.Mapper;

public class MybatisLoadXML {

	@Test
	public void loadMybatisXml(){
		String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
		String outputFile = "UserMapper-config.xml";
		ReaderMapper readerMapper = new ReaderMapper();
		Mapper m = (Mapper) readerMapper.readerConfig(outputPath + "/" +outputFile);
		System.out.println(m.getFileName());
	}
	
	@Test
	public void loadMybatisDao(){
		String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.DAO_PACKAGE_NAME;
		String outputFile = "UserDao-config.xml";
		ReaderDao readerDao = new ReaderDao();
		DaoFile d = (DaoFile) readerDao.readerConfig(outputPath + "/" + outputFile);
		System.out.println(d.getFileName());
	}
}
