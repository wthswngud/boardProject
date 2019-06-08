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
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class CreateBoard
 */
@WebServlet("/createBoard")
public class CreateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreateBoardController.class);
	private IBoardService boardService = null;
	
       
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("createBoard doGet");
		BoardVO boardVO = new BoardVO();
		UserVO userVO = new UserVO();
		
		String name = request.getParameter("name");
		BoardVO boardNM = boardService.searchNameBoard(name);
		
		if(name==null)
			name="";
		
		userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		
		boardVO.setName(name);
		
		if(userVO == null){
//			
			String msg = "로그인을 해야 가능합니다.";
			logger.debug(msg);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/manage").forward(request, response);
			return;
		}
		String userId = userVO.getUserid();
		logger.debug("id : " + userId);
		boardVO.setUserid(userVO.getUserid());
		
		if(name == null || name.equals("") || name.equals(null)){
			
			String msg = "등록할 게시판 이름을 입력해주세요.";
			logger.debug(msg);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/common/left.jsp");
			request.getRequestDispatcher("/manage").forward(request, response);
			return;
		}
		
		if(boardNM != null){
			if(boardNM.getName().equals(name)){
				
				String msg = "동일한 이름의 게시판이 있습니다.";
				logger.debug(msg);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/common/left.jsp");
				request.getRequestDispatcher("/manage").forward(request, response);
				return;
			}
		}
		
		int result = boardService.insertBoard(boardVO);
		
		//새로 리스트 가져오기 사용가능한...
		List<BoardVO> boardList = boardService.boardList();
		//새로 모든 리스트 가져오기
		List<BoardVO> allList = boardService.getAllBoard();
		
		if(result > 0){
			logger.debug("등록 성공");
//			request.setAttribute("boardList", boardList);
//			request.setAttribute("allList", allList);
//			request.getRequestDispatcher("/manage").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/manage");
		}else{
			logger.debug("등록 실패");
			response.sendRedirect(request.getContextPath()+"/manage");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
