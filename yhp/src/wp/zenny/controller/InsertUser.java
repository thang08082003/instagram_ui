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

import wp.zenny.dao.MD5Encryptor;
import wp.zenny.dao.UserDAO;
import wp.zenny.dao.UserRoleDAO;
import wp.zenny.model.User;
import wp.zenny.model.UserRole;
@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		Object usernameSS = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(usernameSS == null || !roleid.equals("1") ) {
				response.sendRedirect("Welcome");
				return;
		}
		String username = request.getParameter("username");
		if(userDAO.mapUser.containsKey(username)) {
			response.sendRedirect("error?errCode=105");
			return;
		}
		String fullname = request.getParameter("fullname");
		String sex = request.getParameter("sex");
		if (sex==null) sex ="1";
		String str_birthday = request.getParameter("birthday");
		Date birthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			birthday = sdf.parse(str_birthday);
			str_birthday = sdf2.format(birthday);
			String[] str = str_birthday.split("/");
			str_birthday = str[0]+str[1]+str[2];
		} catch (ParseException e) {
			e.printStackTrace();
			str_birthday = username.toString();
			birthday = null;
		}
		String phoneNumber = request.getParameter("phone");
		
		User us = new User(username.toString(),new MD5Encryptor().encrypt(str_birthday), fullname, sex, birthday, phoneNumber, "avt.jpg");
		
		if(userDAO.InsertUser(us)) {
			insertUserRole(request, "student", username.toString());
			insertUserRole(request, "admin", username.toString());
			insertUserRole(request, "exammanager", username.toString());
			insertUserRole(request, "questionmanager", username.toString());
			response.sendRedirect("admin-home-page");
			
		} else {
			response.sendRedirect("error?errCode=248");
		}
	}
	private void insertUserRole(HttpServletRequest request, String roleName, String username) {
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		if(request.getParameter(roleName)!=null) {
			UserRole usRole = new UserRole(username, request.getParameter(roleName), new Date(), request.getSession().getAttribute("username").toString());
			userRoleDAO.InsertUserRole(usRole);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
