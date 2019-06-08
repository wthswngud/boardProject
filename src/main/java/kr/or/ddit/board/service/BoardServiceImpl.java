package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardListDaoImpl;
import kr.or.ddit.board.dao.IBoardListDao;
import kr.or.ddit.board.model.BoardVO;

public class BoardServiceImpl implements IBoardService{
	private IBoardListDao dao = new BoardListDaoImpl();

	/**
	* Method : getAllBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 모든 정보를 가져오는 메서드
	*/
	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	/**
	* Method : boardList
	* 작성자 : PC19
	* 변경이력 :
	* @param boardName
	* @return
	* Method 설명 : 사용가능한 게시판만 가져오는 메서드
	*/
	@Override
	public List<BoardVO> boardList() {
		return dao.boardList();
	}

	/**
	* Method : searchNameBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 이름을 파라미터로 같은 이름의 게시판 정보를 반환
	*/
	@Override
	public BoardVO searchNameBoard(String name) {
		return dao.searchNameBoard(name);
	}

	/**
	* Method : insertBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 등록 메서드
	*/
	@Override
	public int insertBoard(BoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	/**
	* Method : updateBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 수정 메서드
	*/
	@Override
	public int updateBoard(BoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 id로 해당 게시판 조회
	*/
	@Override
	public BoardVO searchBoardId(int boardId) {
		return dao.searchBoardId(boardId);
	}
}
