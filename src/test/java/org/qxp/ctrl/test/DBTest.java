package org.qxp.ctrl.test;

import java.util.List;

import org.junit.Test;
import org.qxp.ctrl.mysql.DatabaseMetaDateApplication;

public class DBTest {

	@Test
	public void test(){
		String[] jarfiles = {"D:/work_svn/app/WarningServer_test/mysql-connector-java-5.1.6.jar"};
		String url = "jdbc:mysql://192.168.7.54:3306/step_app";
		String user = "root";
		String pwd = "48e6dba3da";
		try {
			DatabaseMetaDateApplication dm = new DatabaseMetaDateApplication(jarfiles, url, user, pwd);
			List<String> list = dm.getAllTableList(null);
			System.out.println(list.size());
			dm.colseCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
