package wp.zenny.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wp.zenny.dao.ErrorCode;

@WebServlet("/error")
public class error extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public error() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errCode = request.getParameter("errCode");
		String errMess = ErrorCode.getErrorMessage(errCode);
		request.setAttribute("message", errMess);
		
		request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
	}

}
