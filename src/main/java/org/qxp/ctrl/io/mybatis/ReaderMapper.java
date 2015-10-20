package org.qxp.ctrl.io.mybatis;

import org.qxp.ctrl.io.BasicReader;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.util.IOUtil;

import com.thoughtworks.xstream.XStream;

public class ReaderMapper implements BasicReader{

	public Object readerConfig(String filename) {
		String xml = IOUtil.ReadFile(filename);
		System.out.println(xml);
		XStream xstream = new XStream();
		xstream.alias("Mapper", Mapper.class);
		Mapper mapper = (Mapper) xstream.fromXML(xml);
		return mapper;
	}

}
