package org.qxp.ctrl.io.mybatis;

import org.qxp.ctrl.io.BasicReader;
import org.qxp.ctrl.mybatis.dao.po.DaoFile;
import org.qxp.ctrl.util.IOUtil;

import com.thoughtworks.xstream.XStream;

public class ReaderDao implements BasicReader{

	public Object readerConfig(String filename) {
		String xml = IOUtil.ReadFile(filename);
		System.out.println(xml);
		XStream xstream = new XStream();
		xstream.alias("DaoFile", DaoFile.class);
		DaoFile daoFile = (DaoFile) xstream.fromXML(xml);
		return daoFile;
	}

}
