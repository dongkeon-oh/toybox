/**
 * 
 */
$(function(){
	$('form').on('submit', function() {
	     return valid_user();
	 });
});




function valid_user(){
	var id_ex 		= /[a-z0-9_]{8,20}$/;
	var pwd_ex 		= /[a-z0-9_!]{8,20}$/;
	var name_ex 	= /^[가-힣]{2,4}$/
	var sms_ex 		= /^01([0|1|6|7|8|9]?)\d{3,4}\d{4}$/;
//	var kakao_ex 	= "??";

	var usr_id 				= $("#usr_id").val();
	var usr_password 		= $("#usr_password").val();
	var usr_password_chk 	= $("#usr_password_chk").val();
	var usr_name 			= $("#usr_name").val();
	var usr_sms 			= $("#usr_sms").val();
	// 전화번호 하이픈 제거
	usr_sms = usr_sms.replace(/-/gi,"");
	$("#usr_sms").val(usr_sms);
	var usr_question 		= $("#usr_question").val();
	var usr_answer 			= $("#usr_answer").val();
	
	if(!id_ex.test(usr_id)){
		alert('아이디는 8자 이상 20자 이하의 영문 소문자와 숫자, 특수문자 "_" 만 사용이 가능합니다.');
		$("#usr_id").focus();
		return false;
	}

	if(usr_password != usr_password_chk){
		alert('비밀번호가 일치하기 않습니다.');
		$("#usr_password_chk").focus();
		return false;
	}

	if(!pwd_ex.test(usr_password)){
		alert('비밀번호는 8자 이상 20자 이하의 영문 소문자와 숫자, 특수문자 "_!" 만 사용이 가능합니다.');
		$("#usr_password").focus();
		return false;
	}

	if(!pwd_ex.test(usr_password_chk)){
		alert('비밀번호는 8자 이상 16자 이하의 영문 소문자와 숫자, 특수문자 "_~!@#$%^&*" 만 사용이 가능합니다.');
		$("#usr_password_chk").focus();
		return false;
	}

	if(!name_ex.test(usr_name)){
		alert('이름을 2자 이상 4자 이하의 한글로 입력하시기 바랍니다.');
		$("#usr_name").focus();
		return false;
	}

	if(!sms_ex.test(usr_sms)){
		alert('전화번호를 바르게 입력하시 바랍니다.');
		$("#usr_sms").focus();
		return false;
	}

	if(usr_question == "sel"){
		alert('비밀번호 질문을 입력하시기 바랍니다.');
		$("#usr_question").focus();
		return false;
	}

	if(usr_answer == "" || usr_answer == null){
		alert('비밀번호 질문 답변을 입력하시기 바랍니다.');
		$("#usr_answer").focus();
		return false;
	}
}