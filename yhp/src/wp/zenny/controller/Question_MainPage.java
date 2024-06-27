package wp.zenny.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AnswerDAO;
import wp.zenny.dao.ClassDAO;
import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.ExamDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.SubjectDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.UserDAO;
@WebServlet("/question-manager-home")
public class Question_MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Question_MainPage() {
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
		UserDAO userDAO = new UserDAO();
		SubjectDAO subDAO = new SubjectDAO();
		QuestionDAO questionDAO = new QuestionDAO();
		ContentDAO contentDAO = new ContentDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String editCT = request.getParameter("editCT");
		if(editCT!=null && contentDAO.mapContent.get(editCT) !=null)
			request.setAttribute("ctEdit", contentDAO.mapContent.get(editCT));
		request.setAttribute("mapUser", userDAO.mapUser);
		request.setAttribute("mapSubject", subDAO.mapSubject);
		request.setAttribute("contentDAO", contentDAO);
		request.setAttribute("mapContentSize", contentDAO.mapContent.size());
		request.setAttribute("mapQuestion", questionDAO.mapQuestion);
		request.getRequestDispatcher("WEB-INF/Question_MainPage.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
