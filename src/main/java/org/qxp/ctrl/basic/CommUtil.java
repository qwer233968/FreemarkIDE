package org.qxp.ctrl.basic;

import java.util.Map;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public class CommUtil {

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
}
