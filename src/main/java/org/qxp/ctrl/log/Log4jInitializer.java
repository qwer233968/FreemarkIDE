package org.qxp.ctrl.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.xml.DOMConfigurator;

public final class Log4jInitializer {
    private static final String format = "yyyy-MM-dd HH:mm:ss";
    private static XMLWatchdog xdog;
    public static void configureAndWatch(String filename, long delay) {
    	if(null == xdog){
    		xdog = new XMLWatchdog(filename);
            xdog.setName("Log4jWatchdog");
            xdog.setDelay(delay);
            xdog.start();
    	}
    }

    private static final class XMLWatchdog extends FileWatchdog {

        public XMLWatchdog(String filename) {
            super(filename);
        }

        @Override
        public void doOnChange() {
            new DOMConfigurator().doConfigure(filename, LogManager.getLoggerRepository());
            System.out.println("log4j "+new SimpleDateFormat(format).format(new Date()) + " [" + filename + "] load completed.");
        }
    }

}