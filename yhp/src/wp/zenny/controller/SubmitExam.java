package wp.zenny.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wp.zenny.dao.AnswerDAO;
import wp.zenny.dao.ResultDAO;
import wp.zenny.model.Answer;
import wp.zenny.model.Exam;


@WebServlet("/SubmitExam")
public class SubmitExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SubmitExam() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		Object roleid = session.getAttribute("roleid");
		Object listAnsChoosedObj = session.getAttribute("listAnsChoosed");
		if(username == null || !roleid.equals("4") || listAnsChoosedObj == null ) {
			response.sendRedirect("Welcome");
			return;
		}
		String[] listAnsChoosed = (String[]) listAnsChoosedObj;
		int countAnsCorrect = 0;
		Map<String, Answer> mapAllAnswer = new AnswerDAO().getMapAllAnswer();
		for(String ansId : listAnsChoosed){
			Answer ans = mapAllAnswer.get(ansId);
			if(ans != null && ans.getIsCorrect()!=null&& ans.getIsCorrect().equals("1"))
				countAnsCorrect++;
		}
		float mark = (countAnsCorrect*10)/listAnsChoosed.length;
		Exam ex = (Exam)request.getSession().getAttribute("ex");
		int min = Integer.parseInt(request.getSession().getAttribute("min").toString());
		int sec = Integer.parseInt(request.getSession().getAttribute("sec").toString());
		ResultDAO rsDAO = new ResultDAO();
		int timeFinish = ex.getTimeTest()*60 - (min*60 + sec);
		if(rsDAO.InsertResult(ex.getExamId(), username.toString(), mark,timeFinish)) {
			response.sendRedirect("ViewResult");
		} else response.sendRedirect("error?errCode=500");
		
		request.getSession().removeAttribute("listAnsChoosed");
		request.getSession().removeAttribute("listQuestion");
		request.getSession().removeAttribute("index");
		request.getSession().removeAttribute("min");
		request.getSession().removeAttribute("sec");
		request.getSession().removeAttribute("exid");
	}

}
