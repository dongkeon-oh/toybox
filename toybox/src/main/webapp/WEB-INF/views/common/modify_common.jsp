<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<form action="add_user" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="usr_id" id="usr_id" ></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="usr_password" id="usr_password" ></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="usr_name" id="usr_name" ></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="usr_sms" id="usr_sms" ></td>
				</tr>
				<tr>
					<td>image</td>
					<td><input type="text" name="usr_image" id="usr_image" ></td>
				</tr>
				<tr>
					<td>kakao</td>
					<td><input type="text" name="usr_kakao" id="usr_kakao" ></td>
				</tr>
			</table>
			<input type="submit" value="가입">
		</form>
	</body>
</html>