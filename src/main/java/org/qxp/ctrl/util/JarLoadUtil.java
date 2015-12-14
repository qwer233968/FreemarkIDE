package org.qxp.ctrl.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoadUtil {

	private static URLClassLoader loader = null;  
	
	public static URLClassLoader getURLClassLoader(String[] fileNames){     
        if(loader == null){       
            if(fileNames != null && fileNames.length > 0){      
                URL urls[] = new URL[fileNames.length];  
                for(int i = 0;i < fileNames.length;i++){  
                    try {  
                        urls[i] = new URL(fileNames[i]);  
                    } catch (MalformedURLException e) {  
                        throw new RuntimeException("加载jar文件出错！",e);  
                    }  
                }  
                loader = new URLClassLoader(urls);  
            }  
        }  
        return loader;  
    }  
}
