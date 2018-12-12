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
          <form action="put_user" method="post" class="needs-validation">
            <div class="mb-3">
              <label for="usr_id">아이디</label>
              <input type="text" class="form-control" id="usr_id" name="usr_id" placeholder="아이디를 입력하세요." value="${userVo.usr_id}">
            </div>

            <div class="mb-3">
			    <label for="usr_password">비밀번호</label>
			    <input type="password" class="form-control" name="usr_password" id="usr_password" placeholder="Password">
            </div>

            <div class="mb-3">
				<label for="usr_password">비밀번호 확인</label>
			    <input type="password" class="form-control" id="usr_password_chk" placeholder="Password 확인">
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
				<label for="usr_image">프로필 사진</label>
				<input type="text" class="form-control" name="usr_image" id="usr_image" placeholder="프로필 사진">
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