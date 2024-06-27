package wp.zenny.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.UserDAO;
import wp.zenny.dao.UserRoleDAO;
import wp.zenny.model.User;
import wp.zenny.model.UserRole;

@WebServlet("/edit-user")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object usernameSS = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(usernameSS == null || !roleid.equals("1") ) {
				response.sendRedirect("Welcome");
				return;
		}
		String username = request.getParameter("username");
		UserDAO usDAO = new UserDAO();
		UserRoleDAO usRoleDAO = new UserRoleDAO();
		User us = usDAO.mapUser.get(username);
		if(us == null) return;
		us.setFullname(request.getParameter("fullname"));
		us.setGender(request.getParameter("sex"));
		us.setPhonenumber(request.getParameter("phone"));
		try {
			us.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String page = "error?errCode=481";
		
		if(usDAO.UpdateUser(us)) {
			if(usRoleDAO.DeleteUserRole(username)) {
				String[] arrRoleId = new String[4];
				arrRoleId[0] = request.getParameter("student");
				arrRoleId[1] = request.getParameter("exammanager");
				arrRoleId[2] = request.getParameter("questionmanager");
				arrRoleId[3] = request.getParameter("admin");
				page = "ViewDetail?id="+username;
				for(String RoleId : arrRoleId) {
					if(RoleId == null)
						continue;
					UserRole usRole = new UserRole();
					usRole.setCreator(request.getSession().getAttribute("username").toString());
					usRole.setDateCreate(new Date());
					usRole.setUsername(us.getUsername());
					usRole.setRoleId(RoleId);
					usRoleDAO.InsertUserRole(usRole);
				}
			}
		}
		response.sendRedirect(page);
	}

}
