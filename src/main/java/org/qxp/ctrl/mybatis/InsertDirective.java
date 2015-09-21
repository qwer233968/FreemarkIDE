package org.qxp.ctrl.mybatis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.mybatis.po.Insert;
import org.qxp.ctrl.util.Log;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class InsertDirective implements TemplateDirectiveModel{
	
	private List<Insert> list;
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			list = formatInsertModel(list);
			env.setVariable("insertList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			body.render(env.getOut());
		}
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

	public List<Insert> getList() {
		return list;
	}

	public void setList(List<Insert> list) {
		this.list = list;
	}

	private static final Logger logger= Log.getLogger(InsertDirective.class);
}
