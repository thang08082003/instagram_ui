package wp.zenny.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wp.zenny.dao.MD5Encryptor;
import wp.zenny.dao.UserDAO;
import wp.zenny.dao.UserRoleDAO;
import wp.zenny.model.Role;
import wp.zenny.model.User;

@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object username = request.getSession().getAttribute("username");
		if(username == null){
			response.sendRedirect("Welcome");
			return;
		}
		UserDAO usDAO = new UserDAO();
		User us = usDAO.mapUser.get(username.toString());
		request.setAttribute("us", us);
		List<Role> listRole = new UserRoleDAO().getListRole(us.getUsername());
		request.setAttribute("listRoleUser", listRole);
		request.getRequestDispatcher("WEB-INF/UserInfo.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String state = "0";
		String passOld = request.getParameter("passold");
		MD5Encryptor md5Enc = new MD5Encryptor();
		String passOldEnc = md5Enc.encrypt(passOld);
		String passNew = request.getParameter("passnew");
		String passNew2 = request.getParameter("passnew2");
		User us = new UserDAO().mapUser.get(request.getSession().getAttribute("username").toString());
		if(!passNew.equals(passNew2)) {
			state="101";
		}
		else if(!passOldEnc.equals(us.getPassword())) {
			state="404";
		}
		else {
			us.setPassword(md5Enc.encrypt(passNew));
			if(!(new UserDAO().UpdateUser(us)))
			{
				state="808";
				response.sendRedirect("error?errCode=481");
				return;
			}
		}
		if(state!="0")
			request.setAttribute("state", state);
			doGet(request,response);
	}

}
