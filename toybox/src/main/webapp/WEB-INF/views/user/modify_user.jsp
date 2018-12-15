<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<link  href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/user_js/modify_user.js"></script>
		
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>회원가입</title>
  </head>

  <body class="bg-light">

    <div class="container">
      <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="./Checkout example for Bootstrap_files/bootstrap-solid.svg" alt="" width="72" height="72">
        <h2>회원가입</h2>
      </div>

      <div class="row">
        <div class="col-md-12 order-md-1">
          <form action="put_user" method="post" class="needs-validation" id="modify_user">
            <div class="mb-3">
              <label for="usr_id">아이디</label>
              <input type="text" class="form-control" id="usr_id" name="usr_id" placeholder="아이디를 입력하세요." value="${userVo.usr_id}" maxlength="20">
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
				<input type="text" class="form-control" name="usr_name" id="usr_name" placeholder="이름" value="${userVo.usr_name}">
            </div>

            <div class="mb-3">
				<label for="usr_sms">전화번호</label>
				<input type="text" class="form-control" name="usr_sms" id="usr_sms" placeholder="'-(하이픈)을 제외한 숫자만 입력해주시기 바랍니다.'" value="${userVo.usr_sms}">
            </div>

            <div class="mb-3">
				<label for="usr_kakao">카카오톡</label>
				<input type="text" class="form-control" name="usr_kakao" id="usr_kakao" placeholder="카카오톡" value="${userVo.usr_kakao}">
            </div>

            <div class="mb-3">
				<label for="usr_question">비밀번호 질문</label>
				
				<select class="custom-select d-block w-100" name="usr_question" id="usr_question">
				<option value="sel">선택하세요</option>
				<c:forEach var="com_code" items="${com_list}">
					<c:choose>
					  <c:when test="${userVo.usr_question==com_code.ccd_seq}">
						  <option value="${com_code.ccd_seq }" selected="selected">${com_code.ccd_codename }</option>
					  </c:when>
					  <c:otherwise>
						  <option value="${com_code.ccd_seq }">${com_code.ccd_codename }</option>
					  </c:otherwise>
					</c:choose> 
				</c:forEach>
				</select>				
            </div>

            <div class="mb-3">
				<label for="usr_answer">비밀번호 질문 답변</label>
				<input type="text" class="form-control" name="usr_answer" id="usr_answer" placeholder="답변" value="${userVo.usr_answer}">
            </div>

            <div class="mb-3">
				<label for="usr_image">프로필 사진</label>
				<input type="file" class="form-control-file" name="usr_image" id="usr_image" placeholder="프로필 사진">
<!-- 			    <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small> -->
            </div>

            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">회원가입</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">© 2017-2018 Company Name</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="http://getbootstrap.com/docs/4.1/examples/checkout/#">Privacy</a></li>
          <li class="list-inline-item"><a href="http://getbootstrap.com/docs/4.1/examples/checkout/#">Terms</a></li>
          <li class="list-inline-item"><a href="http://getbootstrap.com/docs/4.1/examples/checkout/#">Support</a></li>
        </ul>
      </footer>
    </div>


  

</body>
</html>