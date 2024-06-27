package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.Question;

public class QuestionDAO {

	public Map<String,Question> mapQuestion = getMapQuestion();
	
	public QuestionDAO() {}
	
	private Map<String,Question> getMapQuestion(){
		Map<String,Question> map = new HashMap<String,Question>();
		Connection conn = ConnectDatabase.getConnection();
		String questionId;
		String questionName;
		String contentId;
		String levelId;
		Date dateCreate;
		String creator;
		Timestamp ts;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetQuestion",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				questionId = rs.getString(1);
				questionName = rs.getString(2);
				contentId = rs.getString(3);
				 ts = rs.getTimestamp(4);
				dateCreate = ts == null ? ts: new Date(ts.getTime());
				creator = rs.getString(5);
				Question Question = new Question(questionId,questionName,contentId,dateCreate,creator);
				map.put(questionId, Question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean InsertQuestion(Question q) {
		boolean result = false;
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spInsertQuestion(?,?,?,?,?)");
			stmt.setString(1, q.getQuestionId());
			stmt.setString(2, q.getQuestionName());
			stmt.setString(3, q.getContentId());
			stmt.setString(4, s.format(new java.sql.Date(q.getDateCreate().getTime())));
			stmt.setString(5, q.getCreator());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public boolean DeleteQues(String quesId) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spDeleteQuestion(?)");
			stmt.setString(1, quesId);
			
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public String getQuestionID(String Questionname) {
		for(String Questionid: mapQuestion.keySet()) {
			Question Question = mapQuestion.get(Questionid);
			if(Question.getQuestionName().equals(Questionname)) {
				return Question.getQuestionId();
			}
		}
		return null;
	}
	public List<String> getRandomListQuesId(String ctID, int numquest){
		List<String> list = new ArrayList<String>();
		for (Question quest : mapQuestion.values()) {
			if(quest.getContentId() !=null &&quest.getContentId().equals(ctID)) {
				list.add(quest.getQuestionId());
			}
		}
		if(list.size() > numquest) {
		Collections.shuffle(list);
		list = list.subList(0, numquest);
		}
		return list;
	}
	public int countQuesFromContent(String ctID) {
		int kq = 0;
		for (Question quest : mapQuestion.values()) {
			if(quest.getContentId() !=null &&quest.getContentId().equals(ctID)) {
				kq ++;
			}
		}
		return kq;
	}
	public static void main(String[] args) {
		QuestionDAO q = new QuestionDAO();
		
		for (String string : q.getRandomListQuesId("CT001", 2)) {
			System.out.println(string);
		}
	}

}
