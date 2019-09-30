<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
		<title>내 요청내역</title>
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

		<!-- custom -->
		<script src="${pageContext.request.contextPath}/js/item_js/response_item.js"></script>
		<link href="${pageContext.request.contextPath}/css/item_css/response_item.css" rel="stylesheet" type="text/css">	
		
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
	
		<table class="table table-dark table-borderless" id="btn_area">
		<tr>
		      <td>
		      	<div class="btn-group btn-block" id="item" role="group" aria-label="Basic example">
					  <button type="button" class="btn btn-outline-secondary">대여 내역</button>
					  <button type="button" class="btn btn-secondary">요청 내역</button>
					</div>
		      </td>
		</tr>
		</table>
		
		<table class="table table-dark table-borderless" >
		  <thead>
		    <tr class="bg-success">
		      <th scope="col">아이템</th>
		      <th scope="col">요청자</th>
		      <th scope="col">기간</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody id="cdt_tbody">
	  		<c:forEach var="itemVo" items="${result}">
	  			<tr>
	  				<td>${itemVo.itm_name }</td>
	  				<td>${itemVo.cdt_user }</td>
	  				<td>${itemVo.cdt_fromdate }<br>~ ${itemVo.cdt_todate }</td>
	  				<td>
						<div class="btn-group">
						  <button type="button" class="btn btn-success dropdown-toggle btn-test" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    선택
						  </button>
						  <div class="dropdown-menu">
						    <a class="dropdown-item" id="rent_permited" href="#">승인</a>
						    <a class="dropdown-item" id="rent_denied" href="#">거절</a>
						  </div>
						</div>
					</td>
	  			</tr>
	  		</c:forEach>
		  </tbody>
		</table> 
	</body>
</html>