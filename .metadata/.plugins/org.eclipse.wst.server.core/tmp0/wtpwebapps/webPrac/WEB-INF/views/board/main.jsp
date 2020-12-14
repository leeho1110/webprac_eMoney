<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eMoney Web 실습</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/main.css">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	
	<style type="text/css">
	#cntPerPage {
		width: 120px;
		height: 27px;
		margin-bottom: 30px;
		text-align: center;
		float: right;
	}
	</style>
</head>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>

<body>
	<!-- header -->
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container">
			<div id="cont_inner">
				<h2>목록</h2>
				<div id="boardMain">
					
					<select id="cntPerPage" class="selectpicker" onchange="cntPerPageChange()">
						<option value="5"
							<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5개 보기</option>
						<option value="10"
							<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10개 보기</option>
						<option value="15"
							<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15개 보기</option>
					</select>
					<table id="boardList" class="table table-bordered">
						<tbody>
							<tr>
								<th>제목</th>
								<th>내용</th>
								<th>작성자</th>
								<th>작성시간</th>
							</tr>
							<!-- 글 목록 당 하나씩 추가 -->
							<c:forEach items="${boardList}" var="post">
								<tr>
									<input type="hidden" class="postNumBox" value="${post.post_num}" name="post_num">
									<td>${post.title }</td>
									<td>${post.content }</td>
									<td>${post.writer_name}</td>
									<td>${post.time}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div id="pagingBtnBox">
						<c:if test="${paging.startPage != 1 }">
							<a href="/main.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
						</c:if>
						<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage }">
									<b>${p }</b>
								</c:when>
								<c:when test="${p != paging.nowPage }">
									<a href="/main.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage != paging.lastPage}">
							<a href="/main.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
						</c:if>
					</div>
					<div id="btnBox" class="">
							<input type="button" id="writeBtn" class="btn" value="글쓰기" onclick="location.href='board.write.do';">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function cntPerPageChange() {
		location.href="main.do?nowPage=${paging.nowPage}&cntPerPage="+$("#cntPerPage").val();
	}

	$("#boardList tr").click(function(){
	    var post_num = $(this).find(".postNumBox").val();
	    location.href="/board.view.do?post_num="+ post_num;
	})
	</script>
	
</body>

</html>