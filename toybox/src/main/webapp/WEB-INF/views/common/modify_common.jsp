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
		<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.min.css" rel="stylesheet">

		<title>공통코그 수정</title>
	</head>
	<body>
		<table>
			<tr>
				<th rowspan="2">공통코드 그룹</th>
			</tr>
			<tr>
				<td>
					<select id="sel_cat" onChange="change_category('1')">
						<option id="deselected" value="deselected">선택</option>
					</select>
				</td>
				<td>
					<input type="button" id="add_cat1" class="btn btn-primary add_btn" value="추가" onclick="add_cat('1')">
					<input type="button" id="cat_mod1" class="btn btn-inverse mod_btn" value="수정" disabled="disabled" onclick="mod_category('1')">
					<input type="button" id="cat_del1" class="btn btn-danger del_btn" value="삭제" disabled="disabled" onclick="del_category('1')">
				</td>
			</tr>
			
			<tr>
				<th rowspan="2">공통코드</th>
			</tr>
			<tr>
				<td>
					<select id="sel_code" onChange="change_category('2')">
						<option id="deselected" value="deselected">선택</option>
					</select>
				</td>
				<td>
					<input type="button" id="cat_add2" class="btn btn-primary add_btn" value="추가" onclick="add_common('2')">
					<input type="button" id="cat_mod2" class="btn btn-inverse mod_btn" value="수정" disabled="disabled" onclick="mod_category('2')">
					<input type="button" id="cat_del2" class="btn btn-danger del_btn" value="삭제" disabled="disabled" onclick="del_category('2')">
				</td>
			</tr>
			
			<tr>
				<td rowspan="2">공통코드 명</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="cat_add3" class="btn btn-primary add_btn" value="추가" onclick="add_category('3')">
				</td>
				<td>
					<input type="button" id="cat_add2" class="btn btn-primary add_btn" value="추가" onclick="add_common('3')">
					<input type="button" id="cat_mod2" class="btn btn-inverse mod_btn" value="수정" disabled="disabled" onclick="mod_category('3')">
					<input type="button" id="cat_del2" class="btn btn-danger del_btn" value="삭제" disabled="disabled" onclick="del_category('3')">
				</td>
			</tr>
			
			<tr>
				<td rowspan="2">카테고리3</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="cat_add3" class="btn btn-primary add_btn" value="추가" onclick="add_category('category3')">
				</td>
				<td>
				</td>
			</tr>
			
			<tr>
				<td rowspan="2">카테고리3</td>
			</tr>
			<tr>
				<td id="category3_topTd">
					<input type="text" id="cat_add3" class="btn btn-primary add_btn" value="추가" onclick="add_category('category3')">
				</td>
				<td>
				</td>
			</tr>
		</table>
	</body>
</html>