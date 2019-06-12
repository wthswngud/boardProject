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
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updatePost")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024)
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdatePostController.class);
    private IPostService postService = null;
    private IAttachService attachService = null;
	

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		attachService = new AttachServiceImpl();
	}

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("게시글 수정 doGet");
		String result = request.getParameter("boardId");
		String result2 = request.getParameter("postId");
		
		int boardId = Integer.parseInt(result);
		int postId = Integer.parseInt(result2);
		
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		
		PostVO postVO2 = postService.searchPostId(postVO);
		List<AttachVO> attachList = attachService.selectAttach(postId);
		
		
		request.setAttribute("boardList", getServletContext().getAttribute("boardList"));
		request.setAttribute("postVO", postVO2);
		request.setAttribute("attachList", attachList);
		request.getRequestDispatcher("/post/update.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("게시글 수정 doPost");
		String result = request.getParameter("boardId");
		String result2 = request.getParameter("postId");
		String content = request.getParameter("smarteditor");
		String title = request.getParameter("title");
		
		logger.debug("boardId : " + result);
		logger.debug("postId : " + result2);
		logger.debug("content : " + content);
		logger.debug("title : " + title);
		
		int boardId = Integer.parseInt(result);
		int postId = Integer.parseInt(result2);
		
		String[] deleteId = request.getParameterValues("deleteId");
		if(deleteId == null) {
			logger.debug("deleteId is null");
		} else {
			logger.debug("deleteId is not null");
		}
		if(deleteId!=null) {
			for(int i=0; i<deleteId.length; i++){
				if(!(deleteId[i].equals(""))){
					logger.debug("deleteId : " + deleteId[0]);
					int fileId = Integer.parseInt(deleteId[i]);
					logger.debug("조건 안에는 들어옴");
					int deleteResult = attachService.deleteAttach(fileId);
					if(deleteResult>0){
						logger.debug("첨부파일 삭제 성공");
					}
				}
			}
		}
		
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		postVO.setContentcul(content);
		postVO.setTitlecul(title);
		
		int updateResult = postService.updatePostContent(postVO);
		
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
				String fileName = PartUtil.getFileName(contentDisposition);
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
				String str = uploadPath+ File.separator + UUID.randomUUID().toString() + ext;
				if(uploadFolder.exists()){
					part.write(str);
					part.delete();
				}else{
					logger.debug("해당 폴더가 존재하지 않습니다.");
				}
				
				AttachVO av = new AttachVO();
				av.setAttachpath(str);
				av.setFilename(fileName);
				av.setPostid(postService.postIdMaxValue());
				
				int insertResult = attachService.insertAttach(av);
				if(insertResult>0){
					logger.debug("첨부파일 등록 성공");
				}
			}
		}
		
		//****************************************************
		
		List<AttachVO> list = attachService.selectAttach(postId);
		
		logger.debug("updateResult : " + updateResult);
		
		if(updateResult>0){
			logger.debug("수정 성공");
			request.setAttribute("boardId", boardId);
			request.setAttribute("postId", postId);
			request.setAttribute("postVO", postVO);
			request.setAttribute("attachList", list);
			request.getRequestDispatcher("/read").forward(request, response);
		}
	}
}
