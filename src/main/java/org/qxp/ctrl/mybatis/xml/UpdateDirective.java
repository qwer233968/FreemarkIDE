package org.qxp.ctrl.mybatis.xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.mybatis.xml.po.Update;
import org.qxp.ctrl.util.Log;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UpdateDirective implements TemplateDirectiveModel{
	
	private List<Update> list;
	
	public UpdateDirective(List<Update> list){
		this.list = list;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			list = formatUpdateModel(list);
			env.setVariable("updateList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
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

	public List<Update> getList() {
		return list;
	}

	public void setList(List<Update> list) {
		this.list = list;
	}

	private static final Logger logger= Log.getLogger(UpdateDirective.class);
}
