<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 	<head> -->
<!-- 		<meta charset="UTF-8"> -->
<!-- 		<script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<!-- 		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script> -->
<%-- 		<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.min.css" rel="stylesheet"> --%>
<%-- 		<script src="${pageContext.request.contextPath}/js/common_js/list_common.js"></script> --%>
<!-- 		<title>회원가입</title> -->
<!-- 	</head> -->
<!-- 	<body> -->
<!-- 		<form action="modify_user" method="post"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_id">ID</label> -->
<!-- 							<input type="text" class="form-control" name="usr_id" id="usr_id" placeholder="ID"> -->
<!-- 					    </div> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 						    <label for="usr_password">Password</label> -->
<!-- 						    <input type="password" class="form-control" name="usr_password" id="usr_password" placeholder="Password"> -->
<!-- 					    </div> -->
<!--     				</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 						    <label for="usr_password">Password 확인</label> -->
<!-- 						    <input type="password" class="form-control" id="usr_password_chk" placeholder="Password 확인"> -->
<!-- 					    </div> -->
<!--     				</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_name">이름</label> -->
<!-- 							<input type="text" class="form-control" name="usr_name" id="usr_name" placeholder="이름"> -->
<!-- 					    </div> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_sms">전화번호</label> -->
<!-- 							<input type="text" class="form-control" name="usr_sms" id="usr_sms" placeholder="'-(하이픈)을 제외한 숫자만 입력해주시기 바랍니다.'"> -->
<!-- 					    </div> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_kakao">카카오톡</label> -->
<!-- 							<input type="text" class="form-control" name="usr_kakao" id="usr_kakao" placeholder="카카오톡"> -->
<!-- 					    </div> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_image">전화번호</label> -->
<!-- 							<input type="text" class="form-control" name="usr_image" id="usr_image" placeholder="프로필 사진"> -->
<!-- 					    </div> -->
<!-- 					    <div class="form-group"> -->
<!-- 							<label for="usr_image">프로필 사진</label> -->
<!-- 							<input type="file" class="form-control-file" id="usr_image"> -->
<!-- 					    </div> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 			<input type="submit" value="가입"> -->
<!-- 		</form> -->
<!-- 	</body> -->
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
          <form class="needs-validation" novalidate="">
            <div class="mb-3">
              <label for="usr_id">아이디</label>
              <input type="text" class="form-control" id="usr_id" name="usr_id" placeholder="아이디를 입력하세요.">
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
				<input type="text" class="form-control" name="usr_name" id="usr_name" placeholder="이름">
            </div>

            <div class="mb-3">
				<label for="usr_sms">전화번호</label>
				<input type="text" class="form-control" name="usr_sms" id="usr_sms" placeholder="'-(하이픈)을 제외한 숫자만 입력해주시기 바랍니다.'">
            </div>

            <div class="mb-3">
				<label for="usr_kakao">카카오톡</label>
				<input type="text" class="form-control" name="usr_kakao" id="usr_kakao" placeholder="카카오톡">
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