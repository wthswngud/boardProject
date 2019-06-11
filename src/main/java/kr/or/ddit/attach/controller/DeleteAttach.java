package kr.or.ddit.attach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteAttach")
public class DeleteAttach extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteAttach.class);
	private IAttachService service = null;
	
	@Override
	public void init() throws ServletException {
		service = new AttachServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("첨부파일 삭제 doPost");
		String result = request.getParameter("attachDelete");
		String result2 = request.getParameter("postId");
		String result3 = request.getParameter("boardId");
		logger.debug("attachId : " + result);
		
		int postId = Integer.parseInt(result2);
		int boardId = Integer.parseInt(result3);
		
		int attachId = Integer.parseInt(result);
		
		int deleteResult = service.deleteAttach(attachId);
		
		if(deleteResult>0){
			logger.debug("첨부파일 삭제완료");
			request.setAttribute("postId", postId);
			request.setAttribute("boardId", boardId);
			
			request.getRequestDispatcher("/read").forward(request, response);
		}
	}
}
