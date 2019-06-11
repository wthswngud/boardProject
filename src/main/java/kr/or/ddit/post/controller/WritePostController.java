package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/writeView")
public class WritePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WritePostController.class);
	private IBoardService boardService = null;
	private IAttachService attachService = null;
       
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("write doGet");
		
		String result1 = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		
		if(result1==null){
			result1 = "0";
		}
		
		int postId = Integer.parseInt(result1);
		int boardId = Integer.parseInt(result2);
		
		List<BoardVO> boardList = boardService.boardList();
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		request.setAttribute("userId", userVO.getUserid());
		request.setAttribute("postId", postId);
		request.setAttribute("boardId", boardId);
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/common/left.jsp");
		
		request.getRequestDispatcher("/SE2/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
