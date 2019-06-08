package kr.or.ddit.CUboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);
    private IPostService postService = null;
    

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		
		int postId = Integer.parseInt(result);
		int boardId = Integer.parseInt(result2);
		request.setAttribute("boardId", boardId);
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO!=null){
			String userId = userVO.getUserid();
			
			PostVO postVO = new PostVO();
			postVO.setPostid(postId);
			postVO.setUserid(userId);
			
			int updateResult = postService.updatePost(postVO);
			
			if(updateResult>0){
				request.getRequestDispatcher("/boardList").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/boardList").forward(request, response);
		}
	}
}
