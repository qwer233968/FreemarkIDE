package org.qxp.ctrl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.qxp.ctrl.entry.EntryFile;
import org.qxp.ctrl.entry.EntryProperties;
import org.qxp.ctrl.io.entry.WriterEntry;

public class EntryTest {

	@Test
	public void test(){
		List<EntryFile> list = createList();
		WriterEntry we = new WriterEntry();
		we.writerTemplate(list);
	}
	
	public List<EntryFile> createList(){
		List<EntryProperties> pro_list = new ArrayList<EntryProperties>();
		EntryProperties ep = new EntryProperties();
		ep.setProName("name");
		ep.setProType("String");
		pro_list.add(ep);
		List<EntryFile> list = new ArrayList<EntryFile>();
		EntryFile entry1 = new EntryFile();
		entry1.setFileName("UserEntry");
		entry1.setSuffix("java");
		entry1.setPackageName("com.mq.entry");
		entry1.setProperties(pro_list);
		EntryFile entry2 = new EntryFile();
		entry2.setFileName("UserEntry");
		entry2.setSuffix("java");
		entry2.setPackageName("");
		entry2.setProperties(pro_list);
		list.add(entry1);
		list.add(entry2);
		return list;
	}
}
