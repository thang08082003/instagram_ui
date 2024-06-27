package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wp.zenny.model.ClassExam;
import wp.zenny.model.ConnectDatabase;

public class ClassExamDAO {

	public Map<String,ClassExam> mapClassExam = getMapClassExam();
	
	public ClassExamDAO() {}
	
	private Map<String,ClassExam> getMapClassExam(){
		Map<String,ClassExam> map = new HashMap<String,ClassExam>();
		Connection conn = ConnectDatabase.getConnection();
		String classId;
		String examId;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetClassExam",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				examId = rs.getString(1);
				classId = rs.getString(2);
				ClassExam ClassExam = new ClassExam(examId,classId);
				map.put(examId+classId, ClassExam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public List<String> getClassIdFromEx(String exID){
		List<String> list = new ArrayList<String>();
		for(ClassExam classEx : mapClassExam.values()) {
			if(classEx.getExamId().equals(exID))
			list.add(classEx.getClassId());
		}
		return list;
	}
	public boolean InsertClassEx(ClassExam classEx) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spInsertClassExam(?,?)");
			stmt.setString(1, classEx.getExamId());
			stmt.setString(2, classEx.getClassId());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		for(String s:new ClassExamDAO().getClassIdFromEx("EX006"))
		System.out.println(s);
	}

}
