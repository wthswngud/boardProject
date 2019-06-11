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

@WebServlet("/commentUpdate")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);
	private ICommentService cvService = null;
	
	@Override
	public void init() throws ServletException {
		cvService = new CommentServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("댓글 삭제 doPost");
		String result1 = request.getParameter("boardId");
		String result2 = request.getParameter("deleteId");
		String result3 = request.getParameter("postId");
		
		int boardId = Integer.parseInt(result1);
		int mentId = Integer.parseInt(result2);
		int postId = Integer.parseInt(result3);
		
		
		int result = cvService.deleteComment(mentId);
		
		if(result>0){
			logger.debug("삭제 성공");
			response.sendRedirect(request.getContextPath()+"/read?boardId="+boardId+"&mentId="+mentId+"&postId="+postId);
		}else{
			logger.debug("삭제 실패");
		}
	}
}