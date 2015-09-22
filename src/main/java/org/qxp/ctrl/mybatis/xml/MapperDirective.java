package org.qxp.ctrl.mybatis.xml;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class MapperDirective implements TemplateDirectiveModel{

	private static final String NAMESPACE = "org.qxp.mapper";
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			env.setVariable("namespace", ObjectWrapper.DEFAULT_WRAPPER.wrap(NAMESPACE));
			body.render(env.getOut());
		}
	}

}
