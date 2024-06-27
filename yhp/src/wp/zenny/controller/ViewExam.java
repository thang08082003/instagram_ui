package wp.zenny.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wp.zenny.dao.ClassDAO;
import wp.zenny.dao.ClassExamDAO;
import wp.zenny.dao.ExamDAO;
import wp.zenny.dao.SubjectDAO;
import wp.zenny.dao.TestDAO;
import wp.zenny.dao.UserDAO;
import wp.zenny.model.Exam;

@WebServlet("/ViewExam")
public class ViewExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object username = request.getSession().getAttribute("username");
		Object RoleId  = request.getSession().getAttribute("roleid");
		if(username == null || !(RoleId.equals("2") || RoleId.equals("4"))) {
			response.sendRedirect("Welcome");
			return;
			}
		String editExamId = request.getParameter("edit");
		ExamDAO examDAO = new ExamDAO();
		SubjectDAO subDAO = new SubjectDAO();
		TestDAO testDAO = new TestDAO();
		UserDAO userDAO = new UserDAO();
		ClassDAO classDAO = new ClassDAO();
		String subjectId = request.getParameter("subid");
		request.setAttribute("mapClass", classDAO.mapClass);
		request.setAttribute("sdf", new SimpleDateFormat("dd/MM/yyyy hh:mm a"));
		request.setAttribute("mapSub", subDAO.mapSubject);
		request.setAttribute("mapTest", testDAO.mapTest);
		request.setAttribute("testDAO", testDAO);
		request.setAttribute("mapUser", userDAO.mapUser);
		Map<String, Exam> mapExam = new HashMap<String, Exam>();
		if(RoleId.toString().equals("4")) { //if user is student
			mapExam = examDAO.getMapAllExamForStudent(username.toString());
		}
		else {
			mapExam = examDAO.mapExam;
			if(editExamId!=null) {
				Exam ex = examDAO.mapExam.get(editExamId);
				if(ex!=null) {
					ClassExamDAO classExDAO = new ClassExamDAO();
					request.setAttribute("listClassId", classExDAO.getClassIdFromEx(ex.getExamId()));
					request.setAttribute("ex", ex);
					String dateStart = new SimpleDateFormat("yyyy-MM-dd").format(ex.getDateStart()) + "T" + new SimpleDateFormat("HH:mm").format(ex.getDateStart());
					request.setAttribute("dateStart", dateStart);
				}
			}
		}
		if(subjectId != null) {
			request.setAttribute("mapExam", examDAO.getMapExam(mapExam,subjectId));
		} else 			request.setAttribute("mapExam", mapExam);
		request.getRequestDispatcher("WEB-INF/Exam_ViewExam.jsp").forward(request, response);
	}

}
