package org.qxp.ctrl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BasicTest {

	@SuppressWarnings("deprecation")
	@Test
	public void Test1() throws IOException, TemplateException{
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("D:/工具/eclipse-rcp-mars-R-win32-x86_64/workspace/freemark_maven/src/test/resources/template"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("utf-8");
		Template t = cfg.getTemplate("test.html");
		Map<String, Object> paramMap = new HashMap<String, Object>();  
		//自定义标签解析  
		paramMap.put("content", new CustomDirective());  
		Writer writer =  new OutputStreamWriter(new FileOutputStream("D:/工具/eclipse-rcp-mars-R-win32-x86_64/workspace/freemark_maven/src/test/resources/output/succcess.html"), "UTF-8");
		t.process(paramMap, writer);
		System.out.println("恭喜，生成成功~~");
	}
}
