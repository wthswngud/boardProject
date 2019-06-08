package kr.or.ddit.CUboard;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import oracle.sql.CharacterSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UpdateBoardController
 */
@WebServlet("/update")
public class UpdateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UpdateBoardController.class);
    private IBoardService service = null;
	

	@Override
	public void init() throws ServletException {
		service = new BoardServiceImpl();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardVO boardVO = new BoardVO();
		
		logger.debug("update doGet");
//		String boardId = request.getParameter("select");
		
		String boardId = request.getParameter("name");
		String name = request.getParameter("boardNM");
		String select = request.getParameter("SelectValue2");
		logger.debug("boardId : "+boardId);
		logger.debug("name : "+name);
		logger.debug("select : " + select);
		
		int id = Integer.parseInt(boardId);
		
		boardVO.setBoardid(id);
		boardVO.setName(name);
		boardVO.setUse_yn(select);
		
		logger.debug("boardVO : {}", boardVO);
		
		int result = service.updateBoard(boardVO);
		
		if(result>0){
			logger.debug("수정 성공");
			request.getRequestDispatcher("/manage").forward(request, response);
		}else{
			logger.debug("수정 실패");
			request.getRequestDispatcher("/manage").forward(request, response);
		}
	}
}