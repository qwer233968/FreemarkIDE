package org.qxp.ctrl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.qxp.ctrl.mybatis.DefaultDirective;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MybatisTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() throws IOException, TemplateException{
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("D:/工具/eclipse-rcp-mars-R-win32-x86_64/workspace/freemark_maven/src/test/resources/template/mybatis"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("utf-8");
		Template t = cfg.getTemplate("mybatis.xml");
		Map<String, Object> paramMap = new HashMap<String, Object>();  
		//自定义标签解析  
		paramMap.put("id", "findUser");  
		paramMap.put("resultType", "java.lang.Integer");  
		paramMap.put("notes", "查询所有用户");  
		paramMap.put("sql", "select * from tb_user");  
		paramMap.put("namespace", "org.qxp.mapper");  
		//paramMap.put("mybatis", new DefaultDirective());  
		Writer writer =  new OutputStreamWriter(new FileOutputStream("D:/工具/eclipse-rcp-mars-R-win32-x86_64/workspace/freemark_maven/src/test/resources/output/mybatis/mybatis.xml"), "UTF-8");
		t.process(paramMap, writer);
		System.out.println("恭喜，生成成功~~");
	}
}
