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
import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.UserDAO;

@WebServlet("/ViewQuestion")
public class ViewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewQuestion() {
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
		request.setAttribute("sdf", new SimpleDateFormat("dd/MM/yyyy hh:mm a"));
		request.setAttribute("mapUser", new UserDAO().mapUser);
		request.setAttribute("mapQues", new QuestionDAO().mapQuestion);
		request.setAttribute("mapContent", new ContentDAO().mapContent);
		request.setAttribute("ansDAO", new AnswerDAO());
		
		request.getRequestDispatcher("WEB-INF/Question_View.jsp").forward(request, response);
	}

}
