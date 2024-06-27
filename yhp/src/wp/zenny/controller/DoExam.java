package wp.zenny.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AnswerDAO;
import wp.zenny.dao.ExamDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.TestQuestionDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.model.Exam;
import wp.zenny.model.Question;

@WebServlet("/DoExam")
public class DoExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("4") ) {
			response.sendRedirect("Welcome");
			return;
		}
		Object exObj = request.getSession().getAttribute("examCurrent");
		if(exObj == null) {
			response.sendRedirect("student-home-page");
			return;
		}
		Exam ex = (Exam)exObj;
		if(ex.getTestId()==null) {
			response.getWriter().println("This exam didn't have any test");
			return;
		}
		TestDAO testDAO = new TestDAO();
		ExamDAO examDAO = new ExamDAO();
		QuestionDAO questionDAO = new QuestionDAO();
		AnswerDAO answerDAO = new AnswerDAO();
		UserDAO userDAO = new UserDAO();
		TestQuestionDAO testQuesDAO = new TestQuestionDAO();
		if(request.getSession().getAttribute("listQuestion") == null) {
			List<Question> listQuestion = testQuesDAO.getQuestionInTest(ex.getTestId());
			String[] listAnsChoosed = new String[listQuestion.size()];
			int index = 0;
			request.getSession().setAttribute("listAnsChoosed", listAnsChoosed);
			request.getSession().setAttribute("listQuestion",listQuestion);
			request.getSession().setAttribute("index", index);
			request.getSession().setAttribute("min", ex.getTimeTest());
			request.getSession().setAttribute("sec", "00");
			request.getSession().setAttribute("ex", ex);
		}
		request.setAttribute("mapUser", userDAO.mapUser);
		request.setAttribute("answerDAO", answerDAO);
		request.getRequestDispatcher("WEB-INF/DoExam.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ansChoose = request.getParameter("ansChoose");
		int indexNext = Integer.parseInt(request.getParameter("indexNext"));
		int index = (int)request.getSession().getAttribute("index");
		String[] listAnsChoosed = (String[]) request.getSession().getAttribute("listAnsChoosed");
			listAnsChoosed[index] = ansChoose;
			request.getSession().setAttribute("index", indexNext);
			request.getSession().setAttribute("listAnsChoosed", listAnsChoosed);
			String min = request.getParameter("min");
			int sec = Integer.parseInt(request.getParameter("sec"));
			
			request.getSession().setAttribute("min", min);
			request.getSession().setAttribute("sec", --sec);
			if(indexNext==-1) {
				response.sendRedirect("SubmitExam");
			}else {
				request.getSession().setAttribute("index", indexNext);
				response.sendRedirect("DoExam");
			}
	}

}
