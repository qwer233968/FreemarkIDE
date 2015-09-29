package org.qxp.ctrl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
    
    /** 
     * @param templatePath 模板文件存放目录  
     * @param templateName 模板文件名称  
     * @param root 数据模型根对象 
     * @param templateEncoding 模板文件的编码方式 
     * @param out 输出流 
     */  
    @SuppressWarnings("deprecation")
	public static void processTemplate(String templatePath, String templateName, String templateEncoding, Map<?,?> root, String outputPath, String outputFile){  
        try {  
            Configuration config=new Configuration();  
            File file=new File(templatePath);  
            //设置要解析的模板所在的目录，并加载模板文件  
            config.setDirectoryForTemplateLoading(file);  
            //设置包装器，并将对象包装为数据模型  
            config.setObjectWrapper(new DefaultObjectWrapper());  
              
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
            Template template=config.getTemplate(templateName,templateEncoding);  
            
            //合并数据模型与模板  
            Writer writer = new OutputStreamWriter(
    				new FileOutputStream(outputPath + "/" + outputFile),"UTF-8");
            template.process(root, writer);  
            writer.flush();  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }  
}
