<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			
			<a class="navbar-brand" href="#">JSP/SPRING 
			<!-- 사용자가 정상적으로 로그인 했을 경우 : USER_INFO에 있는 name 속성 출력
				 사용자가 로그인하지 않고 메인화면으로 직접 접속했을 경우
				 (localhost/jsp/main.jsp) 접속하지 않은 사용자 입니다. 문구를 표현 -->
				 <c:if test = "${USER_INFO.name!=null}">
				 	${USER_INFO.name}
				 </c:if>
				 <c:if test = "${USER_INFO.name==null}">
				 	접속하지 않은 사용자 입니다.
				 </c:if>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
</nav>