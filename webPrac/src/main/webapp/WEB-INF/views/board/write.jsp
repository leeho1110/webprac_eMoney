<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eMoney Web 실습</title>
		
	<!-- 네이버 스마트에디터2 -->
	<script type="text/javascript" src="/SE/static/js/service/HuskyEZCreator.js"></script>
	
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/write.css">
	
</head>
<jsp:include page="/WEB-INF/views/inc/asset.jsp"></jsp:include>

<body>
	<!-- header -->
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		<div id="container" style="text-align: center;">
			<div id="cont_inner">
				<textarea name="ir1" id="writeBox" rows="10" cols="100">에디터에 기본으로 삽입할 글(수정 모드)이 없다면 이 value 값을 지정하지 않으시면 됩니다.</textarea>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
		 oAppRef: oEditors,
		 elPlaceHolder: "writeBox",
		 sSkinURI: "/SE/static/SmartEditor2Skin.html",
		 fCreator: "createSEditor2"
		});
		
	</script>
	
	<script type="text/javascript">
	
	</script>
</body>

</html>