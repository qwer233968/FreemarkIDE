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
import org.qxp.ctrl.mybatis.xml.InsertDirective;
import org.qxp.ctrl.mybatis.xml.MapperDirective;
import org.qxp.ctrl.mybatis.xml.SelectDirective;
import org.qxp.ctrl.mybatis.xml.UpdateDirective;
import org.qxp.ctrl.mybatis.xml.po.Insert;
import org.qxp.ctrl.mybatis.xml.po.Select;
import org.qxp.ctrl.mybatis.xml.po.Update;
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
		
		List<Select> list1 = createSelectList();
		List<Insert> list2 = createInsertList();
		List<Update> list3 = createUpdateList();
		// 自定义标签解析
		paramMap.put("mapper", new MapperDirective());
		paramMap.put("mybatisSelect", new SelectDirective(list1));
		paramMap.put("mybatisInsert", new InsertDirective(list2));
		paramMap.put("mybatisUpdate", new UpdateDirective(list3));
		CommUtil.processTemplate(RELATIVE_PATH + "/src/main/resources/template/mybatis", "mybatis.xml",
				"utf-8", paramMap, writer);
		logger.debug("恭喜，生成成功~~");
	}
	
	public List<Select> createSelectList(){
		List<Select> list = new ArrayList<Select>();
		Select s1 = new Select();
		s1.setId("findUser1");
		s1.setResultType("java.lang.Integer1");
		s1.setSql("select * from tb_user1");
		s1.setFlushCache("true1");
		Select s2 = new Select();
		s2.setId("findUser2");
		s2.setResultType("java.lang.Integer2");
		s2.setSql("select * from tb_user2");
		s2.setFlushCache("true2");
		Select s3 = new Select();
		s3.setId("findUser3");
		s3.setResultType("java.lang.Integer3");
		s3.setSql("select * from tb_user3");
		s3.setFlushCache("true3");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		return list;
	}
	
	public List<Insert> createInsertList(){
		List<Insert> list = new ArrayList<Insert>();
		Insert i1 = new Insert();
		i1.setId("addUser1");
		i1.setSql("insert into user(1)");
		i1.setFlushCache("true1");
		Insert i2 = new Insert();
		i2.setId("addUser2");
		i2.setSql("insert into user(2)");
		i2.setFlushCache("true2");
		Insert i3 = new Insert();
		i3.setId("addUser3");
		i3.setSql("insert into user(3)");
		i3.setFlushCache("true3");
		list.add(i1);
		list.add(i2);
		list.add(i3);
		return list;
	}
	
	public List<Update> createUpdateList(){
		List<Update> list = new ArrayList<Update>();
		Update u1 = new Update();
		u1.setId("addUser1");
		u1.setSql("insert into user(1)");
		u1.setFlushCache("true1");
		Update u2 = new Update();
		u2.setId("addUser2");
		u2.setSql("insert into user(2)");
		u2.setFlushCache("true2");
		Update u3 = new Update();
		u3.setId("addUser3");
		u3.setSql("insert into user(3)");
		u3.setFlushCache("true3");
		list.add(u1);
		list.add(u2);
		list.add(u3);
		return list;
	}
	
	private static final Logger logger= Log.getLogger(MybatisTest.class);
}
