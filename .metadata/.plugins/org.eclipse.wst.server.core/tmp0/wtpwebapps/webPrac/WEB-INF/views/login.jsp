<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eMoney Web 실습</title>
<script src="/resources/js/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="/resources/css/webPrac.css">
<link rel="stylesheet" href="/resources/css/login.css">
</head>

<body>
	<!-- wrapper -->
	<div id="wrap">
		<!-- header -->
		<div id="header">
			<!-- header top menu -->
			<div id="topTab">
				<div id="topTabInner">
					<span>로그인</span> <span>회원가입</span>
				</div>
			</div>
			<!-- header menu -->
			<div id="category">
				<img id="emoneyLogo"
					src="https://pds.saramin.co.kr/company/logo/201902/26/pnj7pp_2uf9-0_logo.jpg"
					alt="">
			</div>
		</div>

		<!-- 여기부터 본문 복사 -->
		<div id="container">
			<div id="cont_inner">
				<div id="infobox">
					<form action="loginCheck.do" action="POST">
						<h2>로그인</h2>
						<div class="info_area">
							<input type="text" name="accnt_id" placeholder="아이디를 입력해주세요">
						</div>
						<div class="info_area">
							<input type="password" name="accnt_passwd" placeholder="비밀번호를 입력해주세요">
						</div>
					</form>
					
					<!-- submit btn -->
					<div>
						<input type="submit" id="loginbox" value="로그인">
					</div>
				</div>
			</div>
		</div>

		<!-- 여기까지 -->
		<div id="footer"></div>
	</div>
</body>

</html>