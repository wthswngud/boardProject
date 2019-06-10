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
		String result = request.getParameter("deleteComment");
		String result2 = request.getParameter("deleteId");
		
		int mentid = Integer.parseInt(result2);
		
		CommentVO cv = new CommentVO();
		cv.setMentid(mentid);
	}
}
