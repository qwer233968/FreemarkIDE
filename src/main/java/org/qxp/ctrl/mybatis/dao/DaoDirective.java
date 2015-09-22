package org.qxp.ctrl.mybatis.dao;

import java.io.IOException;
import java.util.Map;

import org.qxp.ctrl.mybatis.dao.po.DaoFile;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class DaoDirective implements TemplateDirectiveModel{

	private DaoFile file;
	
	public DaoDirective(DaoFile dfile){
		this.file = dfile;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body is null");
		}
		if(null == file){
			throw new TemplateModelException("DaoFile is null");
		}
		env.setVariable("file", ObjectWrapper.DEFAULT_WRAPPER.wrap(file));
		body.render(env.getOut());
	}

}
