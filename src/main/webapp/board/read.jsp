<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>사용자 상세조회</title>
<%@include file="/common/basicLib.jsp"%>
<style>
	#button, #delete, #comment, #reply{
		float: right;
	}
	textarea.autosize { min-height: 50px; border:none; resize: none;}
	
</style>
<script type="text/javascript">
	function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
	
	$(document).ready(function(){
		var id = $("#labelId").text();
		$("#button").on("click", function(){
			$("#userId").val(id);
			
			$("#frm").submit();
		})
		
		$("#delete").on("click", function(){
			$("#frmDelete").submit();
		})
		//답글
		$("#reply").on("click", function(){
			$("#frmComment").submit();
		})
		
		//댓글
		$("#comment").on("click", function(){
			if($("#commentText").val()==""){
				alert("댓글을 입력해 주세요.");
				return;
			}
			$("#commentFrm").submit();
		})
	})
	
	
	function autoTextarea(obj,limit) {
        obj.style.height = "1px";
        if (limit >= obj.scrollHeight) obj.style.height = (20+obj.scrollHeight)+"px";
        else obj.style.height = (20+limit)+"px";
	}
</script>
</head>
<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 상세 페이지</h2>

						<form id="frm" action="${pageContext.request.contextPath}/updatePost" class="form-horizontal" role="form" method="post">
							<input type="hidden" name="boardId" value="${postVO.boardid}"/>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<label for="userId" class="control-label">${postVO.titlecul}</label>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">글내용</label>
								<div class="col-sm-10">
									<textarea class="autosize" style="width: 766px;" onkeydown="resize(this)" onkeyup="resize(this)" readonly></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									<label class="control-label"></label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">작성일</label>
								<div class="col-sm-10">
									<label class="control-label">
									<fmt:formatDate value="${postVO.dtcreation}" pattern="yyyy-MM-dd hh:mm:ss" var="date"/>
									${date}</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:if test="${userVO.userid eq boardVO.userid}">
										<button id="delete" type="button" class="btn btn-default">삭제</button>
									</c:if>
									<c:if test="${userVO!=null}">
										<button id="button" type="button" class="btn btn-default">수정</button>
										<button id="reply" type="button" class="btn btn-default">답글</button>
									</c:if>
								</div>
							</div>
						</form>
						
						<!-- 댓글 저장 버튼 클릭시 -->
						<form id="commentFrm" action="${pageContext.request.contextPath}/comment" class="form-horizontal" role="form">
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">댓글</label>
								<div class="col-sm-10">
									<c:forEach items="${commentList}" var="commentVO">
										<fmt:formatDate value="${commentVO.dtcreation}" var="date"/> 
										<label>${commentVO.contentcul}   [${commentVO.userid}/${date}]</label><br>
									</c:forEach>
									<input id="commentText" type="text" name="comment"/>
									<input type="hidden" name="boardId" value="${postVO.boardid}"/>
									<input type="hidden" name="userId" value="${userVO.userid}"/>
									<input type="hidden" name="postId" value="${postVO.postid}"/>
									<button id="comment" type="button" class="btn btn-default">댓글저장</button>
								</div>
							</div>
						</form>
						
						
						<!-- 삭제버튼 클릭시 -->
						<form id="frmDelete" action="${pageContext.request.contextPath}/delete" method="post" class="form-horizontal" role="form">
							<input id="delete" type="hidden" name="postId" value="${postVO.postid}"/>
							<input id="boardId" type="hidden" name="boardId" value = "${boardVO.boardid}"/>
						</form>
						
						<!-- 답글버튼 클릭시 -->
						<form id="frmComment" action="${pageContext.request.contextPath}/SE2/index.jsp" method="post" class="form-horizontal" role="form">
							<input id="delete" type="hidden" name="postId" value="${postVO.postid}"/>
							<input id="boardId" type="hidden" name="boardId" value = "${boardVO.boardid}"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>