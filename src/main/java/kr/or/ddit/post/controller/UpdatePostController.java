package kr.or.ddit.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updatePost")
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdatePostController.class);
    private IPostService postService = null;
	

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("게시글 수정 doGet");
		String result = request.getParameter("boardId");
		String result2 = request.getParameter("postId");
		
		int boardId = Integer.parseInt(result);
		int postId = Integer.parseInt(result2);
		
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		
		PostVO postVO2 = postService.searchPostId(postVO);
		
		request.setAttribute("boardList", getServletContext().getAttribute("boardList"));
		request.setAttribute("postVO", postVO2);
		request.getRequestDispatcher("/post/update.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("게시글 수정 doPost");
		String result = request.getParameter("boardId");
		String result2 = request.getParameter("postId");
		String content = request.getParameter("smarteditor");
		String title = request.getParameter("title");
		
		logger.debug("boardId : " + result);
		logger.debug("postId : " + result2);
		logger.debug("content : " + content);
		logger.debug("title : " + title);
		
		int boardId = Integer.parseInt(result);
		int postId = Integer.parseInt(result2);
		
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		postVO.setContentcul(content);
		postVO.setTitlecul(title);
		
		int updateResult = postService.updatePostContent(postVO);
		
		logger.debug("updateResult : " + updateResult);
		
		if(updateResult>0){
			logger.debug("수정 성공");
			request.setAttribute("boardId", boardId);
			request.setAttribute("postId", postId);
			request.getRequestDispatcher("/read").forward(request, response);
		}
	}
}
