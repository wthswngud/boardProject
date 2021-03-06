package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVO;

public class PostServiceImpl implements IPostService{
	private IPostDao dao = new PostDaoImpl();
	
	/**
	* Method : postList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 입력받은 게시판 아이디로 
	* 			   해당 게시글 리스트 반환
	*/
	@Override
	public List<PostVO> postList(int boardId) {
		return dao.postList(boardId);
	}

	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 해당하는 페이지 리스트를 반환하는 메서드
	*/
	@Override
	public Map<String, Object> getPaging(int boardId, PageVO pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<PostVO> postList = dao.getPaging(pageVO);
		int postCnt = dao.postCount(boardId);
		
		
		int paginationSize = (int)Math.ceil((double)postCnt/pageVO.getPageSize());
		
		map.put("postList", postList);
		map.put("paginationSize", paginationSize);
		
		return map;
	}

	/**
	* Method : usersCount
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 전체수 조회
	*/
	@Override
	public int postCount(int boardId) {
		return dao.postCount(boardId);
	}

	/**
	* Method : postPagingList
	* 작성자 : PC19
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 페이징 처리된 post리스트를 반환하는 메서드
	*/
	@Override
	public List<PostVO> postPagingList(Map<String, Integer> map) {
		return dao.postPagingList(map);
	}

	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 게시판 id와 게시글id로 해당 게시글 조회
	*/
	@Override
	public PostVO searchPostId(PostVO postVO) {
		return dao.searchPostId(postVO);
	}

	/**
	* Method : insertPost
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 등록 메서드
	*/
	@Override
	public int insertPost(PostVO postVO) {
		return dao.insertPost(postVO);
	}

	/**
	* Method : updatePost
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 :해당 게시글의 삭제여부를 삭제로 바꾸는 메서드
	*/
	@Override
	public int updatePost(PostVO postVO) {
		return dao.updatePost(postVO);
	}

	@Override
	public int postIdMaxValue() {
		return dao.postIdMaxValue();
	}

	/**
	* Method : selectGroupSeq
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 부모 게시글의 시퀀스번호를 받아서 부모정보 알아내기
	*/
	@Override
	public PostVO selectParent(PostVO postVO) {
		return dao.selectParent(postVO);
	}

	/**
	* Method : insertReply
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 답글 게시글 등록
	*/
	@Override
	public int insertReply(PostVO postVO) {
		return dao.insertReply(postVO);
	}

	@Override
	public int updatePostContent(PostVO postVO) {
		return dao.updatePostContent(postVO);
	}
}
