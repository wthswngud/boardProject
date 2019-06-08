package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.mybatis.MyBatisUtil;


public class BoardListDaoImpl implements IBoardListDao{

	/**
	* Method : getAllBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 모든 정보를 가져오는 메서드
	*/
	@Override
	public List<BoardVO> getAllBoard() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.getAllBoard");
		sqlSession.close();
		return boardList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> list = sqlSession.selectList("board.boardList");
		sqlSession.close();
		return list;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.searchNameBoard", name);
		sqlSession.close();
		return boardVO;
	}

	/**
	* Method : insertBoard
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 등록 메서드
	*/
	@Override
	public int insertBoard(BoardVO	boardVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("board.insertBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("board.updateBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.searchBoardId", boardId);
		sqlSession.close();
		return boardVO;
	}
}
