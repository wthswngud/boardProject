package kr.or.ddit.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostWriteController
 */
@WebServlet("/write")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024)
public class PostWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostWriteController.class);
	private IPostService postService = null;
	
	
       
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String result = request.getParameter("boardId");
		String content = request.getParameter("smarteditor");
		String profile = request.getParameter("profile");
		
		int boardId = Integer.parseInt(result);
		
		PostVO postVO = new PostVO();
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		postVO.setTitlecul(title);
		postVO.setContentcul(content);
		postVO.setBoardid(boardId);
		postVO.setUserid(userVO.getUserid());
		
		int insertResult = postService.insertPost(postVO);
		
		if(insertResult>0){
			//현재 게시물 번호중 제일 큰값을 조회하는 메서드
			int postId = postService.postIdMaxValue();
			request.setAttribute("postId", postId+"");
			request.setAttribute("boardId", result);
			logger.debug("게시물 제일 큰 값 : " + postId);
			request.getRequestDispatcher("/read").forward(request, response);
		}
	}
}
