<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<form action="add_item" method="post">
			<table>
				<tr>
					<td>itm_name</td>
					<td><input type="text" name="itm_name" id="itm_name" ></td>
				</tr>
				<tr>
					<td>itm_type</td>
					<td><input type="text" name="itm_type" id="itm_type" ></td>
				</tr>
				<tr>
					<td>itm_owner</td>
					<td><input type="text" name="itm_owner" id="itm_owner" ></td>
				</tr>
				<tr>
					<td>itm_mainitem</td>
					<td><input type="text" name="itm_mainitem" id="itm_mainitem" ></td>
				</tr>
				<tr>
					<td>itm_note</td>
					<td><input type="text" name="itm_note" id="itm_note" ></td>
				</tr>
				<tr>
					<td>itm_image</td>
					<td><input type="text" name="itm_image" id="itm_image" ></td>
				</tr>
				<tr>
					<td>itm_location</td>
					<td><input type="text" name="itm_location" id="itm_location" ></td>
				</tr>
			</table>
			<input type="submit" value="가입">
		</form>
	</body>
</html>