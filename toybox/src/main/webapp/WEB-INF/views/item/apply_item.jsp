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
		
		<!-- custom -->
		<script src="${pageContext.request.contextPath}/js/item_js/apply_item.js"></script>
		<link href="${pageContext.request.contextPath}/css/item_css/apply_item.css" rel="stylesheet" type="text/css">		
		
		<!-- menu -->
		<script src="${pageContext.request.contextPath}/js/nav_menu.js"></script>
	</head>
	<body class="bg-dark" >
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
<!-- 		      		<li class="nav-item"> -->
<!-- 		        		<a class="nav-link" href="#">Link</a> -->
<!-- 		      		</li> -->
<!-- 		      		<li class="nav-item dropdown"> -->
<!-- 		        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 		          			Dropdown -->
<!-- 		        		</a> -->
<!-- 		        		<div class="dropdown-menu" aria-labelledby="navbarDropdown"> -->
<!-- 		          			<a class="dropdown-item" href="#">Action</a> -->
<!-- 		          			<a class="dropdown-item" href="#">Another action</a> -->
<!-- 		          			<div class="dropdown-divider"></div> -->
<!-- 		          			<a class="dropdown-item" href="#">Something else here</a> -->
<!-- 		        		</div> -->
<!-- 		      		</li> -->
<!-- 		      		<li class="nav-item"> -->
<!-- 		        		<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
<!-- 		      		</li> -->
		    	</ul>
		  	</div>
		</nav>
		
	

	<form action="rent_item" method="post">
		<table class="table table-dark table-borderless" >
		  	<thead>
			    <tr class="bg-success">
					<th scope="col">아이템</th>
					<th scope="col" class="">인도</th>
					<th scope="col" class="">인수</th>
			    </tr>
		  	</thead>
		  <tbody id="item_tbody">
		  		<c:forEach var="itemVo" items="${result}" varStatus="status">
			  		<tr class=''>  
						<td>${itemVo.itm_name}</td>
						<td>
							<c:forEach var="userVo" items="${retun_point}">
								<c:if test="${itemVo.cdt_location == userVo.usr_id}">
									<input type="text" readonly="readonly" class="form-control form-control-sm" value="${userVo.usr_name }">
									<input type="hidden" name="cdt_location" value="${userVo.usr_id}">
								</c:if>
							</c:forEach>
						</td>
						<td class="return_point_set">
							<select class="form-control form-control-sm" name="cdt_return">
								<c:forEach var="userVo" items="${retun_point}">
									<option value="${userVo.usr_id }">${userVo.usr_name }</option>
								</c:forEach>							
							</select>
							<input type="hidden" name="cdt_item" value="${itemVo.itm_id}">							
						</td>
					</tr>                            
		  		</c:forEach>	
		  		<tr>
		  			<td colspan='3'>
						<div class="form-group">
							<label for="note">요청 사항</label>
							<textarea class="form-control" id="note" name="note" rows="3"></textarea>
						</div>		  			
					</td>
				</tr>
		  		<tr>
		  			<td colspan='3'>
						<div class="form-group from_to_date">
							<label class="from_to_date">대여기간 : ${rent_date} ~ ${return_date}</label>
						</div>		  			
					</td>
				</tr>
		  		<tr>
		  			<td colspan='3'>
		  				<button type="submit" class="btn btn-success btn-block">대여신청</button>
		  			</td>
				</tr>
		  </tbody>
		  </table> 
		  <input type="hidden" name="rent_date" value="${rent_date}">
		  <input type="hidden" name="return_date" value="${return_date}">
		  <input type="hidden" name="result">
		  </form>
	</body>
</html>