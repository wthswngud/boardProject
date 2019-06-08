package kr.or.ddit.comment.service;

import java.util.List;

import kr.or.ddit.comment.dao.CommentDaoImpl;
import kr.or.ddit.comment.dao.ICommentDao;
import kr.or.ddit.comment.model.CommentVO;

public class CommentServiceImpl implements ICommentService{
	private ICommentDao dao = new CommentDaoImpl();

	/**
	* Method : insertComment
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 등록 메서드
	*/
	@Override
	public int insertComment(CommentVO cv) {
		return dao.insertComment(cv);
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
	public List<CommentVO> selectComment(int postId) {
		return dao.selectComment(postId);
	}
}
