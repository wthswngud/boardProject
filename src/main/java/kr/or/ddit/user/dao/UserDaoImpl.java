package kr.or.ddit.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public class UserDaoImpl implements IuserDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사정자 전체 조회
	*/
	public static void main(String[] args) {
		/***Given***/
		IuserDao userDao = new UserDaoImpl();
		/***When***/
		List<UserVO> userList = userDao.userList();
		/***Then***/
		logger.debug("userList : {}", userList+"\n");
		
		
		
		/***Given***/
		String userId = "brown";
		/***When***/
		UserVO userVO = userDao.getUser(userId);
		/***Then***/
		logger.debug("userVO : {}", userVO);

	}
	
	
	@Override
	public List<UserVO> userList() {
		SqlSession sqlSession =  MyBatisUtil.getSqlSession();
		List<UserVO> userList = sqlSession.selectList("user.userList");
		sqlSession.close();
		return userList;
	}


	/**
	* Method : getUser
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession =  MyBatisUtil.getSqlSession();
		UserVO userVO = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return userVO;
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
	public List<UserVO> getPaging(PageVO pageVO) {
		SqlSession sqlSession =  MyBatisUtil.getSqlSession();
		List<UserVO> list = sqlSession.selectList("user.userPagingList", pageVO);
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
	public int usersCount() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int usersCnt = (int)sqlSession.selectOne("user.usersCount");
		sqlSession.close();
		return usersCnt;
	}

	@Override
	public int insertUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.insert("user.insertUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}


	@Override
	public int deleteUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public int modiUser(UserVO userVO){
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = sqlSession.update("user.modiUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 리스트
	*/
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @param userVO
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass", userVO);
	}
}