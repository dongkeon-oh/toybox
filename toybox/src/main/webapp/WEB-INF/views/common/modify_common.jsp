<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/common_js/add_common.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/common_js/add_category.js" type="text/javascript"></script>
		<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.css" rel="stylesheet">
		<title>회원가입</title>
	</head>
	<body>
		<form action="add_common" method="post">
			<table>
				<tr>
					<td rowspan="2">카테고리1</td>
				</tr>
				<tr>
					<td id="category1_topTd">
						<select id="category1_sel" name="com_category1" onChange="change_category('category1')">
							<option id="deselected" value="deselected">선택</option>
						</select>
					</td>
					<td id="category1_buttomTd">
						<input type="button" id="cat1_add" class="btn btn-primary add_btn" value="카테고리 추가" onclick="add_category('category1')">
					</td>
				</tr>
				
				<tr>
					<td rowspan="2">카테고리2</td>
				</tr>
				<tr>
					<td id="category2_topTd">
						<select id="category2_sel" name="com_category2" onChange="change_category('category2')">
							<option id="deselected" value="deselected">선택</option>
						</select>
					</td>
					<td id="category2_buttomTd">
						<input type="button" id="cat2_add" class="btn btn-primary add_btn" value="카테고리 추가" onclick="add_category('category2')">
					</td>
				</tr>
				
				<tr>
					<td rowspan="2">카테고리3</td>
				</tr>
				<tr>
					<td id="category3_topTd">
						<select id="category3_sel" name="com_category3">
							<option id="deselected" value="deselected">선택</option>
						</select>
					</td>
					<td id="category3_buttomTd">
						<input type="button" id="cat3_add" class="btn btn-primary add_btn" value="카테고리 추가" onclick="add_category('category3')">
					</td>
				</tr>
				
				<tr>
					<td>공통코드명</td>
					<td><input type="text" id="com_name" value="${commonVo.com_name}"></td>
				</tr>
				<tr>
					<td rowspan="2"><input type="button" class="btn btn-primary add_btn" value="추가" onclick="save_common()"></td>
				</tr>
			</table>
			<input type="hidden" id="cat_id" name="cat_id">
			<input type="hidden" id="category_id">
		</form>		
	</body>
</html>