package org.qxp.ctrl.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.qxp.ctrl.mybatis.MapperDirective;
import org.qxp.ctrl.mybatis.SelectDirective;
import org.qxp.ctrl.mybatis.po.Select;
import org.qxp.ctrl.util.CommUtil;
import org.qxp.ctrl.util.Log;

import freemarker.template.TemplateException;

public class MybatisTest {

	String RELATIVE_PATH = System.getProperty("user.dir"); 
	@Test
	public void selectTest() throws IOException, TemplateException {
		Writer writer = new OutputStreamWriter(
				new FileOutputStream(RELATIVE_PATH + "/src/test/resources/output/mybatis/mybatis.xml"),
				"UTF-8");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Select> list = new ArrayList<Select>();
		Select s1 = new Select();
		s1.setId("findUser");
		s1.setResultType("java.lang.Integer");
		s1.setSql("select * from tb_user");
		s1.setFlushCache("true");
		list.add(s1);
		// 自定义标签解析
		paramMap.put("mapper", new MapperDirective());
		paramMap.put("mybatisSelect", new SelectDirective(list));
		CommUtil.processTemplate(RELATIVE_PATH + "/src/test/resources/template/mybatis", "mybatis.xml",
				"utf-8", paramMap, writer);
		logger.debug("恭喜，生成成功~~");
	}
	
	private static final Logger logger= Log.getLogger(MybatisTest.class);
}
