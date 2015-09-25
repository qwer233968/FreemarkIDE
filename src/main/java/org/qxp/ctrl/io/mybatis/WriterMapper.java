package org.qxp.ctrl.io.mybatis;

import org.apache.log4j.Logger;
import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.io.BasicWriter;
import org.qxp.ctrl.test.MybatisTest;
import org.qxp.ctrl.util.Log;

public class WriterMapper implements BasicWriter{

	public boolean writerTemplate(Object o) {
		SystemConfig s= new SystemConfig();
		if(null == s.getProjectType() || SystemConfig.PROJECT_TYPE.equals(s.getProjectType())){
			
		}
		
		String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis";
		String readFile = "mapper.xml";
		String outputPath = SystemConfig.PROJECT_OUTPUT + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
		String outputFile = "UserMapper.xml";
		String charset = "utf-8";
		logger.debug(outputPath);
		return false;
	}

	public boolean writerConfig(Object o) {
		
		return false;
	}

	private static final Logger logger= Log.getLogger(WriterMapper.class);
}
