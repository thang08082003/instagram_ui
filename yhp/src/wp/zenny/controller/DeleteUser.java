package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.UserDAO;
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object usernameSS = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(usernameSS == null || !roleid.equals("1") ) {
				response.sendRedirect("Welcome");
				return;
		}
		String username = request.getParameter("username");
		if(username.equals(request.getSession().getAttribute("username"))) {
			response.sendRedirect("admin-home-page");
			return;
		}
		if(new UserDAO().DeleteUser(username))
			response.sendRedirect("admin-home-page");
		else {
			response.sendRedirect("error?errCode=369");
		}
	}

}
