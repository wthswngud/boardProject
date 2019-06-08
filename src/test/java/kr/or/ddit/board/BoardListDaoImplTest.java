package kr.or.ddit.board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardListDaoImpl;
import kr.or.ddit.board.dao.IBoardListDao;
import kr.or.ddit.board.model.BoardVO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardListDaoImplTest {
	private IBoardListDao dao = new BoardListDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(BoardListDaoImplTest.class);

	
	/**
	* Method : getAllBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 모든 정보를 가져오는 메서드
	*/
	@Test
	public void getAllBoardTest() {
		/***Given***/
		List<BoardVO> boardVO = new ArrayList<BoardVO>();
		
		/***When***/
		boardVO = dao.getAllBoard();
		/***Then***/
		logger.debug("boardVO : {}" + boardVO);
	}
	
	/**
	* Method : boardList
	* 작성자 : PC19
	* 변경이력 :
	* @param boardName
	* @return
	* Method 설명 : 사용가능한 게시판만 가져오는 메서드
	*/
	@Test
	public void boardListTest(){
		/***Given***/
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		/***When***/
		 list = dao.boardList();

		/***Then***/
		 logger.debug("list : {}", list);
	}
	
	/**
	* Method : searchNameBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 이름을 파라미터로 같은 이름의 게시판 정보를 반환
	*/
	@Test
	public void searchNameBoardTest(){
		/***Given***/
		String name = "자유게시판";
		BoardVO boardVO = new BoardVO();

		/***When***/
		boardVO = dao.searchNameBoard(name);

		/***Then***/
		logger.debug("boardVO : {}", boardVO);
		
	}
	
	/**
	* Method : insertBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 등록 메서드
	*/
	@Test
	public void insertBoardTest(){
		/***Given***/
		BoardVO boardVO = new BoardVO(1, "곰돌이", "Y", "2019-01-01", "test");
		
		/***When***/
		int result = dao.insertBoard(boardVO);

		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : updateBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 수정 메서드
	*/
	@Test
	public void updateBoardTest(){
		/***Given***/
		BoardVO boardVO = new BoardVO(1, "곰돌이게시판", "N", "2019-01-01", "test");

		/***When***/
		int result = dao.updateBoard(boardVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 id로 해당 게시판 조회
	*/
	@Test
	public void searchBoardIdTest(){
		/***Given***/
		int boardId = 1;

		/***When***/
		BoardVO boardVO = dao.searchBoardId(boardId);

		/***Then***/
		logger.debug("boardVO : {}", boardVO);
	}
}
