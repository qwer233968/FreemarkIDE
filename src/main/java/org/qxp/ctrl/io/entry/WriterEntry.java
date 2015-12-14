package org.qxp.ctrl.io.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.entry.EntryFile;
import org.qxp.ctrl.entry.directive.EntryDirective;
import org.qxp.ctrl.io.BasicWriter;
import org.qxp.ctrl.log.Logger;
import org.qxp.ctrl.util.CommUtil;

public class WriterEntry implements BasicWriter{

	public boolean writerTemplate(List<?> list) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/entry";
			String readFile = "ClassEntry";
			String outputPath = SystemConfig.PROJECT_OUTPUT + "/" + SystemConfig.ENTRY_PACKAGE_NAME;
			String charset = "utf-8";
			logger.debug(outputPath);
			
			for(Object o : list){
				EntryFile entryFile = (EntryFile) o;
				String outputFile = entryFile.getFileName() + "." + entryFile.getSuffix();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("entry", new EntryDirective(entryFile));
				CommUtil.processTemplate(readPath, readFile,
						charset, paramMap, outputPath, outputFile);
				logger.debug("entry,生成成功~~");
			}
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean writerConfig(List<?> list) {
		return false;
	}
	
	private static final Logger logger = Logger.getLogger(WriterEntry.class);

}
