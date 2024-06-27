package wp.zenny.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.TestQuestionDAO;
import wp.zenny.model.Content;
import wp.zenny.model.TestQuestion;
@WebServlet("/InsertTestQues")
public class InsertTestQues extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertTestQues() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subId = request.getParameter("subname");
		String ctId;
		String numques;
		String page = "ViewTest";
		ContentDAO ctDAO = new ContentDAO();
		QuestionDAO questDAO = new QuestionDAO();
		TestQuestionDAO testQuesDAO = new TestQuestionDAO();
		List<String> listQuesID;
		Map<String,Content> mapTestForEachSub = ctDAO.getListContentForEachSub(subId);
		for(String ctID : mapTestForEachSub.keySet()) {
			ctId = request.getParameter("ctID"+ctID);
			if(ctId!=null) {
				numques = request.getParameter("numques"+ctID);
				try {
					listQuesID = questDAO.getRandomListQuesId(ctID,Integer.parseInt(numques));
					for(String questId:listQuesID) {
						TestQuestion testQuest = new TestQuestion(request.getAttribute("testid").toString(),questId);
						testQuesDAO.InsertTestQuest(testQuest);
					}
				} catch(NumberFormatException e) {
					e.printStackTrace();
					page = "error?errCode=632";
				}
			}
		}
		response.sendRedirect(page);
	}

}
