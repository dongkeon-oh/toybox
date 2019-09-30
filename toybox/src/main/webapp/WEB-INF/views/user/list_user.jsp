<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<link  href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/commonUtil.js"></script>
		<script src="${pageContext.request.contextPath}/js/user_js/list_user.js"></script>
		<title>공통코드 관리</title>
	</head>
	<body class="bg-light">
		<nav class="navbar navbar-expand-lg navbar-dark bg-light">
			<a class="navbar-brand" href="#">검색</a>
			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <form class="form-inline my-2 my-lg-0">
				    <button type="button" class="btn btn-outline-primary" id="putGrpBtn" data-toggle="modal" data-target="#grpModModal" style="margin-right: 8px;" onClick="clear_group()">공통코드 그룹생성</button>
			      	<div class="form-group" style="margin-right: 8px;">
				    	<select class="custom-select" id="keytype">
				      		<option value="sel">검색조건 선택</option>
				      		<option value="group">공통코드</option>
				      		<option value="name">공통코드명</option>
				    	</select>
				  	</div>
				  	<input class="form-control mr-sm-2" id="keyword" type="text" placeholder="검색어를 입력하세요" onkeyup="search_enter()">
			      	<button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="search_keyword('onkey')">검색</button>
			    </form>
		    </div>
		</nav>
		
		<table class="table table-hover">
		  <thead id="user_thead">
		
		  </thead>
		  <tbody id="user_tbody">
		  
		  </tbody>
		  
		</table> 


  <div class="form-group">
    <select class="custom-select" id="cnt" onchange="change_page_count(this.value)">
      <option value="10">10건씩</option>
      <option value="15">15건씩</option>
    </select>
  </div>
  
  <div>
	  <ul class="pagination" id="pagination_area">
	
	  </ul>
	</div>



<div>
  <ul class="pagination">
    <li class="page-item disabled">
      <a class="page-link" href="#">&laquo;</a>
    </li>
    <li class="page-item active">
      <a class="page-link" href="#">1</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">2</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">3</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">4</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">5</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">&raquo;</a>
    </li>
  </ul>
</div>

	</body>
</html>