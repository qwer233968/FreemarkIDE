package org.qxp.ctrl.mybatis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qxp.ctrl.mybatis.po.Select;
import org.qxp.ctrl.util.Log;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SelectDirective implements TemplateDirectiveModel{

	private List<Select> list;
	
	public SelectDirective(List<Select> list){
		this.list = list;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body is null");
		}if(null == list){
			throw new TemplateModelException("select list is null");
		}else{
			list = formatSelectModel(list);
			env.setVariable("selectList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			body.render(env.getOut());
		}
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
	public List<Select> getList() {
		return list;
	}

	public void setList(List<Select> list) {
		this.list = list;
	}

	private static final Logger logger= Log.getLogger(SelectDirective.class);
}
