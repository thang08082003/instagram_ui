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
import wp.zenny.dao.ContentDAO;
import wp.zenny.model.Content;

@WebServlet("/InsertContent")
public class InsertContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertContent() {
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
		String subId = request.getParameter("subname");
		String contentName = request.getParameter("contentname");
		ContentDAO ctDAO = new ContentDAO();
		String ctId = new AutoIncreaseID(ctDAO.mapContent, "CT").getId();
		Content ct = new Content(ctId,contentName,subId,new Date(),username.toString());
		String editCtId = request.getParameter("ctEditId");
		if(!editCtId.isEmpty()) {
			if(ctDAO.UpdateContent(editCtId,contentName,subId)) {
				response.sendRedirect("question-manager-home");
			} else response.sendRedirect("error?errCode=481");
		} else {
			if(ctDAO.InsertContent(ct)) {
				response.sendRedirect("question-manager-home");
			} else response.sendRedirect("error?errCode=248");
		}
	}

}
