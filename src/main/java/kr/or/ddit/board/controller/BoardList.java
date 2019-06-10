package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/boardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardList.class);
	private IBoardService boardService;
	private IPostService postService;
	
    
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
		postService = new PostServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("BoardList doGet");
		String boardName = request.getParameter("boardName");
		logger.debug("boardName : " + boardName);
		PageVO pageVO = new PageVO();
		
		String result1 = request.getParameter("page");
		String result2 = request.getParameter("pageSize");
		String result3 = request.getParameter("boardId");
		
		int boardId = Integer.parseInt(result3);
		
		BoardVO boardVO = boardService.searchBoardId(boardId);
		
		if(result1 == null || result1.equals(""))
			result1 = "1";
		
		if(result2 == null || result2.equals(""))
			result2 = "10";
		
		int page = Integer.parseInt(result1);
		int pageSize = Integer.parseInt(result2);
		
		pageVO.setPage(page);
		pageVO.setPageSize(pageSize);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("boardid", boardVO.getBoardid());
		
		logger.debug("boardid : {}", boardId);
		
		List<PostVO> postList = postService.postPagingList(map);
		
		Map<String, Object> resultMap = postService.getPaging(boardId, pageVO);
		List<PostVO> postList1 = (List<PostVO>) resultMap.get("postList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		logger.debug("boardVO : {}", boardVO);
		
		
		request.setAttribute("boardid", boardId);
		request.setAttribute("postList", postList);
		getServletContext().setAttribute("postList", postList);
		
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("pageVO", pageVO);
		
		List<BoardVO> list = boardService.getAllBoard();
		List<BoardVO> boardList = boardService.boardList();
		
		request.setAttribute("allList", list);
		request.setAttribute("boardList", boardList);
		getServletContext().setAttribute("boardList", boardList);
		request.getRequestDispatcher("/common/left.jsp");

		request.setAttribute("boardName", boardName);
		request.getRequestDispatcher("/board/boardList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}