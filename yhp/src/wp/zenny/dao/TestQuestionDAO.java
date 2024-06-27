package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.Content;
import wp.zenny.model.Question;
import wp.zenny.model.Test;
import wp.zenny.model.TestQuestion;

public class TestQuestionDAO {

	public Map<String,TestQuestion> mapTestQuestion = getMapTestQuestion();
	
	public TestQuestionDAO() {}
	
	private Map<String,TestQuestion> getMapTestQuestion(){
		Map<String,TestQuestion> map = new HashMap<String,TestQuestion>();
		Connection conn = ConnectDatabase.getConnection();
		String testId;
		String questionId;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetTestQuestion",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				testId = rs.getString(1);
				questionId = rs.getString(2);
				TestQuestion TestQuestion = new TestQuestion(testId,questionId);
				map.put(testId+questionId, TestQuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean InsertTestQuest(TestQuestion testQues) {
		boolean result = false;
		try {
			 Connection conn = ConnectDatabase.getConnection();
			 CallableStatement stmt = conn.prepareCall("call spInsertTestQues(?,?)");
			stmt.setString(1, testQues.getTestId());
			stmt.setString(2, testQues.getQuestionId());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public Map<String,Integer> getMapContentQues(String testid){
		Map<String,Integer> map = new HashMap<String,Integer>();
		ContentDAO contentDAO = new ContentDAO();
		Test test = new TestDAO().mapTest.get(testid);
		String subId = test.getSubId();
		if(test==null || subId == null) return map;
		for(Content ct : contentDAO.getListContentForEachSub(subId).values()) {
			int countQuestInEachContent = 0;
			for(TestQuestion testQues : mapTestQuestion.values()) {
				if(testQues.getTestId().equals(testid) && testQues.getQuestionId()!=null) {
					Question question = new QuestionDAO().mapQuestion.get(testQues.getQuestionId());
					if(question!=null&&question.getContentId().equals(ct.getContentId())) {
						countQuestInEachContent++;
						map.put(ct.getContentId(), countQuestInEachContent);
					}
				}
			}
		}
		return map;
	}
	public List<Question> getQuestionInTest(String testId){
		List<Question> listQuestion = new ArrayList<Question>();
		Map<String, Question> mapQuest = new QuestionDAO().mapQuestion;
		for(TestQuestion testQues : mapTestQuestion.values()) {
			if(testQues.getQuestionId() != null && testQues.getTestId()!=null &&testQues.getTestId().equals(testId)) {
				listQuestion.add(mapQuest.get(testQues.getQuestionId()));
			}
		}
		return listQuestion;
	}
	public static void main(String[] args) {
		List<Question> t = new TestQuestionDAO().getQuestionInTest("TS005");
		for(Question k : t) {
			if(k!=null)
			System.out.println(k.getQuestionId());
		}
		System.out.println("end");
	}
}
