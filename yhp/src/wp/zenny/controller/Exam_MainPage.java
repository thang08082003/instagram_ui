package wp.zenny.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.ClassDAO;
import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.ExamDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.SubjectDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.UserDAO;

@WebServlet(urlPatterns = {"/exam-manager-home"})
public class Exam_MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Exam_MainPage() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username != null && roleid.equals("2") ) {
			ExamDAO examDAO = new ExamDAO();
			TestDAO testDAO = new TestDAO();
			SubjectDAO subjectDAO = new SubjectDAO();
			UserDAO userDAO = new UserDAO();
			ContentDAO contentDAO = new ContentDAO();
			QuestionDAO questionDAO = new QuestionDAO();
			ClassDAO classDAO = new ClassDAO();
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			request.setAttribute("mapTopExam", examDAO.mapTopExam);
			request.setAttribute("mapTopTest", testDAO.mapTopTest);
			request.setAttribute("mapSubject", subjectDAO.mapSubject);
			request.setAttribute("mapContent", contentDAO.mapContent);
			request.setAttribute("mapClass", classDAO.mapClass);
			
			request.setAttribute("testDAO", testDAO);
			request.setAttribute("mapUser", userDAO.mapUser);
			request.setAttribute("subjectDAO", subjectDAO);
			request.setAttribute("sdf", sdf);
			request.setAttribute("contentDAO", contentDAO);
			request.setAttribute("questionDAO", questionDAO);
				request.getRequestDispatcher("WEB-INF/Exam_MainPage.jsp").forward(request, response);
		} else {
				response.sendRedirect("Welcome");
				return;
		}
	}
}
