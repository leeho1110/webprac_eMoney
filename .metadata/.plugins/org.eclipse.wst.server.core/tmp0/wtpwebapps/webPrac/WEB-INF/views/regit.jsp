<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eMoney Web 실습</title>
<link rel="stylesheet" href="/resources/css/webPrac.css">
<link rel="stylesheet" href="/resources/css/regit.css">
<script src="/resources/js/jquery-1.12.4.js"></script>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/rsa/jsbn.js"></script>
<script src="/resources/js/rsa/prng4.js"></script>
<script src="/resources/js/rsa/rng.js"></script>
<script src="/resources/js/rsa/rsa.js"></script>


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
				<h2>회원가입</h2>
				<div id="infobox">
					<form id="accntRegit" action="/register.submit.do" method="POST">
						<table id="infoTbl">
							<tbody>
								<tr>
									<th>아이디 <em>*</em></th>
									<td>
										<div style="display: flex; width: 720px;">
											<input type="text" id="idinput" name="id"> 
											<img src="//img.x1.co.kr/x1/images/btn/btn_duplication.gif" id="duplicheck"> 
											<span class="iddupliment" id="idduplino">4자이상 12자이하 영문,숫자(띄어쓰기, 특수문자 불가)<span style="color: red;"> 중복확인을 해주세요</span></span> 
											<span class="iddupliment" id="iddupliok">사용가능합니다.</span>
										</div>
									</td>
								</tr>
								<tr>
									<th>비밀번호 <em>*</em></th>
									<td><input type="password" id="pwinput" name="s_passwd">
										<span class="pwdment" id="pwduplino">영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자(띄어쓰기 불가)</span> 
										<span class="pwdment" id="pwdupliok">사용가능합니다.</span></td>
								</tr>
								<tr>
									<th>비밀번호 확인 <em>*</em></th>
									<td><input type="password" id="pwreinput"></td>
								</tr>
								<tr>
									<th>성명 <em>*</em></th>
									<td><input type="text" id="nameinput" name="name"></td>
								</tr>
							</tbody>
						</table>
					</form>
					<!-- submit btn -->
					<div id="submitdiv">
						<img id="cancelbtn"
							src="//img.x1.co.kr/x1/images/btn/btn_cancel.gif"> <img
							id="submitbtn" src="//img.x1.co.kr/x1/images/btn/btn_confirm.gif">
					</div>

					<form action="/register.submit.do" method="post" id="regithiddenForm">
						<fieldset>
							<input type="hidden" name="id" /> 
							<input type="hidden" name="s_passwd" />
							<input type="hidden" name="name" />
						</fieldset>
					</form>


				</div>
			</div>
		</div>

		<!-- 여기까지 -->
		<div id="footer"></div>
	</div>
</body>
<script>
	var regitFlag = [ false, false, false, false, false ];
	var regitMent = [ "아이디 형식이 올바르지 않습니다", "아이디 중복검사를 실시해주세요",
			"비밀번호 형식이 올바르지 않습니다", "비밀번호가 서로 일치하지않습니다", "성명 형식이 올바르지 않습니다" ]
	// ID 값 정규식
	var idReg = /^[A-Za-z0-9]{4,12}$/;

	// ID 중복확인시 정규식 체크
	$("#idinput").keyup(function() {
		// 입력값이 변경되면 중복검사를 다시 진행해야하기 때문에 무조건 중복확인을 false로 변경
		regitFlag[1] = false;

		// 정규식 일치 여부 확인
		if (idReg.test($("#idinput").val())) {
			regitFlag[0] = true;
		} else {
			regitFlag[0] = false;
		}
	});

	$("#duplicheck").click(function() {
		// 아이디 입력값이 맞는 경우만
		if (regitFlag[0]) {
			// 데이터베이스에 존재하는지 확인
			$.ajax({
				type : "POST",
				url : "register.idcheck.do",
				data : {
					"idinput" : $("#idinput").val()
				},
				dataType : "text",
				success : function(result) {
					// 성공시
					if (result != "duplicated") {
						alert("사용 가능합니다");
						regitFlag[1] = true;
						$("#idduplino").hide();
						$("#iddupliok").show();
					}

					// 실패시 
					else {
						alert("중복된 아이디입니다");
						regitFlag[1] = false;
						$("#iddupliok").hide();
						$("#idduplino").show();
					}
					;
				},
				error : function(a, b, c) {
					console.log(a, b, c);
				}

			});
		} else {
			alert(regitMent[0]);
			regitFlag[0] = false;
			$("#iddupliok").hide();
			$("#idduplino").show();
		}

	});

	// PW 값 정규식
	var pwReg_eng_num = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,12}$/;
	var pwReg_eng_spe = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,12}$/;
	var pwReg_sep_num = /^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{6,12}$/;

	// PW 입력 시 정규식 체크
	$("#pwinput").keyup(
			function() {
				// PW 입력값
				var pwInput = $("#pwinput").val();

				// 영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자
				if (pwReg_eng_num.test(pwInput) || pwReg_eng_spe.test(pwInput)
						|| pwReg_sep_num.test(pwInput)) {
					// 해당 조건 만족 시에 공백 검사
					if (pwInput.search(/\s/) == -1) {
						$("#pwduplino").hide();
						$("#pwdupliok").show();
						regitFlag[2] = true;
					} else {
						$("#pwdupliok").hide();
						$("#pwduplino").show();
						regitFlag[2] = false;
					}
				} else {
					$("#pwdupliok").hide();
					$("#pwduplino").show();
					regitFlag[2] = false;
				}
			});

	// PW 중복 체크 
	$("#pwreinput").keyup(function() {
		if ($("#pwinput").val() == $("#pwreinput").val()) {
			regitFlag[3] = true;
		} else {
			regitFlag[3] = false;
		}
	})

	// 성명 한글 체크
	var nameReg = /^[가-힣]*$/;
	$("#nameinput").keyup(function() {
		if (nameReg.test($("#nameinput").val())) {
			regitFlag[4] = true;
		} else {
			regitFlag[4] = false;
		}
	});

	// 전송 시 항목별 체크

	$("#submitbtn").click(function() {
		var tempFlag = true;
		for (var i = 0; i < regitFlag.length; i++) {
			if (regitFlag[i] == false) {
				alert(regitMent[i]);
				tempFlag = false;
				break;
			}
		}
		if (tempFlag) {
			$("#accntRegit").submit();
		}
	});
</script>

</html>