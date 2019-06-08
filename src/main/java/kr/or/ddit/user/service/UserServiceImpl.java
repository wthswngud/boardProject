package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;

public class UserServiceImpl implements IuserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private IuserDao userDao = new UserDaoImpl();
	
	/**
	 * Method : userList
	 * 작성자 : 손주형
	 * 변경이력 : 
	 * @return
	 * Method : 사용자 전체 리스트 조회
	 * 
	 */
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}

	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public Map<String, Object> getPaging(PageVO pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVO> userList = userDao.getPaging(pageVO);
		int usersCnt = userDao.usersCount();
		
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVO.getPageSize());
		
		map.put("userList", userList);
		map.put("paginationSize", paginationSize);
		
		return map;
	}
	
	public int insertUser(UserVO dbUser){
		return userDao.insertUser(dbUser);
	}

	
	@Override
	public int deleteUser(String userId) {
		int result = userDao.deleteUser(userId);
		return result;
	}

	@Override
	public int modiUser(UserVO userVO) {
		int result = userDao.modiUser(userVO);
		return result;
	}

	/**
	* Method : encryptPassAllUser
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 일괄 적용 배치
	* 			 : 재적용 하지말것!!!!
	* 			 : 다시 실행하면 암호화된 비밀번호를 다시 암호화 하는것
	*/
	@Override
	public int encryptPassAllUser() {
		if(1==1)
			return 0;
		//0. sql 실행에 필요한 sqlSession 객체를 생성
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		//1. 모든 사용자 정보를 조회(단, 기존 암호화 적용 사용자 제외, brown, userTest)
		List<UserVO> userList = userDao.userListForPassEncrypt(sqlSession);
		//2. 조회된 사용자의 비밀번호를 암호화 적용후 사용자 업데이트
		
		int updateCntSum = 0;
		for(UserVO userVO : userList){
			String encryptPass = KISA_SHA256.encrypt(userVO.getPass());
			userVO.setPass(encryptPass);
			
			int updateCnt = userDao.updateUserEncryptPass(sqlSession, userVO);
			updateCntSum += updateCnt;
			//비정상
			if(updateCnt != 1){
				sqlSession.rollback();
				break;
			}
		}
		
		//3. sqlSession 객체를 commit
		sqlSession.commit();
		sqlSession.close();
		
		return updateCntSum;
	}
	
	public static void main(String[] args) {
		IuserService service = new UserServiceImpl();
		int updateCnt = service.encryptPassAllUser();
		logger.debug("updateCnt : {}" + updateCnt);
	}
}