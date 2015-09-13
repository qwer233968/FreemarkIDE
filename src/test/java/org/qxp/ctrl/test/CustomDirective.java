package org.qxp.ctrl.test;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public class CustomDirective implements TemplateDirectiveModel{

	private static final String PARAM_NAME = "name";  
    private static final String PARAM_AGE = "age"; 
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body){
			throw new TemplateModelException("body null");
		}else{
			String name = getString(PARAM_NAME, params);
			Integer age = getInt(PARAM_AGE, params);
			//接收到参数之后可以根据做具体的操作，然后将数据再在页面中显示出来。  
            if(name!=null){  
                env.setVariable("output", ObjectWrapper.DEFAULT_WRAPPER.wrap("从ContentDirective解析类中获得的参数是："+name+", "));  
            }  
            if(age!=null){  
                env.setVariable("append", ObjectWrapper.DEFAULT_WRAPPER.wrap("年龄："+age));  
            }  
            Writer out = env.getOut();  
            out.write("从这里输出可以再页面看到具体的内容，就像document.writer写入操作一样。<br/>");  
            body.render(out);  
              
		}
	}
	
	 /** 
     * 获取String类型的参数的值 
     * @param paramName 
     * @param paramMap 
     * @return 
     * @throws TemplateModelException 
     */  
    public static String getString(String paramName, Map<String, TemplateModel> paramMap) throws TemplateModelException{  
        TemplateModel model = paramMap.get(paramName);  
        if(model == null){
        	System.out.println("null");
            return null;  
        }  
        if(model instanceof TemplateScalarModel){  
            return ((TemplateScalarModel)model).getAsString();  
        }else if (model instanceof TemplateNumberModel) {  
            return ((TemplateNumberModel)model).getAsNumber().toString();  
        }else{  
            throw new TemplateModelException(paramName);  
        }  
    }  

    /** 
     *  
     * 获得int类型的参数 
     * @param paramName 
     * @param paramMap 
     * @return 
     * @throws TemplateModelException  
     */  
    public static Integer getInt(String paramName, Map<String, TemplateModel> paramMap) throws TemplateModelException{  
        TemplateModel model = paramMap.get(paramName);  
        if(model==null){  
            return null;  
        }  
        if(model instanceof TemplateScalarModel){  
            String str = ((TemplateScalarModel)model).getAsString();  
            try {  
                return Integer.valueOf(str);  
            } catch (NumberFormatException e) {  
                throw new TemplateModelException(paramName);  
            }  
        }else if(model instanceof TemplateNumberModel){  
            return ((TemplateNumberModel)model).getAsNumber().intValue();  
        }else{  
            throw new TemplateModelException(paramName);  
        }  
    }  
}
