package wp.zenny.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AnswerDAO;
import wp.zenny.dao.AutoIncreaseID;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.model.Answer;
import wp.zenny.model.Question;

@WebServlet("/InsertQuestion")
public class InsertQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertQuestion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("3") ) {
				response.sendRedirect("Welcome");
				return;
		}
		QuestionDAO quesDAO = new QuestionDAO();
		AnswerDAO ansDAO = new AnswerDAO();
		String quesId = new AutoIncreaseID(quesDAO.mapQuestion, "QT").getId();
		String ctId = request.getParameter("contentname");
		String questName = request.getParameter("questname");
		String quesEditId = request.getParameter("quesEditId");
		if(quesEditId!=null) {
			quesId = quesEditId;
			if(!quesDAO.DeleteQues(quesId)) {
				response.sendRedirect("error?errCode=481");
				return;
			}
		}
		Question q = new Question(quesId,questName,ctId,new Date(),username.toString());
		if(quesDAO.InsertQuestion(q)) {
			String[] arrAnsName = request.getParameterValues("answername");
			if(arrAnsName != null) {
				for(int i=0;i<arrAnsName.length;i++) {
					String ansId = new AutoIncreaseID(ansDAO.getMapAllAnswer(), "AS").getId();
					if(arrAnsName[i]!=null && !arrAnsName[i].isEmpty()) {
						String isCorrect = "0";
						if(i==0) isCorrect = "1";
						Answer ans = new Answer(ansId,arrAnsName[i],quesId,isCorrect);
						ansDAO.InsertAnswer(ans);
					}
				}
			}
			response.sendRedirect("ViewQuestion");
		} else response.sendRedirect("error?errCode=248");
	}

}
