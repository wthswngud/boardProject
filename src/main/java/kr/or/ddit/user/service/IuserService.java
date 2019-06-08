package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IuserService {
	
	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	List<UserVO> userList();
	
	/**
	* Method : getUser
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVO getUser(String userId);
	
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
	* Method : insertUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVO userVO);

	/**
	* Method : deleteUser
	* 작성자 : PC19
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 :사용자 삭제
	*/
	int deleteUser(String string);
	
	/**
	* Method : modiUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 수정
	*/
	int modiUser(UserVO userVO);
	
	/**
	* Method : encryptPassAllUser
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 일괄 적용 배치
	*/
	int encryptPassAllUser();
	
}