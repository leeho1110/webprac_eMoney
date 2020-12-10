<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
			<!-- header top menu -->
			<div id="topTab">
				<div id="topTabInner">
				<c:if test="${empty sessionScope.loginStatus }">
					<a href="login.do"><span>로그인</span></a> <a href="register.do"><span>회원가입</span></a>
				</c:if>
				<c:if test="${not empty sessionScope.loginStatus }">
					<a href="logout.do"><span>로그아웃</span></a> <a href="register.do"><span>마이페이지</span></a>
				</c:if>
				</div>
			</div>
			<!-- header menu -->
			<div id="category">
				<img id="emoneyLogo" src="https://pds.saramin.co.kr/company/logo/201902/26/pnj7pp_2uf9-0_logo.jpg" alt="">
			</div>
		</div>