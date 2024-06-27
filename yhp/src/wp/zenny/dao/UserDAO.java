package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import wp.zenny.model.ClassUser;
import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.User;
import wp.zenny.model.UserRole;

public class UserDAO {
	Connection conn;
	CallableStatement stmt;
	public Map<String,User> mapUser;

	public UserDAO() {
		conn = ConnectDatabase.getConnection();
		mapUser = getMapUser();
	}
	
	public Map<String, User> getMapUser() {
		mapUser = getAllUser();
		return mapUser;
	}
	
	private Map<String,User> getAllUser(){
		Map<String,User> map = new HashMap<String,User>();
		String username;
		String password;
		String fullname;
		String gender;
		Date birthday;
		String phonenumber;
		String avatar;
		try {
			stmt = conn.prepareCall("call spGetUser",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				username = rs.getString(1);
				password = rs.getString(2);
				fullname = rs.getString(3);
				gender = rs.getString(4);
				try {
					birthday = (Date)rs.getDate(5);
					} catch(Exception e) {birthday = null;}
				phonenumber = rs.getString(6);
				avatar = rs.getString(7);
				User user = new User(username,password,fullname,gender,birthday,phonenumber,avatar);
				map.put(username, user);
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
	
	public Map<String,User> getMapUser(String roleId){
		Map<String,User> map = new HashMap<String,User>();
		for(User us : mapUser.values()) {
			for(UserRole usRole : new UserRoleDAO().mapUserRole.values()) {
				if(usRole.getUsername().equals(us.getUsername()) && usRole.getRoleId().equals(roleId)) {
					map.put(us.getUsername(), us);
				}
			}
		}
		return map;
	}
	
	public boolean DeleteUser(String username) {
		boolean result = false;
		try {
			stmt = conn.prepareCall("call spDeleteUser(?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, username);
			stmt.executeUpdate();
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		return result;
	}
	
	public boolean UpdateUser(User us) {
		boolean result = false;
		try {
			stmt = conn.prepareCall("call spUpdateUser(?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, us.getUsername());
			stmt.setString(2, us.getPassword());
			stmt.setString(3, us.getFullname());
			stmt.setInt(4, us.getGenderInt());
			stmt.setDate(5, new java.sql.Date(us.getBirthday().getTime()));
			stmt.setString(6, us.getPhonenumber());
			stmt.setString(7, us.getAvatar());
			stmt.executeUpdate();
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		return result;
	}
	public boolean InsertUser(User us) {
		boolean result = false;
		try {
			stmt = conn.prepareCall("call spInsertUser(?,?,?,?,?,?,?)");
			stmt.setString(1, us.getUsername());
			stmt.setString(2, us.getPassword());
			stmt.setString(3, us.getFullname());
			stmt.setInt(4, us.getGenderInt());
			stmt.setDate(5, new java.sql.Date(us.getBirthday().getTime()));
			stmt.setString(6, us.getPhonenumber());
			stmt.setString(7, us.getAvatar());
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

	public boolean checkLogin(String username,String password, String roleid) {
			if(mapUser.containsKey(username)) {
				String pwd = new MD5Encryptor().encrypt(password);
				if(mapUser.get(username).getPassword().equals(pwd)) {
					if(roleid != null) {
						if(new UserRoleDAO().mapUserRole.containsKey(username + roleid))
						{
							return true;
						}
					}
				}
			}
		return false;
	}
	
	public Map<String,wp.zenny.model.Class> getClass(String username){
		Map<String,wp.zenny.model.Class> map = new HashMap<String,wp.zenny.model.Class>();
		for(ClassUser c : new ClassUserDAO().mapClassUser.values()) {
			if(c.getUsername().equals(username)) {
				map.put(c.getClassId(),new ClassDAO().mapClass.get(c.getClassId()));
			}
		}
		return map;
	}
	public static void main(String[] args) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date;
		try {
			date = s.parse("16/08/1997");
		} catch (ParseException e) {
			date = new Date();
		}
		User us = new User("123444", "YZENNY", "ZENNY97", "1", date, "01682075449", "avt.jpg");
		System.out.println(new UserDAO().UpdateUser(us));
		
	}
}
