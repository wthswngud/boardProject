package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;

public interface IPostService {
	/**
	* Method : postList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 입력받은 게시판 아이디로 
	* 			   해당 게시글 리스트 반환
	*/
	List<PostVO> postList(int boardId);
	
	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 해당하는 페이지 리스트를 반환하는 메서드
	*/
	Map<String, Object> getPaging(PageVO pageVO);
	
	/**
	* Method : usersCount
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 전체수 조회
	*/
	int postCount();
	
	/**
	* Method : postPagingList
	* 작성자 : PC19
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 페이징 처리된 post리스트를 반환하는 메서드
	*/
	List<PostVO> postPagingList(Map<String, Integer> map);
	
	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 게시판 id와 게시글id로 해당 게시글 조회
	*/
	public PostVO searchPostId(PostVO postVO);
	
	/**
	* Method : insertPost
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 등록 메서드
	*/
	int insertPost(PostVO postVO);
	
	/**
	* Method : updatePost
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 :해당 게시글의 삭제여부를 삭제로 바꾸는 메서드
	*/
	int updatePost(PostVO postVO);
	
	/**
	* Method : selectCurrent
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 현재 posts1_seq 시퀀스의 값을 반환하는 메서드
	*/
	int selectCurrent();
}
