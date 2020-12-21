<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eMoney Web 실습</title>

<link rel="stylesheet" href="resources/css/view.css">

</head>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>

<body>
	<!-- header -->
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container">
			<div id="cont_inner">
			<h2>본문</h2>
				<div id="allowUserBtn">
					<!-- 자신이 작성한 글의 경우 수정과 삭제가 가능하고 이외에는 운영자만 기능 부여 (계정 권한별 기능 구분)-->
					<c:if test="${sessionScope.loginStatus.accnt_id eq post.writer || sessionScope.loginStatus.user_type eq '운영자'}">
							<input type="button" id="modBtn" class="btn" value="수정"
								onclick="location.href='board.modify.do?post_num=<c:out value="${post.post_num}"></c:out>';">
							<input type="button" id="delBtn" class="btn" value="삭제"
								onclick="location.href='board.delete.do?post_num=<c:out value="${post.post_num}"></c:out>';">
					</c:if>
				</div>
				<table id="postTbl" class="table table-bordered">
					<tbody>
						<tr>
							<th>제목</th>
							<td><c:out value="${post.title}"></c:out></td>
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
							<td colspan="2"><div id="contentDiv"><c:out value="${post.content}" escapeXml="false"></c:out></div></td>
						</tr>
					</tbody>
				</table>
				<div id="btnDiv">
					<input type="button" id="backBtn" class="btn" value="뒤로가기"
						onclick="location.href='main.do';">
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
		    var justText = $("#contentDiv").text();
		    $("#contentDiv").text("");
		    $("#contentDiv").append(justText);
		})
	</script>
</body>

</html>