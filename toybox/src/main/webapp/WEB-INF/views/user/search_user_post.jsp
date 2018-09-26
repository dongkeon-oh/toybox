<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/user_js/searchUser.js" type="text/javascript"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>COMMON LIST</title>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>전화번호</td>
					<td>카카오 아이디</td>
					<td>상세보기</td>
				</tr>
				<c:forEach var="item" items="${userList}" >
				<tr>
					<td>${item.usr_id }</td>
					<td>${item.usr_name }</td>
					<td>${item.usr_sms }</td>
					<td>${item.usr_kakao }</td>
					<td><input type="button" value="상세보기" id="${item.usr_id }" onclick="view_user('${item.usr_id }')"></td>
				</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>
