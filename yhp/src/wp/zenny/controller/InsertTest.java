package wp.zenny.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AutoIncreaseID;
import wp.zenny.dao.TestDAO;
import wp.zenny.model.Test;

@WebServlet("/InsertTest")
public class InsertTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertTest() {
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
		TestDAO testDAO = new TestDAO();
		
		//if edit the test
				String testId = request.getParameter("testid");
				if(testId != null && testDAO.mapTest.containsKey(testId)) {
					if(!testDAO.DeleteTest(testId))
					{
						response.sendRedirect("error?errCode=481");
						return;
					}
				} else {
					testId = new AutoIncreaseID(testDAO.mapTest, "TS").getId();
					if(testId == null) {
						response.sendRedirect("error?errCode=248");
						return;
					}
				}

		String testName = request.getParameter("testname");
		String subId = request.getParameter("subname");
		
		Test test = new Test(testId, testName, new Date(), username.toString(), subId);
		if(testDAO.InsertTest(test))
		{
			request.setAttribute("testid", testId);
			request.getRequestDispatcher("InsertTestQues").forward(request, response);
		} else response.sendRedirect("error?errCode=248");
	}

}
