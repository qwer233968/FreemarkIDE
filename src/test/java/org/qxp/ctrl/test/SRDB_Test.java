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
import org.qxp.ctrl.mysql.DatabaseMetaDateApplication;
import org.qxp.ctrl.util.CommUtil;
import org.qxp.ctrl.util.ImportUtil;
import org.qxp.ctrl.util.JAVAConstant;
import org.qxp.ctrl.util.SRDBConstant;

public class SRDB_Test {

	@Test
	public void srdb_mybatis_test(){
		String[] jarfiles = {"file://D:/maven/repo/org/srdbsql/srdbsql.Driver/1.0/srdbsql.Driver-1.0.jar"};
		String url = "jdbc:srdbsql://10.168.220.95:1975/ceshidb";
		String user = "dba";
		String pwd = "dba";
		String driver = "org.srdbsql.Driver";
		
		String entry_path = "E:/test_channel/djr_channel_model/src/main/java";
		String entry_pac = "com.xinhe99.channel.testmodel";
		String mapper_path = "E:/test_channel/djr_channel_dao/src/main/resources/test-mappers";
		
		try {
			DatabaseMetaDateApplication dm = new DatabaseMetaDateApplication(jarfiles, driver, url, user, pwd);
			dm.setSpecialDB(true);
			createEntry(dm, entry_path, entry_pac);
			createMapper(dm, mapper_path, entry_pac);
			dm.colseCon();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createEntry(DatabaseMetaDateApplication dm, String entry_path, String entry_pac) throws IllegalArgumentException, IllegalAccessException{
		List<EntryFile> list = new ArrayList<EntryFile>();
		List<String> tableNamelist = dm.getAllTableList("public");
		for(String tableName : tableNamelist){
			Map<String,String> map = dm.getTableColumns(null, tableName);
			List<EntryProperties> properties  = getEntryProperties(map);
			EntryFile ef = new EntryFile();
			ef.setFileName(tableName);
			ef.setPackageName(entry_pac);
			ef.setSuffix("java");
			ef.setProperties(properties);
			ef.setImportList(ImportUtil.getEntryImport(properties));
			list.add(ef);
		}
		String path = entry_path + "/" + CommUtil.pacToPath(entry_pac);
		WriterEntry we = new WriterEntry();
		we.writerTemplate(list, path);
	}
	public List<EntryProperties> getEntryProperties(Map<String,String> map) throws IllegalArgumentException, IllegalAccessException{
		List<EntryProperties> properties = new ArrayList<EntryProperties>();
		Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry<String,String> entry = entries.next();  
		    EntryProperties ep = new EntryProperties();
		    ep.setProName(entry.getKey());
		    Object obj = CommUtil.columToJava(entry.getValue(), SRDBConstant.class, JAVAConstant.class);
		    ep.setProType(obj.toString());
		    properties.add(ep);
		}  
		return properties;
	}
	
	public void createMapper(DatabaseMetaDateApplication dm, String mapper_path, String entry_pac){
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
			    mp.setProperty(entry.getKey());
			    mapProperties.add(mp);
			}  
			remap.setId(tableName + "Map");
			remap.setType(entry_pac + "." + tableName);
			remap.setMapProperties(mapProperties);
			
			String colum_str = CommUtil.resultMapToString(mapProperties);
			String params_str =CommUtil.paramsToString(mapProperties);
			List<Select> selectList = new ArrayList<Select>();
			Select sel = new Select();
			sel.setId("find"+tableName);
			sel.setResultMap(tableName + "Map");
			sel.setSql("select " + colum_str + " from " + tableName);
			sel.setFlushCache("false");
			selectList.add(sel);
			
			List<Insert> insertList = new ArrayList<Insert>();
			Insert ins = new Insert();
			ins.setParameterType("java.util.Map");
			ins.setId("add"+tableName);
			ins.setSql("insert into " + tableName + " values(" + params_str + ")");
			ins.setFlushCache("false");
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
		wm.writerTemplate(list, mapper_path);
	}
	
	
}
