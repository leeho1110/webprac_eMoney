<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eMoney Web 실습</title>
<script src="resources/js/jquery-1.12.4.js"></script>
</head>
<body>
	<script>
		var loginResult = "${navigate}";
		
		if (loginResult == "login.do") {
			alert("아이디와 비밀번호를 다시 확인해주세요")
			location.href = "${navigate}";
		} else if (loginResult == "register.Api.do") {
			alert("추가 정보기입 창으로 이동합니다")
			location.href = "${navigate}";
		} else if (loginResult == "main.do") {
			location.href = "${navigate}";
		} else {
			alert("이호에게 문의하세요");
			location.href = "login.do";
		}
	</script>
</body>
</html>