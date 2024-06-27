package wp.zenny.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.SubjectDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.TestQuestionDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.model.Test;


@WebServlet("/ViewTest")
public class ViewTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ViewTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("2") ) {
				response.sendRedirect("Welcome");
				return;
		}
    	SubjectDAO subDAO = new SubjectDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    	TestDAO testDAO = new TestDAO();
    	UserDAO userDAO = new UserDAO();
    	ContentDAO contentDAO = new ContentDAO();
    	String subId = request.getParameter("subid");
    	String testId = request.getParameter("edit");
    	if(testId != null) {
    		Test test = testDAO.mapTest.get(testId);
        	request.setAttribute("mapContentQuest", new TestQuestionDAO().getMapContentQues(testId));
    		if(test!=null) request.setAttribute("test", test);
    	}
    	request.setAttribute("subDAO", subDAO);
    	request.setAttribute("testDAO", testDAO);
    	request.setAttribute("contentDAO", contentDAO);
    	if(subId == null)
    		request.setAttribute("mapTest", testDAO.mapTest);
    	else {
    		request.setAttribute("mapTest", testDAO.getTestForEachSub(subId));
    	}
    	request.setAttribute("sdf", sdf);
    	request.setAttribute("mapContent", contentDAO.mapContent);
    	request.setAttribute("mapUser", userDAO.mapUser);
    	request.setAttribute("mapSub", subDAO.mapSubject);
    	request.setAttribute("questionDAO", new QuestionDAO());
		request.getRequestDispatcher("WEB-INF/Exam_ViewTest.jsp").forward(request, response);
	}
}
