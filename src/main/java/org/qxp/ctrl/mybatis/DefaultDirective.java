package org.qxp.ctrl.mybatis;

import java.io.IOException;
import java.util.Map;

import org.qxp.ctrl.basic.CommUtil;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class DefaultDirective implements TemplateDirectiveModel{

	/*private static final String ID = "myid";
	private static final String RESULT_TYPE = "myresultType";
	private static final String SQL = "sql";
	private static final String NAMESPACE = "namespace";
	private static final String NOTES = "notes";*/
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			/*String id = CommUtil.getString(ID, params);
			String resultType = CommUtil.getString(RESULT_TYPE, params);
			String sql = CommUtil.getString(SQL, params);
			String namespace = CommUtil.getString(NAMESPACE, params);
			String notes = CommUtil.getString(NOTES, params);*/
			env.setVariable("id", ObjectWrapper.DEFAULT_WRAPPER.wrap("findUser"));
			env.setVariable("resultType", ObjectWrapper.DEFAULT_WRAPPER.wrap("java.lang.Integer"));
			env.setVariable("sql", ObjectWrapper.DEFAULT_WRAPPER.wrap("select * from tb_user"));
			env.setVariable("notes", ObjectWrapper.DEFAULT_WRAPPER.wrap("查询所有用户"));
		}
		
	}

}
