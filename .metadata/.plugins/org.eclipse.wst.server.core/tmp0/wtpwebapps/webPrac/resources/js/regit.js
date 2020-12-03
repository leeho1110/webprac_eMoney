 // 각 항목별 pass 체크 딕셔너리
 var regitCategory = {};

 regitCategory['term1'] = [false, "서비스 약관 1번에 동의해주세요"];
 regitCategory['term2'] = [false, "서비스 약관 2번에 동의해주세요"];
 regitCategory['idForm'] = [false, "아이디 형식이 올바르지 않습니다"];
 regitCategory['idDupli'] = [false,  "아이디 중복검사를 실시해주세요"];
 regitCategory['pwForm'] = [false,  "비밀번호 형식이 올바르지 않습니다"];
 regitCategory['pwCorrect'] = [false, "비밀번호가 서로 일치하지않습니다"];
 regitCategory['nameForm'] = [false, "성명 형식이 올바르지 않습니다"];
 regitCategory['nicknameForm'] = [false, "닉네임 형식이 올바르지 않습니다"];
 regitCategory['nicknameDupli'] = [false, "닉네임 중복검사를 실시해주세요"];
 regitCategory['phoneForm'] = [false, "휴대폰 번호 형식이 올바르지 않습니다"];

 // 약관 동의
 // 전체 약관 동의 클릭
 $("#agreeAll").change(function(){
     if($(this).is(":checked")){
         $("input[type=checkbox]").prop("checked",true);
         regitCategory["term1"][0] = true;
         regitCategory["term2"][0] = true;
     } else {
         $("input[type=checkbox]").prop("checked",false);
         regitCategory["term1"][0] = false;
         regitCategory["term2"][0] = false;
     }
 })
 
 // // 각 버튼 클릭 시 
 $("#agreeEach1").change(function(){
     if($(this).prop("checked")){
         regitCategory["term1"][0] = true;
     } else {
         regitCategory["term1"][0] = false;ㄱㄷㅎㅇㄻㄴㅇ
     }
 });

 $("#agreeEach2").change(function(){
     if($(this)
     .prop("checked")){
         regitCategory["term2"][0] = true;
     } else {
         regitCategory["term2"][0] = false;
     }
 });

 // 체크박스를 클릭 시 체크되어있는지를 검사
 $("input[type=checkbox]").change(function(){
     if(regitCategory["term1"][0] == false || regitCategory["term2"][0] == false){
         $("#agreeAll").prop("checked",false);
     }

     else if(regitCategory["term1"][0] == true || regitCategory["term2"][0] == true){
         $("#agreeAll").prop("checked",true);
     }
 });


 // ID 값 정규식
 var idReg = /^[A-Za-z0-9]{4,12}$/;

 // ID 중복확인시 정규식 체크
 $("#idinput").keyup(function() {
     // 입력값이 변경되면 중복검사를 다시 진행해야하기 때문에 무조건 중복확인을 false로 변경
     regitCategory["idDupli"][0] = false;

     // 정규식 일치 여부 확인
     if (idReg.test($("#idinput").val())) {
         regitCategory["idForm"][0] = true;
     } else {
         regitCategory["idForm"][0] = false;
     }
 });

 $("#idduplicheck").click(function() {
     // 아이디 입력값이 맞는 경우만
     if (regitCategory["idForm"][0] == true) {
         // 데이터베이스에 존재하는지 확인
         $.ajax({
             type : "POST",
             url : "register.idcheck.do",
             data : { "idinput" : $("#idinput").val() },
             dataType : "text",
             success : function(result) {
                 // 성공시
                 if (result != "duplicated") {
                     alert("사용 가능합니다");
                     regitCategory["idDupli"][0] = true;
                     $("#idduplino").hide();
                     $("#iddupliok").show();
                 }

                 // 실패시 
                 else {
                     alert("중복된 아이디입니다");
                     regitCategory["idDupli"][0] = false;
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
         alert(regitCategory['idForm'][1]);
         regitCategory["idForm"][0] = false;
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
             var pwInput = $(this).val();

             // 영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자
             if (pwReg_eng_num.test(pwInput) || pwReg_eng_spe.test(pwInput)
                     || pwReg_sep_num.test(pwInput)) {
                 // 해당 조건 만족 시에 공백 검사
                 if (pwInput.search(/\s/) == -1) {
                     $("#pwduplino").hide();
                     $("#pwdupliok").show();
                     regitCategory['pwForm'] = true;
                 } else {
                     $("#pwdupliok").hide();
                     $("#pwduplino").show();
                     regitCategory['pwForm'] = false;
                 }
             } else {
                 $("#pwdupliok").hide();
                 $("#pwduplino").show();
                 regitCategory['pwForm'] = false;
             }
         });

 // PW 중복 체크 
 $("#pwreinput").keyup(function() {
     if ($("#pwinput").val() == $(this).val()) {
         regitCategory["pwCorrect"][0] = true;
     } else {
         regitCategory["pwCorrect"][0] = false;
     }
 })

 // 성명 한글 체크
 var nameReg = /^[가-힣]*$/;
 $("#nameinput").keyup(function() {
     if (nameReg.test($(this).val())) {
         regitCategory["nameForm"][0] = true;
     } else {
         regitCategory["nameForm"][0] = false;
     }
 });

 
 // 닉네임 체크
 //  닉네임 형식 체크
 var nicknameReg_eng = /^[a-zA-Z]{4,12}$/;
 var nicknameReg_kor = /^[가-힣]{2,6}$/;

$("#nicknameinput").keyup(function(){
    regitCategory['nicknameDupli'][0] = false;
    var nicknameInput = $("#nicknameinput").val()
    if(nicknameReg_eng.test(nicknameInput) || nicknameReg_kor.test(nicknameInput)){
        regitCategory['nicknameForm'][0] = true;
    } else {
        regitCategory['nicknameForm'][0] = false;
    }
})

$("#nicknameduplicheck").click(function(){
    if(regitCategory['nicknameForm'][0] == true){
        $.ajax({
            type : "POST",
             url : "register.nicknamecheck.do",
             data : {
                 "nicknameinput" : $("#nicknameinput").val()
             },
             
             dataType : "text",
             success : function(result) {
                 // 성공시
                 if (result != "duplicated") {
                     alert("사용 가능합니다");
                     regitCategory["nicknameDupli"][0] = true;
                     $("#nicknameduplino").hide();
                     $("#nicknamedupliok").show();
                 }

                 // 실패시 
                 else {
                     alert("중복된 닉네임입니다");
                     regitCategory["nicknameDupli"][0] = false;
                     $("#nicknamedupliok").hide();
                     $("#nicknameduplino").show();
                 }
                 ;
             },
             error : function(a, b, c) {
                 console.log(a, b, c);
             }
        });
    } else {
        alert(regitCategory['nicknameForm'][1]);
         regitCategory["nicknameForm"][0] = false;
         $("#nicknamedupliok").hide();
         $("#nicknameduplino").show();
    }
})

// phone
$("#phoneinput2").keyup(function(){
    if($(this).val().length == 4){
        $("#phoneinput3").focus();
    }
});

var phoneReg = /^[0-9]{11}$/;
$("input.phoneinput").keyup(function(){
    var phoneNum = $("#phoneinput1").val() + $("#phoneinput2").val() + $("#phoneinput3").val();
    if(phoneReg.test(phoneNum)){
        regitCategory["phoneForm"][0] = true;
        $("#phoneHiddenInput").val(phoneNum);
    } else {
        regitCategory["phoneForm"][0] = false;
    }
});



//  -----------------------------------------------------

 // Server로부터 받은 공개키 입력
 var rsa = new RSAKey();
 rsa.setPublic("${modulus}", "${exponent}");
 rsa.encrypt("test");
 // 전송 시 항목별 체크
 $("#submitbtn").click(function() {
	 
     var tempFlag = true;
     for(var key in regitCategory){
         if(regitCategory[key][0] == false){
             alert(regitCategory[key][1]);
             tempFlag=false;
             break;
         }
     }
     // 모든 조건에 만족한 경우 RSA 암호화를 진행					
     if (tempFlag) {
         
//         var encrytedId = rsa.encrypt($("#idinput").val());
//         var encrytedPw = rsa.encrypt($("#pwinput").val());
         
         // hidden 태그에 값을 주입
//         $("#idhiddeninput").val(encrytedId);
//         $("#pwhiddeninput").val(encrytedPw);
         
         // 전송
         $("#accntRegit").submit();
         
     };
 });