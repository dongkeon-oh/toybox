<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>COMMON LIST</title>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>COM_ID</td>
					<td>COM_CATEGORY1</td>
					<td>COM_CATEGORY2</td>
					<td>COM_CATEGORY3</td>
					<td>COM_NAME</td>
					<td>COM_USEYN</td>
				</tr>
				<c:forEach var="item" items="${eList}" >
				<tr>
					<td>${item.com_id }</td>
					<td>${item.com_category1 }</td>
					<td>${item.com_category2 }</td>
					<td>${item.com_category3 }</td>
					<td>${item.com_name }</td>
					<td>${item.com_useyn }</td>
				</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>
