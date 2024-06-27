package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.Role;
import wp.zenny.model.UserRole;

public class UserRoleDAO {

	public Map<String,UserRole> mapUserRole;
	Connection conn;
	CallableStatement stmt;
	
	public UserRoleDAO() {
		conn = ConnectDatabase.getConnection();
		mapUserRole = getMapUserRole();
	}
	
	private Map<String,UserRole> getMapUserRole(){
		Map<String,UserRole> map = new HashMap<String,UserRole>();
		String userName;
		String roleId;
		Date dateCreate;
		String creator;
		Timestamp ts;
		try {
			stmt = conn.prepareCall("call spGetUserRole",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				userName = rs.getString(1);
				roleId = rs.getString(2);
				
				ts = rs.getTimestamp(3);
				dateCreate = ts==null? ts: new Date(ts.getTime());
				creator = rs.getString(4);
				UserRole UserRole = new UserRole(userName,roleId,dateCreate,creator);
				map.put(userName + roleId, UserRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		return map;
	}
	
	public List<Role> getListRole(String username) {
		List<Role> listRole = new ArrayList<Role>();
		RoleDAO roleDAO = new RoleDAO();
		Map<String,Role> mapRole = roleDAO.getMapRole();
		for (String key : this.getMapUserRole().keySet()) {
			if(key.startsWith(username)) {
				listRole.add(mapRole.get(this.getMapUserRole().get(key).getRoleId()));
			}
		}
		return listRole;
	}
	
	public boolean InsertUserRole(UserRole usRole) {
		boolean result = false;
		try {
			stmt = conn.prepareCall("call spInsertUserRole(?,?,?,?)");
			stmt.setString(1, usRole.getUsername());
			stmt.setInt(2, Integer.parseInt(usRole.getRoleId()));
			stmt.setDate(3, new java.sql.Date(usRole.getDateCreate().getTime()));
			stmt.setString(4, usRole.getCreator());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			stmt = null;
		}
		return result;
	}
	public boolean DeleteUserRole(String username) {
		boolean result = false;
		try {
			stmt = conn.prepareCall("call spDeleteUserRole(?)");
			stmt.setString(1, username);
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			stmt = null;
		}
		return result;
	}
	public static void main(String[] args) {
		UserRole us = new UserRole("15110300", "3", new Date(), "15110378");
		System.out.println(new UserRoleDAO().InsertUserRole(us));
	}

}
