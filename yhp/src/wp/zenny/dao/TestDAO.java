package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.Subject;
import wp.zenny.model.Test;

public class TestDAO {

	public Map<String,Test> mapTest = getMapTest();
	public Map<String,Test> mapTopTest = getMapTopTest();
	CallableStatement stmt;
	Connection conn;
	public TestDAO() {
		conn = ConnectDatabase.getConnection();
	}
	
	private Map<String,Test> getMapTest(){
		Map<String,Test> map = new HashMap<String,Test>();
		 if(conn == null) conn = ConnectDatabase.getConnection();
		String testId;
		String testName;
		Date dateCreate;
		String creator;
		String subId;
		Timestamp ts;
		try {
			stmt = conn.prepareCall("call spGetTest",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				testId = rs.getString(1);
				testName = rs.getString(2);
				 ts = rs.getTimestamp(3);
				dateCreate = ts==null?ts: new Date(ts.getTime());
				creator = rs.getString(4);
				subId = rs.getString(5);
				Test Test = new Test(testId,testName,dateCreate,creator,subId);
				map.put(testId, Test);
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
	
	public boolean InsertTest(Test test) {
		boolean result = false;
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			 if(conn == null) conn = ConnectDatabase.getConnection();
			stmt = conn.prepareCall("call spInsertTest(?,?,?,?,?)");
			stmt.setString(1, test.getTestId());
			stmt.setString(2, test.getTestName());
			stmt.setString(3,s.format(new java.sql.Date(test.getDateCreate().getTime())));
			stmt.setString(4, test.getCreator());
			stmt.setString(5, test.getSubId());
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
	
	private Map<String,Test> getMapTopTest(){
		Map<String,Test> map = new HashMap<String,Test>();
		String testId;
		String testName;
		Date dateCreate;
		String creator;
		String subId;
		Timestamp ts;
		try {
			 if(conn == null) conn = ConnectDatabase.getConnection();
			stmt = conn.prepareCall("call spGetTopTest",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				testId = rs.getString(1);
				testName = rs.getString(2);
				 ts = (Timestamp)rs.getTimestamp(3);
				 dateCreate = ts==null?ts: new Date(ts.getTime());
				dateCreate = new Date(ts.getTime());
				creator = rs.getString(4);
				subId = rs.getString(5);
				Test Test = new Test(testId,testName,dateCreate,creator,subId);
				map.put(testId, Test);
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
	public boolean DeleteTest(String testid) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spDeleteTest(?)");
			stmt.setString(1, testid);
			
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public Map<String,Test> getTestForEachSub(String subId) {
		Map<String,Test> map = new HashMap<String,Test>();
		for(Test t : new TestDAO().mapTest.values()) {
			if(t.getSubId().equals(subId)) {
				map.put(t.getTestId(), t);
			}
		}
		return map;
	}
	public Subject getSubject(Object test) {
		Subject s;
		if(test == null)
			return null;
		Test ts;
		try {
			ts = (Test)test;
		s = new SubjectDAO().mapSubject.get(mapTest.get(ts.getTestId().toString()).getSubId());
		} catch(NullPointerException ne) {
			ne.printStackTrace();
			s = null;
		}catch(Exception e) {
			e.printStackTrace();
			s=null;
			}
		
		return s;
	}
	public static void main(String[] args) {
	}
}
