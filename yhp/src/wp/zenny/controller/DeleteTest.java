package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.TestDAO;

@WebServlet("/DeleteTest")
public class DeleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteTest() {
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
		String testId = request.getParameter("testid");
		TestDAO testDAO = new TestDAO();
		if(testDAO.DeleteTest(testId))
			response.sendRedirect("ViewTest");
		else response.sendRedirect("error?errCode=369");
	}

}
