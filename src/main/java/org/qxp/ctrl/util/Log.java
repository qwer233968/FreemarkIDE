package org.qxp.ctrl.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	public static String RELATIVE_PATH = System.getProperty("user.dir");
	
	static{
		PropertyConfigurator.configure(RELATIVE_PATH + "/src/main/resources/log/log4j.properties");
	}
	
	public static Logger getLogger(Class<?> o){
		Logger logger  =  Logger.getLogger(o);
		return logger;
	}
}
