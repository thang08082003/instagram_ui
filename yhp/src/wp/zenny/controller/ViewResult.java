package wp.zenny.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.ExamDAO;
import wp.zenny.dao.ResultDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.UserDAO;

@WebServlet("/ViewResult")
public class ViewResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewResult() {
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
		ResultDAO resultDAO = new ResultDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a dd-MM-yyyy");

		request.setAttribute("sdf", sdf);
		request.setAttribute("testDAO", new TestDAO());
		request.setAttribute("examDAO", new ExamDAO());
		request.setAttribute("mapExam", new ExamDAO().mapExam);
		request.setAttribute("mapTest", new TestDAO().mapTest);
		request.setAttribute("mapUser", new UserDAO().mapUser);
		
		
		request.setAttribute("mapResult", resultDAO.getMapResult(username.toString()));
		request.getRequestDispatcher("WEB-INF/ViewResult.jsp").forward(request, response);
	}

}
