package kr.or.ddit.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private IuserService service = null;
	private IBoardService boardService = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new UserServiceImpl();
		boardService = new BoardServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doGet");
		
		if(request.getSession().getAttribute("USER_INFO")!=null){
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			boardList = boardService.boardList();
			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else{
			String userId = request.getParameter("userId");
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost");
		UserVO userVO = new UserVO();
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		String userId = request.getParameter("userId");
		String userPW = request.getParameter("userPW");
		String result = KISA_SHA256.encrypt(userPW);
		
		userVO = service.getUser(userId);
		boardList = boardService.boardList();
		
		
		if(userVO.getUserid().equals(userId) && userVO.getPass().equals(result)){
			
			int cookieMaxAge = 0;
			if(request.getParameter("rememberme") != null)
				cookieMaxAge = 60*60*24*30;
				
			Cookie userIdCookie = new Cookie("userId", userId);
			userIdCookie.setMaxAge(cookieMaxAge);
			
			Cookie rememberMeCookie = new Cookie("rememberme", "true");
			rememberMeCookie.setMaxAge(cookieMaxAge);
			
			response.addCookie(userIdCookie);
			response.addCookie(rememberMeCookie);
			
			request.getSession().setAttribute("USER_INFO", userVO);
			request.setAttribute("userId", userId);
			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/common/left.jsp");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/login");
	}
}