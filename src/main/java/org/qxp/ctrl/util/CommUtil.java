package org.qxp.ctrl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.qxp.ctrl.mybatis.xml.po.MapProperties;

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
            File outpath =new File(outputPath);    
	          //如果文件夹不存在则创建    
	        if  (!outpath .exists()  && !outpath .isDirectory()) {       
	        	outpath.mkdir();    
	        }
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
   
    public static String resultMapToString(List<MapProperties> mapProperties){
		StringBuffer sb = new StringBuffer();
		for(MapProperties mp : mapProperties){
			sb.append(mp.getColumn()).append(",");
		}
		if(sb.length() >= 1){
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
	
	public static String paramsToString(List<MapProperties> mapProperties){
		StringBuffer sb = new StringBuffer();
		for(MapProperties mp : mapProperties){
			sb.append("#{").append(mp.getColumn()).append("},");
		}
		if(sb.length() >= 1){
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
	
	public static String pacToPath(String pacName){
		String[] pathArray = pacName.split("\\.");
		StringBuffer sb = new StringBuffer();
		for(String path : pathArray){
			sb.append(path).append("/");
		}
		return sb.toString();
	}
	
	public static Object columToJava(String colum, Class<?> original, Class<?> target) throws IllegalArgumentException, IllegalAccessException{
		Field[] originalfield = original.getDeclaredFields();
		Field[] targetfield = target.getDeclaredFields();
		Object targetval = colum;
        for (int i = 0; i < originalfield.length; i++) {
        	originalfield[i].setAccessible(true); 
            Object val = originalfield[i].get(original);
            if(val.equals(colum)){
            	targetval = targetfield[i].get(target);
            }
        }
        return targetval;
	}
}
