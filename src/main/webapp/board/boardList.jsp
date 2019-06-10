<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>${boardName}</title>
<style>
#board {
	margin: 0px auto;
}

.btn {
	float: right;
}

#postid{
	display: none;
}
</style>
<script>
	$(document).ready(function() {
		$(".tr").on("click", function() {
			var title = $(this).find(".title").text();
			
			if($("#No").val()=="No"){
				alert("회원만 조회가능합니다.");				
			}else{
				if (title != '삭제된 게시글 입니다.') {
					var postId = $(this).find(".postid").text();
					
					$("#postId").val(postId);
					$("#frm").submit();
				} else {
					alert('삭제된 게시글 입니다.');
				}
			}
		})
		
		$(".btn").on("click", function(){
			if($("#No").val()=="No"){
				alert("회원만 등록 가능합니다.");				
			}else{
				$("#frm2").submit();
			}
		})
	})
</script>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
</head>

<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="blog-header">
					<h1 class="blog-title">${boardName}</h1>
				</div>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="blog-post">
							<hr>
							<div id="board">

								<form id="frm" action="${pageContext.request.contextPath}/read"
									method="get">
									<input type="hidden" id="boardId" name="boardId"
										value="${boardid}" /> <input type="hidden" id="postId"
										name="postId" />

									<table class="table table-striped" id="table">
										<tr>
											<th>게시글 번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>작성일시</th>
										</tr>
										<c:choose>
											<c:when test="${sessionScope.USER_INFO==null}">
												<input type="hidden" id="No" value="No"/>
												<c:forEach items="${postList}" var="boardVO">
													<c:choose>
														<c:when test="${boardVO.deletecul == 'N'}">
															<tr class="tr">
																<td>${boardVO.rn}</td>
																<td class="title">
																	<c:forEach begin="1" end="${boardVO.lv}">
																		<c:if test="${boardVO.lv>0}">
																			&nbsp;&nbsp;
																	</c:if>
																	</c:forEach>
																	${boardVO.titlecul}
																</td>
																<td>${boardVO.userid}</td>
																<td><fmt:formatDate value="${boardVO.dtcreation}"
																		pattern="yyyy-MM-dd hh:mm:ss" var="date" />${date}</td>
																<td class="postid" id="postid">${boardVO.postid}</td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr class="tr">
																<td>${boardVO.rn}</td>
																<c:forEach begin="1" end="${boardVO.lv}" step="1">
																	<c:if test="${boardVO.lv>0}">
																		&nbsp;&nbsp;
																	</c:if>
																</c:forEach>
																<td class="title">삭제된 게시글 입니다.</td>
																<td>${boardVO.userid}</td>
																<td><fmt:formatDate value="${boardVO.dtcreation}"
																		pattern="yyyy-MM-dd hh:mm:ss" var="date" />${date}</td>
																<td class="postid" id="postid">${boardVO.postid}</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach items="${postList}" var="boardVO">
													<c:choose>
														<c:when test="${boardVO.deletecul == 'N'}">
															<tr class="tr">
																<td>${boardVO.rn}</td>
																<td class="title">
																	<c:forEach begin="1" end="${boardVO.lv}">
																		<c:if test="${boardVO.lv>0}">
																			&nbsp;&nbsp;
																	</c:if>
																	</c:forEach>
																	${boardVO.titlecul}
																</td>
																<td>${boardVO.userid}</td>
																<td><fmt:formatDate value="${boardVO.dtcreation}"
																		pattern="yyyy-MM-dd hh:mm:ss" var="date" />${date}</td>
																<td class="postid" id="postid">${boardVO.postid}</td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr class="tr">
																<td>${boardVO.rn}</td>
																<c:forEach begin="1" end="${boardVO.lv}" step="1">
																	<c:if test="${boardVO.lv>0}">
																		&nbsp;&nbsp;
																	</c:if>
																</c:forEach>
																<td class="title">삭제된 게시글 입니다.</td>
																<td>${boardVO.userid}</td>
																<td><fmt:formatDate value="${boardVO.dtcreation}"
																		pattern="yyyy-MM-dd hh:mm:ss" var="date" />${date}</td>
																<td class="postid" id="postid">${boardVO.postid}</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</table>
								</form>

								<div class="text-center">
									<ul class="pagination">

										<c:set var="pageVO" value="${pageVO}" scope="request" />

										<c:if test="${pageVO.page==1}">
											<li class="disabled"><span><</span></li>
										</c:if>
										<c:if test="${pageVO.page!=1 }">
											<li><a
												href="${pageContext.request.contextPath}/boardList?page=${1}&boardName=${boardName}&boardId=${boardid}"><span><</span></a></li>
										</c:if>

										<c:if test="${pageVO.page==1}">
											<li class="disabled"><span>«</span></li>
										</c:if>
										<c:if test="${pageVO.page!=1 }">
											<li><a
												href="${pageContext.request.contextPath}/boardList?page=${pageVO.page-1}&boardName=${boardName}&boardId=${boardid}"><span>«</span></a></li>
										</c:if>

										<c:forEach begin="1" end="${paginationSize}" step="1" var="i"
											varStatus="String">
											<c:if test="${pageVO.page==i}">
												<li class=active><span>${i}</span></li>
											</c:if>
											<c:if test="${pageVO.page!=i}">
												<li><a
													href="${pageContext.request.contextPath}/boardList?page=${i}&boardName=${boardName}&boardId=${boardid}">${i}</a></li>
											</c:if>
										</c:forEach>

										<c:choose>
											<c:when test="${pageVO.page == paginationSize}">
												<li class="disabled"><span>»</span></li>
											</c:when>
											<c:otherwise>
												<li><a
													href="${pageContext.request.contextPath}/boardList?page=${pageVO.page+1}&boardName=${boardName}&boardId=${boardid}"><span>»</span></a></li>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${pageVO.page == paginationSize}">
												<li class="disabled"><span>></span></li>
											</c:when>
											<c:otherwise>
												<li><a
													href="${pageContext.request.contextPath}/boardList?page=${paginationSize}&boardName=${boardName}&boardId=${boardid}"><span>></span></a></li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>

								<form id="frm2" action="${pageContext.request.contextPath}/writeView">
									<input type="hidden" name="boardId" value="${boardid}"/>
									<button class="btn" type="button">글쓰기</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
