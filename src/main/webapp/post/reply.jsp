<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<script src="${pageContext.request.contextPath}/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${pageContext.request.contextPath}/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if($("#userId").val()==null){
			alert("로그인한 회원만 답글을 작성할 수 있습니다.");
			return;
		}
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
	
	var count =0;
	$("#add").on("click", function(){
		count+=1;
		if(count>4){
			alert("첨부파일은 최대 5개까지 가능합니다");
			return;
		}
		var res = "<label>첨부파일</label>&nbsp;&nbsp; <input id='attach' type='file'id='profile' name='profile' class='btn btn-default'/> <br>";
		$("#attach1").append(res);
	})
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}
	return true;
}

</script>
<%@include file="/common/basicLib.jsp" %>
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
					<h1 class="blog-title">게시물 작성</h1>
				</div>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="blog-post">
							<hr>
							<div id="board">
								<input id="userId" type="hidden" value="${userVO.userid}"/>
								<form action="${pageContext.request.contextPath}/reply" method="post" id="frm">
									<input type="hidden" name="boardId" value="${boardId}"/>
									<input type="hidden" name="postId" value="${postId}"/>
								
									<label>제목</label>&nbsp;&nbsp; <input type="text" id="title" name="title" /><br><br>

									<textarea name="smarteditor" id="smarteditor" rows="10"
										cols="100" style="width: 766px; height: 412px;"></textarea>
									<br>
									<div id="attach1">
										<label>첨부파일</label>&nbsp;&nbsp; <input id="attach" type="file"
											id="profile" name="profile" class="btn btn-default"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input id="add" name="add" type="button" class="btn btn-default" value="첨부파일 추가"/><br>
									</div>
								</form>
								<input type="button" id="savebutton" value="서버전송" maxlength="5"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>