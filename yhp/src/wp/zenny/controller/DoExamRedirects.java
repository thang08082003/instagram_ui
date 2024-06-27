package wp.zenny.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.ExamDAO;
import wp.zenny.model.Exam;

@WebServlet("/DoExamRedirects")
public class DoExamRedirects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoExamRedirects() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("4") ) {
			response.sendRedirect("Welcome");
			return;
		}
		String examId = request.getParameter("id");
		ExamDAO exDAO = new ExamDAO();
		if(!exDAO.getExamReady(username.toString()).containsKey(examId)) {
			response.sendRedirect("error?errCode=500");
			return;
		}
		Exam ex = exDAO.mapExam.get(examId);
		if(request.getSession().getAttribute("listQuestion")!=null) {
			request.getSession().removeAttribute("listQuestion");
			request.getSession().removeAttribute("listAnsChoosed");
			request.getSession().removeAttribute("index");
			request.getSession().removeAttribute("sec");
			request.getSession().removeAttribute("min");
			request.getSession().removeAttribute("ex");
		}
		
		if(ex.getDateStart().after(new Date())) {
			request.setAttribute("time",ex.getDateStart().getTime());
			request.getRequestDispatcher("WEB-INF/Wait.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("examCurrent", ex);
			response.sendRedirect("DoExam");
		}
	}

}
