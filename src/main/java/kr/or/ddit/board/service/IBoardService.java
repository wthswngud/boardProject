package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	/**
	* Method : getAllBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 모든 정보를 가져오는 메서드
	*/
	List<BoardVO> getAllBoard();
	
	/**
	* Method : boardList
	* 작성자 : PC19
	* 변경이력 :
	* @param boardName
	* @return
	* Method 설명 : 사용가능한 게시판만 가져오는 메서드
	*/
	List<BoardVO> boardList();
	
	/**
	* Method : searchNameBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 이름을 파라미터로 같은 이름의 게시판 정보를 반환
	*/
	BoardVO	searchNameBoard(String name);
	
	/**
	* Method : insertBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 등록 메서드
	*/
	int insertBoard(BoardVO	boardVO);
	
	/**
	* Method : updateBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 수정 메서드
	*/
	int updateBoard(BoardVO boardVO);
	
	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 id로 해당 게시판 조회
	*/
	BoardVO searchBoardId(int boardId);
}
