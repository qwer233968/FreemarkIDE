package org.qxp.ctrl.mybatis;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SelectDirective implements TemplateDirectiveModel{

	private static final String ID = "findUser";
	private static final String RESULT_TYPE = "java.lang.Integer";
	private static final String SQL = "select * from tb_user";
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			env.setVariable("id", ObjectWrapper.DEFAULT_WRAPPER.wrap(ID));
			env.setVariable("resultType", ObjectWrapper.DEFAULT_WRAPPER.wrap(RESULT_TYPE));
			env.setVariable("sql", ObjectWrapper.DEFAULT_WRAPPER.wrap(SQL));
			body.render(env.getOut());
		}
	}

}
