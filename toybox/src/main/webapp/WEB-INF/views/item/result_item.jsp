<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
		<title>아이템 목록</title>
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

		<link href="${pageContext.request.contextPath}/css/item_css/result_item.css" rel="stylesheet" type="text/css">	
		
		<!-- menu -->
		<script src="${pageContext.request.contextPath}/js/nav_menu.js"></script>
	</head>
	<body class="bg-dark">
		<nav class="navbar navbar-expand-xs navbar-dark bg-dark">		
			<div class="form-inline my-2 my-lg-0">
		  		<a class="navbar-brand" href="#">${mapping_name }</a>
		    </div>
		
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  			
		  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		    	<ul class="navbar-nav mr-auto">
		    		<c:forEach var="mList" items="${menu_list }" varStatus="status">
			      		<li class="nav-item active">
			      			<c:choose>
			      				<c:when test="${mList.ccd_code eq mapping_menu }">
			        				<a class="nav-link nav-menu" href="#" id="${mList.ccd_code }" style="color : #28a745">${mList.ccd_codename }</a>
			      				</c:when>
			      				<c:otherwise>
			        				<a class="nav-link nav-menu" href="#" id="${mList.ccd_code }">${mList.ccd_codename }</a>
			      				</c:otherwise>
			      			</c:choose>
			      		</li>
					</c:forEach>
		    	</ul>
		  	</div>
		</nav>
	
		<div class="container">
			<div class="result_area text-center">
			  	<img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/image/toybox_success.png" alt="" width="72" height="72">
			  	<h2>대여신청 성공!</h2>
			</div>
		</div>
		<div class="jumbotron">
			<p class="lead">총 ${rent_item_count }건의 아이템에 대한 대여를 신청했습니다.<br>대여한 아이템의 대여 현황은 [내 요청사항]에서 확인하시기 바랍니다.</p>
			<form action="request_item" method="GET">
				<button class="btn btn-success btn-lg btn-block" type="submit">내 요청사항으로 바로가기</button >
			</form>
			<hr class="my-4">
			<p>대여기간 : ${rent_date} ~ ${return_date}<br>대여기간을 준수해 주시기 바랍니다.<br>비상 연락처 : (E-mail)test@gmail.com</p>
		</div>
	</body>
</html>