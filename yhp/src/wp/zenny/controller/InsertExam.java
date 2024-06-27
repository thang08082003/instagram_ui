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

import wp.zenny.dao.AutoIncreaseID;
import wp.zenny.dao.ClassExamDAO;
import wp.zenny.dao.ExamDAO;
import wp.zenny.model.ClassExam;
import wp.zenny.model.Exam;

@WebServlet("/InsertExam")
public class InsertExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		if(username == null || !roleid.equals("2") ) {
				response.sendRedirect("Welcome");
				return;
		}
		ExamDAO examDAO = new ExamDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String examName = request.getParameter("examname");
		String testId = request.getParameter("testid");
		int timeTest;
		try {
			timeTest = Integer.parseInt(request.getParameter("timetest"));
		} catch(NumberFormatException e) {
			timeTest = 0;
			e.printStackTrace();
		}
		String strDateTest = request.getParameter("datetest").replace('T', ' ');
		Date dateTest;
		try {
			dateTest = sdf.parse(strDateTest);
		} catch (ParseException e) {
			e.printStackTrace();
			response.sendRedirect("error?errCode=248");
			return;
		}
		String[] arrClassId = request.getParameterValues("classid");
		//if edit the examination
		String examId = request.getParameter("examid");
		if(examId != null && examDAO.mapExam.containsKey(examId)) {
			if(!examDAO.DeleteExam(examId))
			{
				response.sendRedirect("error?errCode=481");
				return;
			}
		} else {
			examId = new AutoIncreaseID(examDAO.mapExam, "EX").getId();
			if(examId == null) {
				response.sendRedirect("error?errCode=248");
				return;
			}
		}
		Exam ex = new Exam(examId,testId,examName,timeTest,dateTest,new Date(),username.toString());
		if(examDAO.InsertExam(ex))
		{
			ClassExamDAO classExDAO = new ClassExamDAO();
			for(String classId : arrClassId) {
				ClassExam classEx = new ClassExam(examId,classId);
				classExDAO.InsertClassEx(classEx);
			}
			response.sendRedirect("ViewExam");
			return;
		}
		response.sendRedirect("error?errCode=248");
	}
}
