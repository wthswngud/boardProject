package kr.or.ddit.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comment.model.CommentVO;
import kr.or.ddit.comment.service.CommentServiceImpl;
import kr.or.ddit.comment.service.ICommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/comment")
public class CommentPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommentPostController.class);
	private ICommentService commentService;
       
	@Override
	public void init() throws ServletException {
		commentService = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("comment doGet");
		
		String userId = request.getParameter("userId");
		String result = request.getParameter("postId");
		String comment = request.getParameter("comment");
		String boardId =request.getParameter("boardId");
		logger.debug("boardId : " + boardId);
		logger.debug("userId : " + userId);
		logger.debug("postId : " + result);
		
		int boardid = Integer.parseInt(boardId);
		
		
		int postId = Integer.parseInt(result);
		
		CommentVO cv = new CommentVO();
		
		cv.setPostid(postId);
		cv.setUserid(userId);
		cv.setContentcul(comment);
		cv.setBoardid(boardid);
		
		int insertResult = commentService.insertComment(cv);
		
		if(insertResult>0){
			logger.debug("댓글 등록 성공");
			response.sendRedirect(request.getContextPath()+"/read?postId="+result+"&boardId="+boardId);
		}else{
			response.sendRedirect(request.getContextPath()+"/read?boardId="+result+"&userId="+userId);
			logger.debug("댓글 등록 실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
