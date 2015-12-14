package org.qxp.ctrl.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.qxp.ctrl.mysql.DatabaseMetaDateApplication;

public class DBTest {

	@Test
	public void test(){
		String[] jarfiles = {"file://D:/maven/repo/org/srdbsql/srdbsql.Driver/1.0/srdbsql.Driver-1.0.jar"};
		String url = "jdbc:srdbsql://10.168.220.95:1975/ceshidb";
		String user = "dba";
		String pwd = "dba";
		String driver = "org.srdbsql.Driver";
		try {
			DatabaseMetaDateApplication dm = new DatabaseMetaDateApplication(jarfiles, driver, url, user, pwd);
			dm.setSpecialDB(true);
			List<String> list = dm.getAllTableList("public");
			Map<String,String> map = dm.getTableColumns(null, "t_product_account");
			dm.colseCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
