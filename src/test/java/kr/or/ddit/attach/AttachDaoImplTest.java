package kr.or.ddit.attach;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.attach.dao.AttachDaoImpl;
import kr.or.ddit.attach.dao.IAttachDao;
import kr.or.ddit.attach.model.AttachVO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachDaoImplTest {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachDaoImplTest.class);
	private IAttachDao dao = new AttachDaoImpl();

	/**
	* Method : selectAttach
	* 작성자 : PC19
	* 변경이력 :
	* @param attachId
	* @return
	* Method 설명 : 게시글 아이디를 입력받아 해당 하는 첨부파일 리스트를 반환
	*/
	@Test
	public void selectAttachTest() {
		/***Given***/
		
		/***When***/
		List<AttachVO> list = dao.selectAttach(112);
		/***Then***/
		assertEquals(3, list.size());
	}
}
