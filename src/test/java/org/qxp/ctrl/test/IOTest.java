package org.qxp.ctrl.test;

import org.junit.Test;
import org.qxp.ctrl.util.IOUtil;

public class IOTest {

	public void testDown(){
		String remoteFilePath = "https://github.com/qwer233968/resources/tree/master/spring/spring-mvc.xml";
		String localFilePath = "D:/soft/gitWork/FreemarkIDE/src/test/resources/spring-mvc.xml";
		IOUtil.downloadRemoteFile(remoteFilePath, localFilePath);
	}
	
	@Test
	public void testLocalDown(){
		String remoteFilePath = "D:/soft/gitWork/resources/spring/spring-mvc.xml";
		String localFilePath = "D:/soft/gitWork/FreemarkIDE/src/test/resources/spring-mvc.xml";
		IOUtil.downloadLocalFile(remoteFilePath, localFilePath);
	}
}
