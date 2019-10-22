<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta charset="UTF-8">
    
    <title>회원가입</title>
					
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
				
	<script src="${pageContext.request.contextPath}/js/user_js/modify_user.js"></script>
		
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <c:set var="action_type" value="put_user"></c:set>
    <c:choose>
    	<c:when test="${userDto.usr_id != null}">
    		<title>회원정보 수정</title>
    		<c:set var="action_type" value="modify_user"></c:set>
    	</c:when>
    	<c:otherwise>
    		<title>회원가입</title>
    	</c:otherwise>
    </c:choose>
  </head>

  <body class="bg-dark">

    <div class="container">
      <div class="py-5 text-center">
<!--         <img class="d-block mx-auto mb-4" src="./Checkout example for Bootstrap_files/bootstrap-solid.svg" alt="" width="72" height="72"> -->
        <h2>회원가입</h2>
      </div>

      <div class="row">
        <div class="col-md-8 offset-md-2">
          <form action="${action_type }" method="post" class="needs-validation" id="modify_user">
            <div class="mb-3">
              <label for="usr_id">아이디</label>
	         <c:choose>
		    	<c:when test="${userDto.usr_id != null}">
		    		<input type="text" class="form-control" id="usr_id" name="usr_id" readonly="readonly" value="${userDto.usr_id}" maxlength="20">
		    	</c:when>
		    	<c:otherwise>
		    		<input type="text" class="form-control" id="usr_id" name="usr_id" placeholder="아이디를 입력하세요." maxlength="20">
		    	</c:otherwise>
		    </c:choose>
            </div>

            <div class="mb-3">
			    <label for="usr_password">비밀번호</label>
			    <input type="password" class="form-control" name="usr_password" id="usr_password" placeholder="Password" maxlength="20">
            </div>

            <div class="mb-3">
				<label for="usr_password">비밀번호 확인</label>
			    <input type="password" class="form-control" id="usr_password_chk" placeholder="Password 확인" maxlength="20">
			</div>

            <div class="mb-3">
				<label for="usr_name">이름</label>
				<input type="text" class="form-control" name="usr_name" id="usr_name" placeholder="이름" value="${userDto.usr_name}">
            </div>

            <div class="mb-3">
				<label for="usr_email">E-mail</label>
				<input type="email" class="form-control" name="usr_email" id="usr_email">
            </div>

            <div class="mb-3">
				<label for="usr_sms">전화번호</label>
				<input type="text" class="form-control" name="usr_sms" id="usr_sms" placeholder="'-(하이픈)을 제외한 숫자만 입력해주시기 바랍니다.'" value="${userDto.usr_sms}">
            </div>

            <div class="mb-3">
				<label for="usr_kakao">카카오톡</label>
				<input type="text" class="form-control" name="usr_kakao" id="usr_kakao" placeholder="카카오톡" value="${userDto.usr_kakao}">
            </div>

            <div class="mb-3">
				<label for="usr_question">비밀번호 질문</label>
				
				<select class="custom-select d-block w-100" name="usr_question" id="usr_question">
				<option value="sel">선택하세요</option>
				<c:forEach var="result" items="${result}">
					<c:choose>
					  <c:when test="${userDto.usr_question==result.ccd_code}">
						  <option value="${result.ccd_code }" selected="selected">${result.ccd_codename }</option>
					  </c:when>
					  <c:otherwise>
						  <option value="${result.ccd_code }">${result.ccd_codename }</option>
					  </c:otherwise>
					</c:choose> 
				</c:forEach>
				</select>				
            </div>

            <div class="mb-3">
				<label for="usr_answer">비밀번호 질문 답변</label>
				<input type="text" class="form-control" name="usr_answer" id="usr_answer" placeholder="답변" value="${userDto.usr_answer}">
            </div>

<!--             <div class="mb-3"> -->
<!-- 				<label for="usr_image">프로필 사진</label> -->
<!-- 				<input type="file" class="form-control-file" name="usr_image" id="usr_image" placeholder="프로필 사진"> -->
<!-- 			    <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small> -->
<!--             </div> -->

            <hr class="mb-4">
            <button class="btn btn-success btn-lg btn-block mb-3" type="submit">회원가입</button>
          </form>
          
      		<form action="login" method="get">
      			<button class="btn btn-outline-success btn-lg btn-block mb-3" type="submit">취소</button>
			</form>
        </div>
      </div>
    </div>

  

</body>
</html>