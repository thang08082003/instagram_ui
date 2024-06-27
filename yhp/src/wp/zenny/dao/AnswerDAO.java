package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.Answer;

public class AnswerDAO {
	
	public AnswerDAO() {}
	
	public Map<String,Answer> getMapAnswer(String questid){
		Map<String,Answer> map = new HashMap<String,Answer>();
		Connection conn = ConnectDatabase.getConnection();
		String answerId;
		String answerName;
		String questionId;
		String isCorrect;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetAnswer(?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, questid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				answerId = rs.getString(1);
				answerName = rs.getString(2);
				questionId = rs.getString(3);
				isCorrect = rs.getString(4);
				
				Answer Answer = new Answer(answerId,answerName,questionId,isCorrect);
				map.put(answerId, Answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean InsertAnswer(Answer ans) {
		boolean result = false;
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spInsertAnswer(?,?,?,?)");
			stmt.setString(1, ans.getAnswerId());
			stmt.setString(2, ans.getAnswerName());
			stmt.setString(3, ans.getQuestionId());
			stmt.setString(4, ans.getIsCorrect());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public Map<String,Answer> getMapAllAnswer(){
		Map<String,Answer> map = new HashMap<String,Answer>();
		Connection conn = ConnectDatabase.getConnection();
		String answerId;
		String answerName;
		String questionId;
		String isCorrect;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetAllAnswer()",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				answerId = rs.getString(1);
				answerName = rs.getString(2);
				questionId = rs.getString(3);
				isCorrect = rs.getString(4);
				
				Answer Answer = new Answer(answerId,answerName,questionId,isCorrect);
				map.put(answerId, Answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String[] args) {
		for(Answer a : new AnswerDAO().getMapAllAnswer().values()) {
			System.out.println(a.getIsCorrect());
		}
	}
}
