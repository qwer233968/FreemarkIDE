package org.qxp.ctrl.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.qxp.ctrl.mybatis.MapperDirective;
import org.qxp.ctrl.mybatis.SelectDirective;
import org.qxp.ctrl.util.CommUtil;

import freemarker.template.TemplateException;

public class MybatisTest {

	@Test
	public void selectTest() throws IOException, TemplateException {
		Writer writer = new OutputStreamWriter(
				new FileOutputStream("D:/soft/gitWork/FreemarkIDE/src/test/resources/output/mybatis/mybatis.xml"),
				"UTF-8");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 自定义标签解析
		paramMap.put("mapper", new MapperDirective());
		paramMap.put("mybatisSelect", new SelectDirective());
		CommUtil.processTemplate("D:/soft/gitWork/FreemarkIDE/src/test/resources/template/mybatis", "mybatis.xml",
				"utf-8", paramMap, writer);
		System.out.println("恭喜，生成成功~~");
	}
}
