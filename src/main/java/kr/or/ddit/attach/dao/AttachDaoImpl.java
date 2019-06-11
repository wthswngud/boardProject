package kr.or.ddit.attach.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class AttachDaoImpl implements IAttachDao{
	
	/**
	* Method : insertAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 경로 추가
	*/
	@Override
	public int insertAttach(AttachVO av) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("attach.insertAttach", av);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : updateAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 수정
	*/
	@Override
	public int updateAttach(AttachVO av) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("attach.updateAttach", av);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : selectAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 게시글 아이디를 입력받아 해당 하는 첨부파일 리스트를 반환
	*/
	@Override
	public List<AttachVO> selectAttach(int postId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AttachVO> list = sqlSession.selectList("attach.selectAttach", postId);
		sqlSession.close();
		return list;
	}

	/**
	* Method : selectVO
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 id를 받아서 해당 vo를 반환한다.
	*/
	@Override
	public AttachVO selectVO(int attachId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AttachVO vo = sqlSession.selectOne("attach.selectVO", attachId);
		sqlSession.close();
		return vo;
	}

	/**
	* Method : deleteAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 삭제 쿼리문
	*/
	@Override
	public int deleteAttach(int attachId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.delete("attach.deleteAttach", attachId);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}
