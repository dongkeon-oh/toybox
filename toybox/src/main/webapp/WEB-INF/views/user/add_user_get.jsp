<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>이름</td>
					<td>COM_CATEGORY1</td>
					<td>COM_CATEGORY2</td>
					<td>COM_CATEGORY3</td>
					<td>COM_NAME</td>
					<td>COM_USEYN</td>
				</tr>
				
				<tr>
					<td>${item.com_id }</td>
					<td>${item.com_category1 }</td>
					<td>${item.com_category2 }</td>
					<td>${item.com_category3 }</td>
					<td>${item.com_name }</td>
					<td>${item.com_useyn }</td>
				</tr>
			</table>
		</form>
	</body>
</html>