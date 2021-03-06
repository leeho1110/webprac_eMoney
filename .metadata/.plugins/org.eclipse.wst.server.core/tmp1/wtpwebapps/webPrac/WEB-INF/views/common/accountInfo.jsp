<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>이름 약자별 데이터 노출</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>

	<div style="width: 1100px; margin: 50px auto;">
		<c:choose>
			<c:when test="${!empty accntInfo}">
				<table class="table table-bordered"
					style="width: 700px; text-align: left; margin: 0px auto;">
					<tr>
						<td colspan="2">Input:
							<h4>${accntNickname}</h4>
						</td>
					</tr>
					<tr>
						<td>회원 ID</td>
						<td>${accntInfo.accnt_id}</td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td>${accntInfo.nickname}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${accntInfo.name}</td>
					</tr>
					<tr>
						<td>회원 유형</td>
						<td>${accntInfo.user_type}</td>
					</tr>
					<tr>
						<td>휴대폰 번호</td>
						<td>${accntInfo.phone}</td>
					</tr>
					<tr>
						<td>아이디</td>
						<td>${accntInfo.id}</td>
					</tr>
					<tr>
						<td>패스워드</td>
						<td>${accntInfo.s_passwd}</td>
					</tr>
				</table>
			</c:when>
	
			<c:otherwise>
				<h3>이니셜을 다시 입력해주세요</h3>
			</c:otherwise>
			
		</c:choose>
	</div>
</body>
</html>