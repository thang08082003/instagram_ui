package wp.zenny.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.RoleDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.dao.UserRoleDAO;
import wp.zenny.model.Role;
import wp.zenny.model.User;
@WebServlet("/ViewDetail")
public class ViewDetailUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewDetailUser() {
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
		String username = request.getParameter("id");
		User us = new UserDAO().mapUser.get(username);
		if (us == null)
			response.sendRedirect("admin-home-page");
		else {
			request.setAttribute("user", us);
			List<Role> listRole = new UserRoleDAO().getListRole(us.getUsername());
			if(listRole.size() > 0)
			request.setAttribute("listRoleUser", listRole);
			request.setAttribute("listRoleID", new RoleDAO().getMapRole().keySet().toArray());
			request.getRequestDispatcher("WEB-INF/ViewDetailUser.jsp").forward(request, response);
		}
	}

}
