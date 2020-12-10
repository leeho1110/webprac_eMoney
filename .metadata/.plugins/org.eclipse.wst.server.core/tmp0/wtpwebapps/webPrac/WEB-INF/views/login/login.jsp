<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eMoney Web 실습</title>
<!-- 네이버 로그인 연동 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>

<!-- jQuery & RSA -->
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>
<script src="resources/js/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="resources/css/login.css">
</head>
<body>
	<!-- wrapper -->
	<div id="wrap">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<!-- 여기부터 본문 복사 -->
		<div id="container">
			<div id="cont_inner">
				<div id="infobox">
					<form id="loginForm" action="loginCheck.do" method="POST">
						<h3>로그인</h3>
						<div class="info_area">
							<input type="text" id="idBox" name="id" placeholder="아이디를 입력해주세요">
						</div>
						<div class="info_area">
							<input type="password" id="pwBox" name="pw" placeholder="비밀번호를 입력해주세요">
						</div>
					</form>

					<!-- submit btn -->
					<div>
						<input type="button" id="loginbox" value="로그인" onclick="loginInputCheck();")>

					</div>
					<div  style="text-align:center"><a href="${url}"><img width="215" height="45" style="margin-top: 15px;"src="https://lh3.googleusercontent.com/proxy/_JN8maZyAYB0PGFelR_HLxyRAZoPzioWxMaGVxf44zegdCcOdTbHFht7AdedeNzbaV6Ikd8xpikfsb11j5j6aH8xKdZPz18fVMCM9ZvQrzUhzWM"/></a></div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	 	
		function loginInputCheck() {
			if ($("#idBox").val() == "") {
				alert("아이디 입력을 확인해주세요");
			} else if ($("#pwBox").val() == "") {
				alert("비밀번호 입력을 확인해주세요");
			} else {
				$("#loginForm").submit();
			}
		}
	</script>
</body>

</html>