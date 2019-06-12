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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/reply")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024)
public class ReplyPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyPostController.class);
    private IPostService postService = null;
    private IBoardService boardService = null;
    private IAttachService attachService = null;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
		attachService = new AttachServiceImpl();
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		logger.debug("답글(Reply) doGet");
		
		String result = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}
		
		int postId = Integer.parseInt(result);
		int boardId = Integer.parseInt(result2);
		
		
		List<PostVO> postList = (List<PostVO>) getServletContext().getAttribute("postList");
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		
		request.setAttribute("postId", postId);
		request.setAttribute("boardId", boardId);
		request.setAttribute("postList", postList);
		request.setAttribute("postVO", postVO);
		request.getRequestDispatcher("/common/left.jsp");
		request.getRequestDispatcher("/post/reply.jsp").forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("답글(Reply) doPost");
		
		String result = request.getParameter("postId");
		String result2 = request.getParameter("boardId");
		String title = request.getParameter("title");
		String smarteditor = request.getParameter("smarteditor");
		String profile = request.getParameter("profile");
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		if(userVO == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}
		
		int postId = Integer.parseInt(result);
		int boardId = Integer.parseInt(result2);
		
		logger.debug("postId : " + postId);
		logger.debug("boardId : " + boardId);
		
		
		
		List<PostVO> postList = (List<PostVO>) getServletContext().getAttribute("postList");
		PostVO postVO = new PostVO();
		postVO.setBoardid(boardId);
		postVO.setPostid(postId);
		postVO.setPostid2(postId);
		postVO.setUserid(userVO.getUserid());
		postVO.setTitlecul(title);
		postVO.setContentcul(smarteditor);
		
		
		PostVO postVO1 = postService.selectParent(postVO);
		
		logger.debug("postVO1 : {}", postVO1);
		
		postVO.setGroupSeq(postVO1.getGroupSeq());
		
		int insertResult = postService.insertReply(postVO);
		
		//*********첨부파일***************
		AttachVO av = null;
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
				
				av = new AttachVO();
				logger.debug("str : " + str);
				av.setAttachpath(str);
				av.setFilename(fileName);
				av.setPostid(postService.postIdMaxValue());
				
				int insertResult1 = attachService.insertAttach(av);
				if(insertResult1>0){
					logger.debug("첨부파일 등록 성공");
				}
			}
		}
		//*********첨부파일***************
				
		if(insertResult>0){
			BoardVO boardVO = boardService.searchBoardId(boardId);
			
			request.setAttribute("boardName", boardVO.getName());
			
			logger.debug("답변 등록 성공");
			
			request.setAttribute("boardId", boardId);
			request.setAttribute("postList", postList);
			request.getRequestDispatcher("/common/left.jsp");
			request.getRequestDispatcher("/boardList").forward(request, response);
		}
	}
}
