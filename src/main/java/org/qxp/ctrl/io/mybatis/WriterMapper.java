package org.qxp.ctrl.io.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.io.BasicWriter;
import org.qxp.ctrl.mybatis.xml.MapperDirective;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.util.CommUtil;
import org.qxp.ctrl.util.Log;

public class WriterMapper implements BasicWriter{

	public boolean writerTemplate(Object o) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis";
			String readFile = "mapper.xml";
			String outputPath = SystemConfig.PROJECT_OUTPUT + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
			String outputFile = "UserMapper.xml";
			String charset = "utf-8";
			logger.debug(outputPath);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mapper", new MapperDirective((Mapper) o));
			CommUtil.processTemplate(readPath, readFile,
					charset, paramMap, outputPath, outputFile);
			logger.debug("mybatisXmlTest恭喜，生成成功~~");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean writerConfig(Object o) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis";
			String readFile = "mapper.xml";
			String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
			String outputFile = "UserMapper-config.xml";
			String charset = "utf-8";
			logger.debug(outputPath);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mapper", new MapperDirective((Mapper) o));
			CommUtil.processTemplate(readPath, readFile,
					charset, paramMap, outputPath, outputFile);
			logger.debug("mybatisXmlConfigTest恭喜，生成成功~~");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	private static final Logger logger= Log.getLogger(WriterMapper.class);

}
