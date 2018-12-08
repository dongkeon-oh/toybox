<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/common_js/list_common.js"></script>
		<title>회원가입</title>
	</head>
	<body>
		<form action="modify_user" method="post">
			<table>
				<tr>
					<td>
					    <div class="form-group">
							<label for="usr_id">ID</label>
							<input type="text" class="form-control" name="usr_id" id="usr_id" placeholder="ID">
					    </div>
				    </td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
						    <label for="usr_password">Password</label>
						    <input type="password" class="form-control" name="usr_password" id="usr_password" placeholder="Password">
					    </div>
    				</td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
						    <label for="usr_password">Password 확인</label>
						    <input type="password" class="form-control" id="usr_password_chk" placeholder="Password 확인">
					    </div>
    				</td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
							<label for="usr_name">이름</label>
							<input type="text" class="form-control" name="usr_name" id="usr_name" placeholder="이름">
					    </div>
				    </td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
							<label for="usr_sms">전화번호</label>
							<input type="text" class="form-control" name="usr_sms" id="usr_sms" placeholder="'-(하이픈)을 제외한 숫자만 입력해주시기 바랍니다.'">
					    </div>
				    </td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
							<label for="usr_kakao">카카오톡</label>
							<input type="text" class="form-control" name="usr_kakao" id="usr_kakao" placeholder="카카오톡">
					    </div>
				    </td>
				</tr>
				<tr>
					<td>
					    <div class="form-group">
							<label for="usr_image">전화번호</label>
							<input type="text" class="form-control" name="usr_image" id="usr_image" placeholder="프로필 사진">
					    </div>
					    <div class="form-group">
							<label for="usr_image">프로필 사진</label>
							<input type="file" class="form-control-file" id="usr_image">
					    </div>
				    </td>
				</tr>
			</table>
			<input type="submit" value="가입">
		</form>
	</body>
</html>