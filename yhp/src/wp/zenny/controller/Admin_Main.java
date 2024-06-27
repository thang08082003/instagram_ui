package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.RoleDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.dao.UserRoleDAO;

@WebServlet("/admin-home-page")
public class Admin_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("1") ) {
			response.sendRedirect("Welcome");
			return;
		}
		String title = "Tất cả thành viên";
		UserDAO userDAO = new UserDAO();
		String role = request.getParameter("role");
		if(role == null)
			request.setAttribute("mapAllUser", userDAO.getMapUser());
		else {
			switch(role) {
			case "admin":
				request.setAttribute("mapAllUser", userDAO.getMapUser("1"));
				title="Danh sách quản trị viên";
				break;
			case "examManager":
				request.setAttribute("mapAllUser", userDAO.getMapUser("2"));
				title="Danh sách quản lý kỳ thi";
				break;
			case "questionManager":
				request.setAttribute("mapAllUser", userDAO.getMapUser("3"));
				title="Danh sách quản lý câu hỏi";
				break;
			case "student":
				title="Danh sách học viên";
				request.setAttribute("mapAllUser", userDAO.getMapUser("4"));
				break;
			}
		}
		request.setAttribute("title", title);
		request.setAttribute("userRoleDAO", new UserRoleDAO());
		request.setAttribute("listRoleID", new RoleDAO().getMapRole().keySet().toArray());
		request.getRequestDispatcher("WEB-INF/Admin_MainPage.jsp").forward(request, response);
	}
}
