package kr.or.ddit.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/manage")
public class BoardManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardManageController.class);
	private IBoardService service = null;
	
	@Override
	public void init() throws ServletException {
		service = new BoardServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("manage doGet");
		
		List<BoardVO> list = service.getAllBoard();
		List<BoardVO> boardList = service.boardList();
		
		request.setAttribute("allList", list);
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/common/left.jsp");
		request.getRequestDispatcher("/manage/manage.jsp").forward(request, response);
//		response.sendRedirect(request.getContextPath()+"/manage/manage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
