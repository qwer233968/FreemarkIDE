package org.qxp.ctrl.io.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.io.BasicWriter;
import org.qxp.ctrl.mybatis.dao.DaoDirective;
import org.qxp.ctrl.mybatis.dao.po.DaoFile;
import org.qxp.ctrl.util.CommUtil;
import org.qxp.ctrl.util.Log;

public class WriterDao implements BasicWriter{

	public boolean writerTemplate(Object o) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis";
			String readFile = "mybatis.dao";
			String outputPath = SystemConfig.PROJECT_OUTPUT + "/" + SystemConfig.DAO_PACKAGE_NAME;
			String outputFile = "UserDao.java";
			String charset = "utf-8";
			logger.debug(outputPath);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dao", new DaoDirective((DaoFile) o));
			CommUtil.processTemplate(readPath, readFile,
					charset, paramMap, outputPath, outputFile);
			logger.debug("mybatisDaoTest恭喜，生成成功~~");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean writerConfig(Object o) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis/config";
			String readFile = "mybatis.dao.xml";
			String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.DAO_PACKAGE_NAME;
			String outputFile = "UserDao-config.xml";
			String charset = "utf-8";
			logger.debug(outputPath);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dao", new DaoDirective((DaoFile) o));
			CommUtil.processTemplate(readPath, readFile,
					charset, paramMap, outputPath, outputFile);
			logger.debug("mybatisDaoConfigTest恭喜，生成成功~~");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private static final Logger logger= Log.getLogger(WriterDao.class);

}
