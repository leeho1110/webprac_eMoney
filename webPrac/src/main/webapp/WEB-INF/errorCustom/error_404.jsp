<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
#mainLoadbox {
	width: 200px;
	height: 50px;
	border: 1px solid black;
	border-radius: 3px;
	outline: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<%
String referer = request.getHeader("referer");
 if(referer==null)referer = "/";
 %>
	<div style="text-align: center;">
		<h1>ERROR 404 - Not Found</h1>
		<h3>eMoney Prac</h3>
		<input type="button" id="mainLoadbox" value="메인화면 "
			onclick="movePage();">
	</div>

	<script>
		function movePage(){
			<% 
			if(session.getAttribute("loginStatus") == null){
			%>
				alert("비로그인 상태입니다. 로그인 창으로 이동합니다.");
				location.href="main.do";
			<%
			} else {
			%>
				location.href="main.do";
			<% } %>
		}
		
	</script>
</body>
</html>