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
import wp.zenny.model.Exam;
import wp.zenny.model.Result;

public class ExamDAO {

	public Map<String,Exam> mapExam = getMapExam();
	public Map<String,Exam> mapTopExam = getMapTopExam();
	
	public ExamDAO() {}
	
	private Map<String,Exam> getMapExam(){
		Map<String,Exam> map = new HashMap<String,Exam>();
		Connection conn = ConnectDatabase.getConnection();
		String examId;
		String testId;
		String examName;
		int timeTest;
		Date dateStart;
		Date dateCreate;
		String creator;
		Timestamp ts;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetExam",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				examId = rs.getString(1);
				testId = rs.getString(2);
				examName = rs.getString(3);
				try {
				timeTest = (int)rs.getInt(4);
				} catch(Exception e){timeTest = 0;}
				
					 ts = rs.getTimestamp(5);
				dateStart = ts == null ? ts : new Date(ts.getTime());
				
					 ts = rs.getTimestamp(6);
				dateCreate = ts ==null ? ts : new Date(ts.getTime());
				
				creator = rs.getString(7);
				
				Exam Exam = new Exam(examId,testId,examName,timeTest,dateStart,dateCreate,creator);
				map.put(examId, Exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean InsertExam(Exam ex) {
		boolean result = false;
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spInsertExam(?,?,?,?,?,?,?)");
			stmt.setString(1, ex.getExamId());
			stmt.setString(2, ex.getTestId());
			stmt.setString(3, ex.getExamName());
			stmt.setInt(4, ex.getTimeTest());
			stmt.setString(5, s.format(new java.sql.Date(ex.getDateStart().getTime())));
			stmt.setString(6, s.format(new java.sql.Date(ex.getDateCreate().getTime())));
			stmt.setString(7, ex.getCreator());
			
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	
	public boolean DeleteExam(String examid) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spDeleteExam(?)");
			stmt.setString(1, examid);
			
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	
	public Map<String,Exam> getMapExam(Map<String,Exam> mapParent,String subid){
		TestDAO testDAO = new TestDAO();
		Map<String,Exam> map = new HashMap<String, Exam>();
		for(Exam ex : mapParent.values()) {
			if(ex.getTestId()!=null & testDAO.mapTest.get(ex.getTestId()).getSubId().equals(subid))
				map.put(ex.getExamId(), ex);
		}
		return map;
	}
	
	private Map<String,Exam> getMapTopExam(){
		Map<String,Exam> map = new HashMap<String,Exam>();
		Connection conn = ConnectDatabase.getConnection();
		String examId;
		String testId;
		String examName;
		int timeTest;
		Date dateStart;
		Date dateCreate;
		String creator;
		Timestamp ts;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetTopExam",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				examId = rs.getString(1);
				testId = rs.getString(2);
				examName = rs.getString(3);
				try {
				timeTest = (int)rs.getInt(4);
				} catch(Exception e){timeTest = 0;}
				
					 ts = rs.getTimestamp(5);
				dateStart = ts == null ? ts : new Date(ts.getTime());
				
					 ts = rs.getTimestamp(6);
				dateCreate = ts ==null ? ts : new Date(ts.getTime());
				
				creator = rs.getString(7);
				
				Exam Exam = new Exam(examId,testId,examName,timeTest,dateStart,dateCreate,creator);
				map.put(examId, Exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String,Exam> getMapAllExamForStudent(String username){
		Map<String,Exam> map = new HashMap<String,Exam>();
		Connection conn = ConnectDatabase.getConnection();
		String examId;
		String testId;
		String examName;
		int timeTest;
		Date dateStart;
		Date dateCreate;
		String creator;
		Timestamp ts;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetALLExamForStudent(?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				examId = rs.getString(1);
				testId = rs.getString(2);
				examName = rs.getString(3);
				try {
				timeTest = (int)rs.getInt(4);
				} catch(Exception e){timeTest = 0;}
				
					 ts = rs.getTimestamp(5);
				dateStart = ts == null ? ts : new Date(ts.getTime());
				
					 ts = rs.getTimestamp(6);
				dateCreate = ts ==null ? ts : new Date(ts.getTime());
				
				creator = rs.getString(7);
				
				Exam Exam = new Exam(examId,testId,examName,timeTest,dateStart,dateCreate,creator);
				map.put(examId, Exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String,Exam> getExamReady(String username){
		Map<String,Exam> map = new HashMap<String,Exam>();
		Map<String,Exam> AllExamForSt = getMapAllExamForStudent(username);
		Map<String,Result> mapResult = new ResultDAO().getMapResult(username);
		for(Exam ex : AllExamForSt.values()) {
			if(!mapResult.containsKey(ex.getExamId())){
				map.put(ex.getExamId(), ex);
			}
		}
		return map;
	}
	public static void main(String[] args) {
		for(String s : new ExamDAO().getExamReady("15110378").keySet() ) {
			System.out.println(s);
		}
	}
}
