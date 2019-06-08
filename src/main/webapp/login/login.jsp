<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 500에러 발생시 대체하는 에러 페이지로 이동하기 위한 코드 -->
<%-- <% Integer.parseInt("asdfasdf");%> --%>
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

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
	<!-- 수업시간에 한거 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src = "${pageContext.request.contextPath}/js/js.cookie.js"></script>
	<script>
		$(document).ready(function(){
			// 문서로딩이 완료되고 나서 실행되는 부분
			// rememberme checkbox
			// 1. rememberme cookie가 있는지?, 있으면 값이 true인지 확인
			// 1-1. rememberme가 true이면 input id="rememberme" 체크박스를 체크
			
			
			
			$("#signinBtn").on("click", function(){
				//만약 rememberme 체크박스가 체크되어 있는경우
				//사용자 아이디 값을 userId 쿠키로 저장
				//true 값을 rememberme cookie 값으로 저장
// 				if($("#rememberme").is(":checked")){
// 					setCookie("userId", $("#userId").val(), {expires : 30});
// 					setCookie("rememberme", "true", {expires : 30});
// 				}
				
				//만약 rememberme 체크박스가 해체되어 있는 경우
				//	userId, rememberme cookie 값을 삭제
// 				else{
// 					Cookies.remove("userId");
// 					Cookies.remove("rememberme");
// 				}
				
				//로그인 요청을 서버로 전송
				$("#frm").submit();
			})
			
			var rememberme = Cookies.get("rememberme");
			//getCookie("rememberme")
			
			if(rememberme == "true"){
// 				$("#rememberme").attr("checked", true);
				$("#rememberme").prop("checked", true);
				$("#userId").val(Cookies.get("userId"));
				$("#userPW").focus();
				
				//signin Button 클릭시 실행되는 핸들러
			}else{
// 				$("#rememberme").attr("checked", false);
				$("#rememberme").prop("checked", false);
			}
			
			console.log("document ready");
		});
		
		/*
		$(document).ready(function(){
			console.log("rememberme : " + $("#rememberme").val());
		})
		*/
		
		
		
		//쿠키를 저장
		//expires : 현재날짜로 부터 몇일동안 유효한지 일자(정수)
		function setCookie(cookieName, cookieValue, expires){
			var dt = new Date();
			dt.setDate(dt.getDate() + parseInt(expires));
			
			document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" +
				dt.toGMTString();
		}
		
		//쿠키를 삭제
		function deleteCookie(cookieName){
			setCookie(cookieName, "", -5);
		}
		
		//쿠키 이름에 해당하는 쿠키 값을 조회
		function getCookie(cookieName){
			//document.cookie
			//String[] cookieArray = cookieString.split("; ");
			var cookieArray = document.cookie.split("; ");
			
			//String cookieValue = "";
			var cookieValue = "";
			
			//for(String str : cookieArray){
			for(var i=0; i<cookieArray.length; i++){
				var str = cookieArray[i];
				if(str.startsWith(cookieName+"=")){
					var cookieStr = str.split("=");
					cookieValue = cookieStr[1];
					break;
				}
			}
			
			return cookieValue;
		}
	</script>
  </head>

  <body>

    <div class="container">
										
      <form id = "frm" action = "${pageContext.request.contextPath}/login"
      		class="form-signin"
      		method = "post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userId" class="sr-only">userId</label>
        
        <input type="text" id ="userId" name ="userId" class="form-control" placeholder="Email address" required value="${param.userId}">
        
        <label for="inputPassword"  class="sr-only">Password</label>
        <input type="password" id="userPW" name="userPW" value = "brown1234" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input id = "rememberme" name = "rememberme" type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id = "signinBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>
    </div>
  </body>
</html>
