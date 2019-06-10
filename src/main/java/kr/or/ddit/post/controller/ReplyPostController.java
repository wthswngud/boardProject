package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/reply")
public class ReplyPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyPostController.class);
    private IPostService postService = null;
    private IBoardService boardService = null;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		logger.debug("답글(Reply) doGet");
		
		String result = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}
		
		int postId = Integer.parseInt(result);
		int boardId = Integer.parseInt(result2);
		
		
		List<PostVO> postList = (List<PostVO>) getServletContext().getAttribute("postList");
		PostVO postVO = new PostVO();
		
		
		request.setAttribute("postId", postId);
		request.setAttribute("boardId", boardId);
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/common/left.jsp");
		request.getRequestDispatcher("/post/reply.jsp").forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("답글(Reply) doPost");
		
		String result = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		String title = request.getParameter("title");
		String smarteditor = request.getParameter("smarteditor");
		String profile = request.getParameter("profile");
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}
		
		int postId = Integer.parseInt(result);
		int boardId = Integer.parseInt(result2);
		
		logger.debug("postId : " + postId);
		logger.debug("boardId : " + boardId);
		
		List<PostVO> postList = (List<PostVO>) getServletContext().getAttribute("postList");
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		postVO.setPostid2(postId);
		postVO.setUserid(userVO.getUserid());
		postVO.setTitlecul(title);
		postVO.setContentcul(smarteditor);
		
		
		PostVO postVO1 = postService.selectParent(postVO);
		
		logger.debug("postVO1 : {}", postVO1);
		
		postVO.setGroupSeq(postVO1.getGroupSeq());
		
		int insertResult = postService.insertReply(postVO);
		
		
		if(insertResult>0){
			BoardVO boardVO = boardService.searchBoardId(boardId);
			
			request.setAttribute("boardName", boardVO.getName());
			
			logger.debug("답변 등록 성공");
			
			request.setAttribute("boardId", boardId);
			request.setAttribute("postList", postList);
			request.getRequestDispatcher("/common/left.jsp");
			request.getRequestDispatcher("/boardList").forward(request, response);
		}
	}
}
