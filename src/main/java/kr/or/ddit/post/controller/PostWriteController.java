package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/write")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024)
public class PostWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostWriteController.class);
	private IPostService postService = null;
	private IAttachService attachService = null;
	
	
       
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		attachService = new AttachServiceImpl();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//현재 게시물 번호중 제일 큰값을 조회하는 메서드
		String title = request.getParameter("title");
		String result = request.getParameter("boardId");
		String content = request.getParameter("smarteditor");
		
		int boardId = Integer.parseInt(result);
		
		PostVO postVO = new PostVO();
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		String str = null;
		String fileName = null;
		
		logger.debug("content : " + content);
		
		postVO.setTitlecul(title);
		postVO.setContentcul(content);
		postVO.setBoardid(boardId);
		postVO.setUserid(userVO.getUserid());
		
		int insertResult1 = postService.insertPost(postVO);
		
		if(insertResult1>0){
			int postId = postService.postIdMaxValue();
			List<AttachVO> attachList = attachService.selectAttach(postId);
			
			//****************지금부터 profile***********************
			for(Part part : request.getParts()){
				if(!part.getName().equals("profile")){
					continue;
				}
				logger.debug("part.getSize() {}", part.getSize());
				logger.debug("part.getcontentType() {}", part.getContentType());
				logger.debug("part.getName() {}", part.getName());
				if(part.getSize()==0){
					logger.debug("파일을 선택하지 않았습니다.");
					continue;
				}else if(part.getSize()>0){
					Collection<String> headerNames = part.getHeaderNames();
					for(String header : headerNames)
						logger.debug("{} : {}", header, part.getHeader(header));
					
					String contentDisposition = part.getHeader("content-disposition");
					//파일 이름 추출하기
					fileName = PartUtil.getFileName(contentDisposition);
					String ext = PartUtil.getExt(fileName);
					
					
					//년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지 확인 하는 작업
					Date dt = new Date();
					SimpleDateFormat yyyySdf = new SimpleDateFormat("yyyy");
					SimpleDateFormat mmSdf = new SimpleDateFormat("MM");
					String yyyy = yyyySdf.format(dt);	// 현재 년도 추출
					String mm = mmSdf.format(dt);		// 현재 월 추출
					
					
					File yyyyFolder = new File("d:\\upload\\"+yyyy);
					//신규년도로 넘어갔을때 해당 년도의 폴더를 생성한다.
					if(!yyyyFolder.exists())
						yyyyFolder.mkdir();
					
					
					
					//월에 해당하는 폴더가 있는지 확인
					File mmFolder = new File("d:\\upload\\2019\\"+mm);
					if(!mmFolder.exists())
						mmFolder.mkdir();
					
					
					String uploadPath = "d:\\upload\\"+yyyy+File.separator+mm;
					File uploadFolder = new File(uploadPath);
					
					
					//파일 디스크에 쓰기
					//UUID
					str = uploadPath+ File.separator + UUID.randomUUID().toString() + ext;
					if(uploadFolder.exists()){
						part.write(str);
						part.delete();
					}else{
						logger.debug("해당 폴더가 존재하지 않습니다.");
					}
					
					AttachVO av = new AttachVO();
					av.setAttachpath(str);
					av.setFilename(fileName);
					av.setPostid(postId);
					
					int insertResult = attachService.insertAttach(av);
					if(insertResult>0){
						logger.debug("첨부파일 등록 성공");
					}
					
				}
			}
			
			//****************************************************
			
			request.setAttribute("attachList", attachList);
			request.setAttribute("postId", postId+"");
			request.setAttribute("boardId", result);
			logger.debug("게시물 제일 큰 값 : " + postId);
			request.getRequestDispatcher("/read").forward(request, response);
		}
	}
}
