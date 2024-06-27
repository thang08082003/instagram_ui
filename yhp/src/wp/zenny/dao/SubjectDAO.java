package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.Subject;

public class SubjectDAO {

	public Map<String,Subject> mapSubject = getMapSubject();
	
	public SubjectDAO() {}
	
	private Map<String,Subject> getMapSubject(){
		Map<String,Subject> map = new HashMap<String,Subject>();
		Connection conn = ConnectDatabase.getConnection();
		String subId;
		String subName;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetSubject",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				subId = rs.getString(1);
				subName = rs.getString(2);
				Subject Subject = new Subject(subId,subName);
				map.put(subId, Subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public String getSubName(String testId) {
		try {
			String SubId = new TestDAO().mapTest.get(testId).getSubId();
			if(SubId != null) {
				Subject subj =  new SubjectDAO().mapSubject.get(SubId);
				if(subj != null) {
					return subj.getSubName();
				}
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
