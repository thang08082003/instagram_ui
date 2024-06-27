package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.ClassUser;

public class ClassUserDAO {

	public Map<String,ClassUser> mapClassUser = getMapClassUser();
	
	public ClassUserDAO() {}
	
	private Map<String,ClassUser> getMapClassUser(){
		Map<String,ClassUser> map = new HashMap<String,ClassUser>();
		Connection conn = ConnectDatabase.getConnection();
		String classId;
		String username;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetClassUser",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				classId = rs.getString(1);
				username = rs.getString(2);
				ClassUser ClassUser = new ClassUser(classId,username);
				map.put(classId+username, ClassUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	

}
