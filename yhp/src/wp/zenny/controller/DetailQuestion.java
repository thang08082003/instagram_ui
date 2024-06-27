package wp.zenny.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AnswerDAO;
import wp.zenny.dao.ContentDAO;
import wp.zenny.dao.QuestionDAO;
import wp.zenny.dao.SubjectDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.model.Content;
import wp.zenny.model.Question;

@WebServlet("/DetailQuestion")
public class DetailQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DetailQuestion() {
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
		String questId = request.getParameter("id");
		Question q = new QuestionDAO().mapQuestion.get(questId);
		if(q == null) {
			response.sendRedirect("ViewQuestion");
			return;
		}
		Content ct = new ContentDAO().mapContent.get(q.getContentId());
		if(ct!=null)
			request.setAttribute("subid", ct.getSubId());
		request.setAttribute("mapUser", new UserDAO().mapUser);
		request.setAttribute("contentDAO", new ContentDAO());
		request.setAttribute("mapSubject", new SubjectDAO().mapSubject);
		request.setAttribute("ansDAO", new AnswerDAO());
		request.setAttribute("q", q);
		request.getRequestDispatcher("WEB-INF/DetailQues.jsp").forward(request, response);
	}

}
