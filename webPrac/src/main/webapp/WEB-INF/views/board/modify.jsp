<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eMoney Web 실습</title>

<script type="text/javascript" src="/SE/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>

<link rel="stylesheet" href="resources/css/modify.css">

</head>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>

<body>
	<!-- header -->
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container">
			<div id="cont_inner">
			<h2>수정</h2>
				<table id="postTbl" class="table table-bordered">
					<tbody>
						<tr>
							<th>제목</th>
							<td><div id="titleDiv"><c:out value="${post.title}"></c:out></div></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><div id=" timeDiv"><c:out value="${post.writer_name}"></c:out></div></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td><div id=" timeDiv"><c:out value="${post.time}"></c:out></div></td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="contentDiv">
								<form id="naverSEwriteBox" action="board.modify.submit.do" method="POST"> 
									<textarea name="content" id="modifyBox" rows="10" cols="100">${post.content}</textarea>
									<input type="hidden" value='<c:out value="${post.post_num }"></c:out>' name="post_num">
								</form>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="btnDiv">
					<input type="button" id="submitBtn" class="btn" value="수정완료" onclick="submitModfiyBox();">
					<input type="button" id="goBack" class="btn" value="뒤로가기" onclick="history.back();">
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "modifyBox",
			sSkinURI: "/SE/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
		
		function submitModfiyBox() {
			// load the value of SE		    
		    oEditors.getById["modifyBox"].exec("UPDATE_CONTENTS_FIELD", []);
		    
		    var contentLength = $("#modifyBox").val().length;
		    
		    if(contentLength > 0){
				$("#naverSEwriteBox").submit();
			} else {
				alert("내용을 다시 확인해주세요");
			}
		}
	</script>
</body>

</html>