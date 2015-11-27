package org.qxp.ctrl.io.mybatis;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qxp.ctrl.config.SystemConfig;
import org.qxp.ctrl.io.BasicWriter;
import org.qxp.ctrl.log.Logger;
import org.qxp.ctrl.mybatis.xml.MapperDirective;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.util.CommUtil;

import com.thoughtworks.xstream.XStream;

public class WriterMapper implements BasicWriter{

	public boolean writerTemplate(List<?> list) {
		try{
			String readPath = SystemConfig.RELATIVE_PATH + "/src/main/resources/template/mybatis";
			String readFile = "mapper.xml";
			String outputPath = SystemConfig.PROJECT_OUTPUT + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
			String charset = "utf-8";
			logger.debug(outputPath);
			
			for(Object mapper : list){
				Mapper m = (Mapper) mapper;
				String outputFile = m.getFileName() + "." + m.getSuffix();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mapper", new MapperDirective(m));
				CommUtil.processTemplate(readPath, readFile,
						charset, paramMap, outputPath, outputFile);
				logger.debug("mybatisXml,生成成功~~");
			}
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("resource")
	public boolean writerConfig(List<?> list) {
		try{
			String outputPath = SystemConfig.PROJECT_CONFIG_OUTPUT + "/" + SystemConfig.PROJECT_CONFIG_OUTPUT_NAME + "/" + SystemConfig.MYBATIS_PACKAGE_NAME;
			logger.debug(outputPath);
			for(Object mapper : list){
				Mapper m = (Mapper) mapper;
				String outputFile = m.getFileName() + "-config.xml";
				XStream xstream = new XStream();
				xstream.alias("Mapper", Mapper.class);
				String xml = xstream.toXML(mapper);
				logger.debug(xml);
				File file = new File(outputPath + "/" + outputFile);
				if(!file.exists()){
					file.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				FileChannel fc = fos.getChannel();
				ByteBuffer bb = ByteBuffer.allocate(xml.length());
				bb.put(xml.getBytes());
				bb.flip();
				fc.write(bb);
				logger.debug("mybatisXmlConfig,生成成功~~");
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private static final Logger logger = Logger.getLogger(WriterMapper.class);

}
