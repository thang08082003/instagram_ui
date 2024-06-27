package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.Role;

public class RoleDAO {

	private Map<String,Role> mapRole;
	
	public RoleDAO() {}
	
	public Map<String,Role> getMapRole(){
		Map<String,Role> map = new HashMap<String,Role>();
		Connection conn = ConnectDatabase.getConnection();
		String roleId;
		String roleName;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetRole",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				roleId = rs.getString(1);
				roleName = rs.getString(2);
				Role role = new Role(roleId,roleName);
				map.put(roleId, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String getRoleID(String rolename) {
		for(String roleid: mapRole.keySet()) {
			Role role = mapRole.get(roleid);
			if(role.getRoleName().equals(rolename)) {
				return role.getRoleId();
			}
		}
		return null;
	}
}
