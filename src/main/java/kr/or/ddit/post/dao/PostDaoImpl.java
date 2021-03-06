package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.user.model.UserVO;

public class PostDaoImpl implements IPostDao{

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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> list = sqlSession.selectList("post.postList", boardId);
		sqlSession.close();
		return list;
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
	public List<PostVO> getPaging(PageVO pageVO) {
		SqlSession sqlSession =  MyBatisUtil.getSqlSession();
		List<PostVO> list = sqlSession.selectList("post.PagingList", pageVO);
		sqlSession.close();
		return list;
	}
	
	/**
	* Method : usersCount
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	*/
	public int postCount(int boardId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = (int)sqlSession.selectOne("post.postCount", boardId);
		sqlSession.close();
		return postCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> list = sqlSession.selectList("post.postPagingList", map);
		sqlSession.close();
		return list;
	}

	/**
	* Method : searchBoardId
	* 작성자 : PC19
	* 변경이력 :
	* @param postId
	* @return
	* Method 설명 : 게시판 id로 해당 게시판 조회
	*/
	@Override
	public PostVO searchPostId(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVO postVO1 = sqlSession.selectOne("post.searchPostId", postVO);
		sqlSession.close();
		return postVO1;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("post.insertPost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int updatePost(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("post.updatePost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : postIdMaxValue
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 제일 큰 게시물 번호를 반환하는 메서드
	*/
	@Override
	public int postIdMaxValue() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.selectOne("post.postIdMaxValue");
		sqlSession.close();
		return result;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVO postVO1 = sqlSession.selectOne("post.selectParent", postVO);
		sqlSession.close();
		return postVO1;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("post.insertReply", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : updatePostContent
	* 작성자 : PC19
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 수정 메서드
	*/
	@Override
	public int updatePostContent(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("post.updatePostContent", postVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}