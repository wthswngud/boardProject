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
	#attach{
		display: inline;
	}
	#attach1, #btn{
		float : left;
	}
</style>
<script>
	$(document).ready(function(){
		
	})
</script>

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
					<h1 class="blog-title">게시물 작성</h1>
				</div>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="blog-post">
							<hr>
							<div id="board">
								<form id="create" action="${pageContext.request.contextPath}/createBoard" enctype="multipart/form-data" method="post">
									<label >제목</label>&nbsp;&nbsp;
									<input type = "text" id="name" name="name"/><br><br>
									
									<label >글내용</label><br>
									<textarea rows="10" cols="100" style="width:auto; height:412px;"></textarea><br>
									
									<div id="attach1">
										<label>첨부파일</label>&nbsp;&nbsp;
										<input id="attach" type = "file" id="file" name="file"/><br>
										<input id="add" name="add" type="button" class="btn btn-default" value="첨부파일 추가"/><br>
										
										<button id="btn" class = "btn" type="submit">생성</button>
									</div>
								</form><br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
