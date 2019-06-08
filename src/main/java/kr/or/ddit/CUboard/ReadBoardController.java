package kr.or.ddit.CUboard;

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
import kr.or.ddit.comment.model.CommentVO;
import kr.or.ddit.comment.service.CommentServiceImpl;
import kr.or.ddit.comment.service.ICommentService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/read")
public class ReadBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReadBoardController.class);
	private IPostService postService = null;
	private IBoardService boardService = null;
	private ICommentService commentService = null;
	
       
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
		commentService = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("read doGet");
		int postId = 0;
		int boardId = 0;
		
		String resultBoardId = request.getParameter("boardId");
		String resultPostId = request.getParameter("postId");
		
		
		if(resultBoardId!=null){
			boardId = Integer.parseInt(resultBoardId);
		}
		if(resultPostId!=null){
			postId = Integer.parseInt(resultPostId); 
		}
		
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		BoardVO boardVO = boardService.searchBoardId(boardId);
		PostVO postVO1 = postService.searchPostId(postVO);
		List<CommentVO> list = commentService.selectComment(postId);	// 해당 게시글의 댓글을 반환하는 메서드
		
		
		
		List<BoardVO> boardList = boardService.boardList();
		request.setAttribute("boardList", boardList);
		request.setAttribute("postVO", postVO1);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("userVO", userVO);
		request.setAttribute("boardId", boardId);
		request.setAttribute("commentList", list);
		
		
		
		request.getRequestDispatcher("/common/left.jsp");
		request.getRequestDispatcher("/board/read.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
