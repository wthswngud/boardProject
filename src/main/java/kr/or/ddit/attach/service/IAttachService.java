package kr.or.ddit.attach.service;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

public interface IAttachService {
	/**
	* Method : insertAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 경로 추가
	*/
	int insertAttach(AttachVO av);
	
	/**
	* Method : updateAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 수정
	*/
	int updateAttach(AttachVO av);
	
	/**
	* Method : selectAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 게시글 아이디를 입력받아 해당 하는 첨부파일 리스트를 반환
	*/
	List<AttachVO> selectAttach(int postId);
	
	/**
	* Method : selectVO
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 id를 받아서 해당 vo를 반환한다.
	*/
	AttachVO selectVO(int attachId);
	
	/**
	* Method : deleteAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 삭제 쿼리문
	*/
	int deleteAttach(int attachId);
}
