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
		int pageCnt = dao.postCount();
		
		/***Then***/
		logger.debug("pageCnt : " + pageCnt);
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
		postVO.setBoardid(1);
		postVO.setPostid(2);

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
		assertEquals(1, result);
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
	* Method : selectCurrent
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 현재 posts1_seq 시퀀스의 값을 반환하는 메서드
	*/
	@Test
	public void selectCurrent(){
		/***Given***/
		

		/***When***/
		int result = dao.selectCurrent();
		
		/***Then***/
		assertEquals(23, result);
	}
}
