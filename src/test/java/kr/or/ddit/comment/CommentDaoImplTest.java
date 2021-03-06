package kr.or.ddit.comment;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.comment.dao.CommentDaoImpl;
import kr.or.ddit.comment.dao.ICommentDao;
import kr.or.ddit.comment.model.CommentVO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentDaoImplTest {
	private ICommentDao dao = new CommentDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(CommentDaoImplTest.class);

	@Test
	public void insertCommentTest() {
		/***Given***/
		CommentVO cv = new CommentVO();

		/***When***/
		cv.setPostid(1);
		cv.setUserid("brown");
		cv.setContentcul("테스트3 댓글");
		int result = dao.insertComment(cv);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void selectComment(){
		/***Given***/
		CommentVO cv = new CommentVO();
		cv.setBoardid(18);
		cv.setPostid(89);
		
		/***When***/
		List<CommentVO> list = dao.selectComment(cv);

		/***Then***/
		assertEquals(1, list.size());
		logger.debug("날짜 : " + list.get(0).getDtcreation());
	}
	
	@Test
	public void deleteComment(){
		/***Given***/
		

		/***When***/
		int result = dao.deleteComment(22);

		/***Then***/
		assertEquals(1, result);
	}
}
