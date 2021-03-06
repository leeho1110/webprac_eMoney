<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eMoney Web 실습</title>
<link rel="stylesheet" href="resources/css/main.css">
<style>
#mainLoadbox {
	width: 120px;
	height: 40px;
	border: 0px;
	border-radius: 5px;
	outline: none;
	cursor: pointer;
	color: white;
	background-color: #bbbbbb;
	margin-top: 20px;
}

#loginStatus {
	display: none;
}
</style>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container">
			<div style="text-align: center;">
				<div id="cont_inner">
				<img src="https://previews.123rf.com/images/auttkhamkhauncham/auttkhamkhauncham1411/auttkhamkhauncham141100251/33887902-%EB%8A%90%EB%82%8C%ED%91%9C-%EC%9C%84%ED%97%98-%EC%8B%A0%ED%98%B8.jpg" width="120px" height="120px">
					<h3>동작되지 않는 요청입니다</h3>
					
					<input type="button" class="btn btn-link" id="mainLoadbox" value="메인화면 " onclick="movePage();">
				</div>
			</div>
		</div>
		
	</div>
	<input type="hidden" id="loginStatus" value="${empty sessionScope.loginStatus }"> 

	<script>
	$("#emoneyLogo").click(function(){
	    movePage();
	});
	
	function movePage(){
	    if($("#loginStatus").val() == "false") {
			location.href="main.do";
	    } else {
			alert("로그인을 먼저 진행해주세요");
			location.href="login.do";
	    }
	}
		
	</script>
</body>
</html>