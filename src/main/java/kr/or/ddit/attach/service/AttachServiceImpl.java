package kr.or.ddit.attach.service;

import java.util.List;

import kr.or.ddit.attach.dao.AttachDaoImpl;
import kr.or.ddit.attach.dao.IAttachDao;
import kr.or.ddit.attach.model.AttachVO;

public class AttachServiceImpl implements IAttachService{
	private IAttachDao dao = new AttachDaoImpl();
	/**
	* Method : insertAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 경로 추가
	*/
	@Override
	public int insertAttach(AttachVO av) {
		return dao.insertAttach(av);
	}

	/**
	* Method : updateAttach
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일 수정
	*/
	@Override
	public int updateAttach(AttachVO av) {
		return dao.updateAttach(av);
	}

	/**
	* Method : selectAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 게시글 아이디를 입력받아 해당 하는 첨부파일 리스트를 반환
	*/
	@Override
	public List<AttachVO> selectAttach(int postId) {
		return dao.selectAttach(postId);
	}

	/**
	* Method : selectVO
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 id를 받아서 해당 vo를 반환한다.
	*/
	@Override
	public AttachVO selectVO(int attachId) {
		return dao.selectVO(attachId);
	}

	/**
	* Method : deleteAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 첨부파일 삭제 쿼리문
	*/
	@Override
	public int deleteAttach(int attachId) {
		return dao.deleteAttach(attachId);
	}
}
