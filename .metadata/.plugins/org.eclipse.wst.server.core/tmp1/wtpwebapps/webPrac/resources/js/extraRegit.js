 // 각 항목별 통과 여부와 안내멘트 딕셔너리
 const regitCategory = {};

 regitCategory['nicknameForm'] = [false, "닉네임 형식이 올바르지 않습니다"];
 regitCategory['nicknameDupli'] = [false, "닉네임 중복검사를 실시해주세요"];
 regitCategory['phoneForm'] = [false, "휴대폰 번호 형식이 올바르지 않습니다"];



 // ajax 활용 중복검사 함수 
 function duplicheckAjax(category){
    var ctg = category.id.replace("duplicheck","");
    
    // 정규식 통과 여부
    if (regitCategory[ctg + "Form"][0] == true) {
        // 데이터베이스에 존재하는지 확인
        $.ajax({
            type : "POST",
            url : "register."+ctg+"check.do",
            data : { "dupliinput" : $("#"+ctg+"input").val() },
            dataType : "text",
            success : function(result) {
                // 성공시
                if (result != "duplicated") {
                    alert("사용 가능합니다");
                    regitCategory[ctg+"Dupli"][0] = true;
                    $("#"+ctg+"duplino").hide();
                    $("#"+ctg+"dupliok").show();
                }

                // 실패시 
                else {
                    alert("중복된 아이디입니다");
                    regitCategory[ctg+"Dupli"][0] = false;
                    $("#"+ctg+"dupliok").hide();
                    $("#"+ctg+"duplino").show();
                }
                ;
            },
            error : function(a, b, c) {
                console.log(a, b, c);
            }

        });
    } else {
        alert(regitCategory[ctg+"Form"][1]);
        regitCategory[ctg+"Form"][0] = false;
        $("#"+ctg+"dupliok").hide();
        $("#"+ctg+"duplino").show();
    }
 };


 
 // 닉네임 체크
 // 닉네임 형식 체크
 function nicknameFormCheck(){

    var nicknameReg_eng = /^[a-zA-Z]{4,12}$/;
    var nicknameReg_kor = /^[가-힣]{2,6}$/;

    // 입력값이 변경되면 중복검사를 다시 진행해야하기 때문에 무조건 중복확인을 false로 변경
    regitCategory['nicknameDupli'][0] = false;

    // 닉네임 값
    var nicknameInput = $("#nicknameinput").val();

    if(nicknameReg_eng.test(nicknameInput) || nicknameReg_kor.test(nicknameInput)){
        regitCategory['nicknameForm'][0] = true;
    } else {
        regitCategory['nicknameForm'][0] = false;
    }
};


// phone focus
$("#phoneinput2").keyup(function(){
    if($(this).val().length == 4){
        $("#phoneinput3").focus();
    }
});

// phone reg check
var phoneReg = /^[0-9]{11}$/;
$("input.phoneinput").keyup(function(){
	var phoneNum = $("#phoneinput1").val() + $("#phoneinput2").val() + $("#phoneinput3").val();
    if(phoneReg.test(phoneNum)){
        regitCategory["phoneForm"][0] = true;
        $("#phoneHiddenInput").val(phoneNum.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3"));
    } else {
        regitCategory["phoneForm"][0] = false;
    }
});

//  -----------------------------------------------------

 // Server로부터 받은 공개키 입력 (보류)
// var rsa = new RSAKey();
// rsa.setPublic("${modulus}", "${exponent}");
// rsa.encrypt(encodeURI("test"));
 
  // 전송 시 항목별 체크
function regitCtgSubmit(){
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
         
        // var encrytedId = rsa.encrypt($("#idinput").val());
        // var encrytedPw = rsa.encrypt($("#pwinput").val());
         
         // hidden 태그에 값을 주입
//         $("#idhiddeninput").val(encrytedId);
//         $("#pwhiddeninput").val(encrytedPw);
        $("#idhiddeninput").val($("#idinput").val());
        $("#pwhiddeninput").val($("#pwinput").val());
         
         // 전송
         $("#accntRegit").submit();
         
     };
 };