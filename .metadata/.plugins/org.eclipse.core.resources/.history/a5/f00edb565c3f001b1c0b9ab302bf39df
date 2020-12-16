<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html lang="en">
	
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>eMoney Web 실습</title>
	<link rel="stylesheet" href="resources/css/webPrac.css">
	<link rel="stylesheet" href="resources/css/regit.css">
	
	<!-- RSA 암호화 JavaScript -->
	<script src="resources/js/jquery-1.12.4.js"></script>
	</head>
	
	<body>
		<!-- wrapper -->
		<div id="wrap">
			<!-- header -->
			<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
			<!-- 여기부터 본문 복사 -->
			<div id="container">
				<div id="cont_inner">
					<h2>추가 정보 입력</h2>
					<div id="infobox">
						<form id="accntRegit" action="/register.extraSubmit.do" method="POST">
							<table id="infoTbl">
								<tbody>
										<th>닉네임(필명) <em>*</em></th>
										<td>
											<div class="duplicateboxdiv">
												<input type="text" id="nicknameinput" name="nickname" onkeyup="nicknameFormCheck();">
												<img src="//img.x1.co.kr/x1/images/btn/btn_duplication.gif" id="nicknameduplicheck" onclick="duplicheckAjax(this);"> 
												<span class="duplimentFormMent" id="nicknameduplino">영문 4자~12자,한글 2자~6자(띄어쓰기, 특수문자 불가)<span style="color: red;"> 중복확인을 해주세요</span></span> 
												<span class="duplimentFormMent" id="nicknamedupliok">사용가능합니다.</span></td>
											</div>
										</td>
										
									</td>
                                    
                                    <tr>
                                    <th>휴대폰 번호 <em>*</em></th>
                                    <td>
                                        <select id="phoneinput1" class="phoneinput">
                                            <option>010</option>
                                            <option>011</option>
                                            <option>016</option>
                                            <option>017</option>
                                            <option>018</option>
                                            <option>018</option>
                                            <option>070</option>
                                        </select> -
                                            <input type="text" id="phoneinput2" class="phoneinput" maxlength="4"> -
                                            <input type="text" id="phoneinput3" class="phoneinput" maxlength="4">
                                            <input type="hidden" id="phoneHiddenInput" name="phone">
                                    </td>
                                    </tr>
									</tr>
									
								</tbody>
							</table>
						</form>
						<!-- submit btn -->
						<div id="submitdiv">
							<img id="cancelbtn"
								src="//img.x1.co.kr/x1/images/btn/btn_cancel.gif"> 
								<img
								id="submitbtn" src="//img.x1.co.kr/x1/images/btn/btn_confirm.gif" onclick="regitCtgSubmit();">
						</div>
	
					</div>
				</div>
			</div>
	
		</div>
		
		<!-- JS load -->
		<script src="resources/js/extraRegit.js"></script>
	</body>
	
	</html>