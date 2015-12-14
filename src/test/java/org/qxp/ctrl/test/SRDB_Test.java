package org.qxp.ctrl.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.qxp.ctrl.entry.EntryFile;
import org.qxp.ctrl.entry.EntryProperties;
import org.qxp.ctrl.io.entry.WriterEntry;
import org.qxp.ctrl.io.mybatis.WriterMapper;
import org.qxp.ctrl.mybatis.xml.po.Insert;
import org.qxp.ctrl.mybatis.xml.po.MapProperties;
import org.qxp.ctrl.mybatis.xml.po.Mapper;
import org.qxp.ctrl.mybatis.xml.po.ResultMap;
import org.qxp.ctrl.mybatis.xml.po.Select;
import org.qxp.ctrl.mybatis.xml.po.Update;
import org.qxp.ctrl.mysql.DatabaseMetaDateApplication;

public class SRDB_Test {

	@Test
	public void srdb_mybatis_test(){
		String[] jarfiles = {"file://D:/maven/repo/org/srdbsql/srdbsql.Driver/1.0/srdbsql.Driver-1.0.jar"};
		String url = "jdbc:srdbsql://10.168.220.95:1975/ceshidb";
		String user = "dba";
		String pwd = "dba";
		String driver = "org.srdbsql.Driver";
		try {
			DatabaseMetaDateApplication dm = new DatabaseMetaDateApplication(jarfiles, driver, url, user, pwd);
			dm.setSpecialDB(true);
			createEntry(dm);
			dm.colseCon();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createEntry(DatabaseMetaDateApplication dm){
		List<EntryFile> list = new ArrayList<EntryFile>();
		List<String> tableNamelist = dm.getAllTableList("public");
		for(String tableName : tableNamelist){
			Map<String,String> map = dm.getTableColumns(null, tableName);
			List<EntryProperties> properties  = getEntryProperties(map);
			EntryFile ef = new EntryFile();
			ef.setFileName(tableName);
			ef.setPackageName("com.test.entry");
			ef.setSuffix("java");
			ef.setProperties(properties);
			list.add(ef);
		}
		WriterEntry we = new WriterEntry();
		we.writerTemplate(list);
	}
	public List<EntryProperties> getEntryProperties(Map<String,String> map){
		List<EntryProperties> properties = new ArrayList<EntryProperties>();
		Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry<String,String> entry = entries.next();  
		    EntryProperties ep = new EntryProperties();
		    ep.setProName(entry.getKey());
		    ep.setProType(entry.getValue());
		    properties.add(ep);
		}  
		return properties;
	}
	
	public void createMapper(DatabaseMetaDateApplication dm){
		List<Mapper> list = new ArrayList<Mapper>();
		List<String> tableNamelist = dm.getAllTableList("public");
		for(String tableName : tableNamelist){
			Map<String,String> map = dm.getTableColumns(null, tableName);
			Mapper mapper = new Mapper();
			
			ResultMap remap = new ResultMap();
			List<MapProperties> mapProperties =new ArrayList<MapProperties>();
			Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();  
			while (entries.hasNext()) {  
			    Map.Entry<String,String> entry = entries.next();  
			    MapProperties mp = new MapProperties();
			    mp.setColumn(entry.getKey());
			    mp.setProperty(entry.getValue());
			    mapProperties.add(mp);
			}  
			remap.setId(tableName + "Map");
			remap.setType("com.test.entry." + tableName);
			remap.setMapProperties(mapProperties);
			
			
			List<Select> selectList = new ArrayList<Select>();
			Select sel = new Select();
			sel.setId("find"+tableName);
			sel.setResultMap(tableName + "Map");
			sel.setSql("select * from tb_user1");
			sel.setFlushCache("false");
			
			selectList.add(sel);
			List<Insert> insertList = new ArrayList<Insert>();
			Insert ins = new Insert();
			
			insertList.add(ins);
			
			mapper.setMap(remap);
			mapper.setNamespace("com.test.dao." + tableName);
			mapper.setInsertList(insertList);
			mapper.setSelectList(selectList);
			mapper.setFileName(tableName+"mapper");
			mapper.setSuffix("xml");
			list.add(mapper);
		}
		WriterMapper wm = new WriterMapper();
		wm.writerTemplate(list);
	}
	
	public void resultMapToString(ResultMap remap){
		
	}
}
