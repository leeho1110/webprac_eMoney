<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eMoney Web 실습</title>

	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/main.css">
	
</head>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>

<body>
	<!-- header -->
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container">
			<div id="cont_inner">
				<h3>목록</h3>
				<div id="boardMain">
					<table id="boardList" class="table table-bordered">
						<tbody>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성시간</th>
							</tr>
							<!-- 글 목록 당 하나씩 추가 -->
							<tr>
								<td>number</td>
								<td>title</td>
								<td>member</td>
								<td>sysdate</td>
<!-- 							</tr> -->
						</tbody>
					</table>
					<div id="pagingBtnBox">
						<a>1</a>
					</div>
					<div id="btnBox" >
							<input type="button" id="writeBtn" class="btn" value="글쓰기" onclick="location.href='board.write.do';">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>