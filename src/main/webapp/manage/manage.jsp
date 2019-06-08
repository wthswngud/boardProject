<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>게시판 관리</title>
<style>
	#board{
		margin: 0px auto;
	}
	#a{
		width : 50px;
	}
</style>
<script>
	$(document).ready(function(){
		var msg = '${msg}';
		
		if(msg != ''){
			alert('${msg}');
		}
		
// 			select = $(this).children("option:selected").val();
		
		
		$(".btn").on("click", function(){
			$(".select2").change(function(){
				$(this).children("option:selected").val();
			})
			$(this).parent().submit();
		})
	})
</script>

<!-- css, js -->
<%@include file="/common/basicLib.jsp" %>
</head>

<body>
	<!-- header -->
	<%@include file="/common/header.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/common/left.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="blog-header">
					<h1 class="blog-title">게시물 관리</h1>
				</div>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="blog-post">
							<hr>
							<div id="board">
								<form id="create" action="${pageContext.request.contextPath}/createBoard">
									<label >게시판 추가</label>&nbsp;&nbsp;
									<input type = "text" id="name" name="name" value="${list.name}"/>
									<select id="select1" name="SelectValue1" onchange="chageLangSelect()" form="create">
										<option value="Y">사용</option>
										<option value="N">미사용</option>
									</select>
									<button class = "btn" type="submit">생성</button>
								</form><br>
								
								<c:forEach items="${allList}" var="list">
									<form action="${pageContext.request.contextPath}/update" method="post" id="update${list.boardid}">
										<input type="hidden" id = "hidden" name="name" value="${list.boardid}">
										
										<label>게시판 이름</label>&nbsp;&nbsp;
										<input type = "text" id="boardNM" name="boardNM" value="${list.name}"/>
										<select id="select2" class="select2" name="SelectValue2" form="update${list.boardid}">
											<c:choose>
												<c:when test="${list.use_yn == 'Y'}">
													<option value="Y" selected="selected">사용</option>
													<option value="N">미사용</option>
												</c:when>
											<c:otherwise>
												<option value="Y">사용</option>
												<option value="N"  selected="selected">미사용</option>
											</c:otherwise>
										</c:choose>
										</select>
										<button class = "btn" type="button">수정</button>
									</form><br>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
