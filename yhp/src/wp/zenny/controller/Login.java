package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.RoleDAO;
import wp.zenny.dao.UserDAO;

@WebServlet("/Welcome")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoleDAO roleDAO = new RoleDAO();
		request.setAttribute("mapRole", roleDAO.getMapRole());
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username != null && roleid != null) {
			redirectPage(roleid.toString(),request,response);
			return;
		}
		if(request.getCookies() != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie ck : cookies) {
				if(ck.getName().equals("nameGuest")) {
					request.setAttribute("username", ck.getValue());
				} else if(ck.getName().equals("roleGuest")) {
					request.setAttribute("roleid", ck.getValue());
				} else if(ck.getName().equals("passGuest")) {
					request.setAttribute("password", ck.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("WEB-INF/HomePage.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		// get param from request
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String roleid = request.getParameter("roleid");
		String remember = request.getParameter("remember");
		boolean isExistCookies = false;
		
		// add cookies, save user info
		if(request.getCookies() != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie ck : cookies) {
				if(ck.getName().equals("nameGuest")) {
					ck.setValue(username);
					response.addCookie(ck);
					isExistCookies = true;
				} else if(ck.getName().equals("roleGuest")) {
					ck.setValue(roleid);
					response.addCookie(ck);
				} else if(ck.getName().equals("passGuest")) {
					ck.setValue(pwd);
					response.addCookie(ck);
				}
			}
		}
		if(!isExistCookies) {
			Cookie ckName = new Cookie("nameGuest", username);
			Cookie ckRole = new Cookie("roleGuest", roleid);
			Cookie ckPass = new Cookie("passGuest", pwd);
			ckPass.setMaxAge(60*60*24*30);
			ckName.setMaxAge(60*60*24*30);
			ckRole.setMaxAge(60*60*24*30);
			response.addCookie(ckName);
			response.addCookie(ckRole);
			response.addCookie(ckPass);
		}
		
		if(remember == null) {
			if(request.getCookies() != null){
				Cookie[] cookies = request.getCookies();
				for(Cookie ck : cookies) {
					if(ck.getName().equals("passGuest")) {
						ck.setValue("");
						response.addCookie(ck);
					}
				}
			}
		}
		
		if(userDAO.checkLogin(username,pwd,roleid)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("roleid", roleid);
			
			redirectPage(roleid,request,response);
		}
		else {
			doGet(request,response);
		}
	}
	
	private void redirectPage(String roleid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(roleid) {
		case "4": 
			response.sendRedirect("student-home-page"); 
			break;
		case "2": 
			response.sendRedirect("exam-manager-home");
			break;
		case "3":
			response.sendRedirect("question-manager-home");
			break;
		case "1":
			response.sendRedirect("admin-home-page"); 
			break;
		default:
			response.sendRedirect("error"); 
			break;
		}
	}

}
