package kr.or.ddit.post;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDaoImplTest {
	private IPostDao dao = new PostDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(PostDaoImplTest.class);

	@Test
	public void postListTest() {
		/***Given***/
		int boardId = 1;

		/***When***/
		List<PostVO> list = dao.postList(boardId);
		
		/***Then***/
		logger.debug("list : {}", list);
	}
	
	@Test
	public void getPagingTest(){
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);

		/***When***/
		List<PostVO> list = dao.getPaging(pageVO);
		
		/***Then***/
		logger.debug("list : {}", list);
		assertEquals(2, list.size());
		//19.06.06 현재 2개밖에 없음
	}
	
	@Test
	public void postCountTest(){
		/***Given***/
		

		/***When***/
		int pageCnt = dao.postCount(1);
		
		/***Then***/
		assertEquals(32, pageCnt);
	}
	
	
	@Test
	public void postPagingList(){
		/***Given***/
		int boardid = 1;
		int page = 1;
		int pageSize = 10;

		/***When***/
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardid", boardid);
		map.put("page", page);
		map.put("pageSize", pageSize);
		
		List<PostVO> list = dao.postPagingList(map);
		

		/***Then***/
		assertEquals(2, list.size());
		
	}
	
	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 게시판 id와 게시글id로 해당 게시글 조회
	*/
	@Test
	public void searchPostIdTest(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setBoardid(18);
		postVO.setPostid(1);

		/***When***/
		PostVO postVO1 = dao.searchPostId(postVO);
		
		/***Then***/
		logger.debug("postVO : {}", postVO1);
	}
	
	
	@Test
	public void insertPostTest(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setBoardid(1);
		postVO.setContentcul("게시글 내용입니다.");
		postVO.setPostid(1);
		postVO.setPostid2(1);
		postVO.setTitlecul("게시글입니다.");
		postVO.setUserid("brown");

		/***When***/
		int result = dao.insertPost(postVO);
		/***Then***/
		assertEquals(43, result);
	}
	
	@Test
	public void postIdMaxValue(){
		/***Given***/

		/***When***/
		int result = dao.postIdMaxValue();

		/***Then***/
		assertEquals(59, result);
	}
	
	/**
	* Method : updatePost
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 :해당 게시글의 삭제여부를 삭제로 바꾸는 메서드
	*/
	@Test
	public void updatePostTest(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setPostid(1);
		postVO.setUserid("brown");

		/***When***/
		int result = dao.updatePost(postVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : selectParentTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 :
	*/
	@Test
	public void selectParentTest(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setBoardid(1);
		postVO.setPostid(76);

		/***When***/
		PostVO postVO1 = dao.selectParent(postVO);
		
		/***Then***/
		logger.debug("PostVO : {}", postVO1);
	}
	
	/**
	* Method : insertReply
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 답글 게시글 등록
	*/
	@Test
	public void insertReplyTest(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setBoardid(1);
		postVO.setPostid2(2);
		postVO.setGroupSeq(1);
		postVO.setTitlecul("답글 테스트 코드");
		postVO.setContentcul("테스트 코드");
		postVO.setUserid("brown");
		

		/***When***/
		int result = dao.insertReply(postVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : updatePostContent
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 수정 메서드
	*/
	@Test
	public void updatePostContent(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setBoardid(18);
		postVO.setPostid(82);
		postVO.setContentcul("테스트 중입니다.");
		postVO.setTitlecul("수정 테스트 중");

		/***When***/
		int result = dao.updatePostContent(postVO);

		/***Then***/
		assertEquals(1, result);
	}
}
