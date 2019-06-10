package kr.or.ddit.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comment.model.CommentVO;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.post.model.PostVO;

public class CommentDaoImpl implements ICommentDao{
	/**
	* Method : insertComment
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 등록 메서드
	*/
	@Override
	public int insertComment(CommentVO cv) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("comment.insertComment", cv);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : selectComment
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 해당 게시글의 댓글을 조회하는 메서드
	*/
	@Override
	public List<CommentVO> selectComment(CommentVO cv) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<CommentVO> list= sqlSession.selectList("comment.selectComment", cv);
		sqlSession.close();
		return list;
	}

	@Override
	public int deleteComment(int mentId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("comment.deleteComment", mentId);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}