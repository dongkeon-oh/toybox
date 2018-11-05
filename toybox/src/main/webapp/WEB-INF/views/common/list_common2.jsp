<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/common_js/list_common.js" type="text/javascript"></script>
<!-- 		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<!-- 		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- 		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css" rel="stylesheet"> -->
	<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.min.css" rel="stylesheet">
		<title>회원가입</title>
	</head>
	<body>
		<select id="target">
			<option value="id">공통코드</option>
			<option value="name">공통코드명</option>
			<option value="category1">카테고리1</option>
			<option value="category2">카테고리2</option>
			<option value="category3">카테고리3</option>
		</select>

		<div class="input-append">
			<input class="span2" id="keyword" type="text" placeholder="검색어를 입력하세요.">
			<button class="btn" type="button" onclick="list_common('1')">Go!</button>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<td>No.</td>
					<td>공통코드</td>
					<td>공통코드명</td>
					<td>카테고리1</td>
					<td>카테고리2</td>
					<td>카테고리3</td>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>	
		<select id="idx">
			<option value="10" selected="selected">10</option>
			<option value="15">15</option>
		</select>
		<div class="pagination">
			<ul id="pagination_area">
<!-- 				<li class="disabled"><span>&laquo;</span></li> -->
<!-- 				<li class="active"><span>1</span></li> -->
			</ul>
		</div>
	</body>
</html>