package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.Class;

public class ClassDAO {

	public Map<String,Class> mapClass = getMapClass();
	
	public ClassDAO() {}
	
	private Map<String,Class> getMapClass(){
		Map<String,Class> map = new HashMap<String,Class>();
		Connection conn = ConnectDatabase.getConnection();
		String classId;
		String className;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetClass",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				classId = rs.getString(1);
				className = rs.getString(2);
				Class Class = new Class(classId,className);
				map.put(classId, Class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String getClassID(String Classname) {
		for(String Classid: mapClass.keySet()) {
			Class Class = mapClass.get(Classid);
			if(Class.getClassName().equals(Classname)) {
				return Class.getClassId();
			}
		}
		return null;
	}
	

}
