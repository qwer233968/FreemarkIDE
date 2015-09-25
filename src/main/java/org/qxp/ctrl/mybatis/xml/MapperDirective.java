package org.qxp.ctrl.mybatis.xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.mybatis.xml.po.Insert;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.mybatis.xml.po.Select;
import org.qxp.ctrl.mybatis.xml.po.Update;
import org.qxp.ctrl.util.Log;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class MapperDirective implements TemplateDirectiveModel{

	private Mapper mapper;
	public MapperDirective(Mapper m){
		this.mapper = m;
	}
	
	private static final String NAMESPACE = "org.qxp.mapper";
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else if(null == mapper){
			throw new TemplateModelException("mapper null");
		}else{
			env.setVariable("namespace", ObjectWrapper.DEFAULT_WRAPPER.wrap(NAMESPACE));
			List<Insert> insertList = mapper.getInsertList();
			insertList = formatInsertModel(insertList);
			env.setVariable("insertList", ObjectWrapper.DEFAULT_WRAPPER.wrap(insertList));
			List<Select> selectList = mapper.getSelectList();
			selectList = formatSelectModel(selectList);
			env.setVariable("selectList", ObjectWrapper.DEFAULT_WRAPPER.wrap(selectList));
			List<Update> updateList = mapper.getUpdateList();
			updateList = formatUpdateModel(updateList);
			env.setVariable("updateList", ObjectWrapper.DEFAULT_WRAPPER.wrap(updateList));
			body.render(env.getOut());
		}
	}

	private List<Update> formatUpdateModel(List<Update> list){
		 Iterator<Update> iter = list.iterator();  
		 while(iter.hasNext()){  
			 Update u = iter.next();
			 String id = u.getId();
			 String sql = u.getSql();
			 if(null == id || null == sql){
				logger.warn("UpdateModel Must include id,sql");
				 iter.remove();  
			 }
		 }
		 return list;
	} 
	
	private List<Insert> formatInsertModel(List<Insert> list){
		 Iterator<Insert> iter = list.iterator();  
		 while(iter.hasNext()){  
			 Insert i = iter.next();
			 String id = i.getId();
			 String sql = i.getSql();
			 if(null == id || null == sql){
				logger.warn("InsertModel Must include id,sql");
				 iter.remove();  
			 }
		 }
		 return list;
	} 

	private List<Select> formatSelectModel(List<Select> list){
		 Iterator<Select> iter = list.iterator();  
		 while(iter.hasNext()){  
			 Select s = iter.next();
			 String id = s.getId();
			 String resultType = s.getResultType();
			 String resultMap = s.getResultMap();
			 String sql = s.getSql();
			 if(null == id || null == sql || (null == resultType && null == resultMap) || (null != resultType && null != resultMap)){
				logger.warn("SelectModel Must include id,resultType/resultMap,sql");
				 iter.remove();  
			 }
		 }
		 return list;
	}
	
	private static final Logger logger= Log.getLogger(MapperDirective.class);
}
