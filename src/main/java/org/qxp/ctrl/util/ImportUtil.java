package org.qxp.ctrl.util;

import java.util.ArrayList;
import java.util.List;

import org.qxp.ctrl.entry.EntryProperties;
import org.qxp.ctrl.mybatis.dao.po.Import;

public class ImportUtil {

	public static List<Import> getEntryImport(List<EntryProperties> properties){
		List<Import> list = new ArrayList<Import>();
		for(EntryProperties ep : properties){
			String type = ep.getProType();
			if("Date".equalsIgnoreCase(type)){
				Import im = new Import();
				im.setName("java.util.Date");
				list.add(im);
			}
		}
		return list;
	}
}
