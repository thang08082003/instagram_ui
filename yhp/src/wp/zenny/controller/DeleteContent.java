package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.ContentDAO;

@WebServlet("/DeleteContent")
public class DeleteContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteContent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("3") ) {
				response.sendRedirect("Welcome");
				return;
		}
		String contentId = request.getParameter("id");
		if(new ContentDAO().DeleteContent(contentId))
			response.sendRedirect("question-manager-home");
		else response.sendRedirect("error?errCode=369");
	}

}
