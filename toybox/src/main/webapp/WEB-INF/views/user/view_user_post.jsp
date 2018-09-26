<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/user_js/view_user_post.js" type="text/javascript"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<form action="modify_user" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td>
						<input type="text" name="usr_id" id="usr_id" value="${user.usr_id}">
						<input type="button" value="삭제" onclick="delete_user('${user.usr_id }')" >
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="usr_password" id="usr_password" value=""></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="usr_name" id="usr_name" value="${user.usr_name}"></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="usr_sms" id="usr_sms" value="${user.usr_sms}"></td>
				</tr>
				<tr>
					<td>image</td>
					<td><input type="text" name="usr_image" id="usr_image" value="${user.usr_image}"></td>
				</tr>
				<tr>
					<td>kakao</td>
					<td><input type="text" name="usr_kakao" id="usr_kakao" value="${user.usr_kakao}"></td>
				</tr>
				<tr>
					<c:set var="active" value="활성화" />
					<c:if test="${user.usr_active eq 'Y'}">
						<c:set var="active" value="비활성화" />   
					</c:if>
					<td>active</td>
					<td>
						<input type="text" name="usr_active" id="usr_active" value="${user.usr_active}">
						<input type="button" value="${active }" onclick="active_user('${user.usr_id }', '${user.usr_active}')" >
					</td>
				</tr>
			</table>
			<input type="submit" value="수정">
		</form>
	</body>
</html>


