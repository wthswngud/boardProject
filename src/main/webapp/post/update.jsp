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
<style>
	#deleteBtn{
		float: right;
	}
	
	img{
		width: 20px;
		height: 20px;
	}
	
	#attach{
		display: inline;
	}
	
	#add{
		float: right;
	}
</style>
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
			alert("로그인한 회원만 게시물을 작성할 수 있습니다.");
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
	
	
	$("#add").on("click", function(){
		if($("table tr").length==5){
			alert("첨부파일은 최대 5개까지 가능합니다");
			return;
		}
		var res =  "<tr><td><input id='attach' type='file' id='profile' name='profile' class='btn btn-default'/></td></tr>";
		$("table").append(res);
	})
	
	$("button[name='deleteBtn']").on("click",function(){
		var id = $(this).parent().prev().prev().children().val();
		var value = $(this).parent().next().children().val(id);
		
		$(this).parent().parent().remove();
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
					<h1 class="blog-title">게시물 수정</h1>
				</div>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="blog-post">
							<hr>
							<div id="board">
								<input id="userId" type="hidden" value="userId"/>
								<form action="${pageContext.request.contextPath}/updatePost" method="post" id="frm" enctype="multipart/form-data">
									<input type="hidden" id="boardId" name="boardId" value="${postVO.boardid}"/>
									<input type="hidden" id="postId" name="postId" value="${postVO.postid}"/>
								
									<label>제목</label>&nbsp;&nbsp; <input type="text" id="title" name="title" value="${postVO.titlecul}"/><br><br>

									<textarea name="smarteditor" id="smarteditor" rows="10"
										cols="100" style="width: 766px; height: 412px;">${postVO.contentcul}</textarea>

									<div id="attach1">
										<label>첨부파일</label>&nbsp;&nbsp;&nbsp; <br>
										<div class="col-sm-10">
											<table>
												<c:forEach items="${attachList}" var="attachVO">
													<tr>
														<td><input type="hidden" name="fileId" class="fileId" value="${attachVO.attachid}"/></td>
														<td><a href="#fileInsert" class="btn btn-default" id="btn btn-default deleteBtn">${attachVO.filename}</a></td>
														<td><button id="deleteBtn" name="deleteBtn" class="btn btn-default" type="button">
															<img src="${pageContext.request.contextPath}/img/trash_bin_icon-icons.com_67981.png"></button></td>
														<td><input id="deleteId" name="deleteId" class="deleteId" type="hidden"/></td>
													</tr>
													<br><br>
												</c:forEach>
												<tr>
													<td><input id="attach" type="file" id="profile" name="profile" class="btn btn-default"/></td>
												</tr>
											</table>
											<input id="add" name="add" type="button" class="btn btn-default" value="첨부파일 추가"/>
											<c:if test="${USER_INFO != null}">
												<input type="button" id="savebutton" value="게시글 수정" class="btn btn-default"/>
											</c:if>
										</div>
									</div>
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