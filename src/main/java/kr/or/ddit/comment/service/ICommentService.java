package kr.or.ddit.comment.service;

import java.util.List;

import kr.or.ddit.comment.model.CommentVO;
import kr.or.ddit.post.model.PostVO;

public interface ICommentService {
	/**
	* Method : insertComment
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 등록 메서드
	*/
	int insertComment(CommentVO cv);
	
	/**
	* Method : selectComment
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 해당 게시글의 댓글을 조회하는 메서드
	*/
	List<CommentVO> selectComment(CommentVO cv);
}
