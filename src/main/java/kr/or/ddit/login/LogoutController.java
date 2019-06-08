package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("USER_INFO") == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}else{
			session.invalidate(); // session안에 있는 모든 정보를 삭제 하는 메서드
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}
}
