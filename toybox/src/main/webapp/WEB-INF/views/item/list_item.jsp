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
				
		<!-- datepicker -->
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">	
		
		<!-- custom -->
		<script src="${pageContext.request.contextPath}/js/item_js/list_item.js"></script>
		<link href="${pageContext.request.contextPath}/css/item_css/list_item.css" rel="stylesheet" type="text/css">
		
		<!-- marquee -->
		<link href="${pageContext.request.contextPath}/css/marquee.css" rel="stylesheet" type="text/css">	
	</head>
	<body class="bg-dark">
		<nav class="navbar navbar-expand-xs navbar-dark bg-dark">		
			<div class="form-inline my-2 my-lg-0">
		  		<a class="navbar-brand" href="#">${mapping_name }</a>
		      	<button class="btn btn-outline-success btn-sm my-sm-0 nav_btn" data-toggle="collapse" data-target="#searchControl" aria-controls="searchControl" aria-expanded="false" aria-label="Toggle navigation">검색</button>
		      	<button class="btn btn-success btn-sm my-sm-0 nav_btn" id="rent_selected" onclick="total_rent(rent_item)" disabled>선택 대여</button>
		    </div>
		
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	
		  	<div class="collapse navbar-collapse" id="searchControl">
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item active">
		      		  	<div class="form-group row">
						    <label for="rent_date" class="col-sm-2">아이템</label>
					    	<div class="col-sm-10">
		        				<input type="text" class="form-control form-control-sm" id='keyword'>
					    	</div>
						</div>
					</li>	
		      		<li class="nav-item">
		      		  	<div class="form-group row">
	    					<label class="col-sm-2">대여기간</label>
			      		  	<div class="col-sm-10">
								<div class="input-group mb-3">
									<input type="text" class="form-control datepicker rent_date" onfocus="blur()">
									<div class="input-group-prepend">
										<span class="input-group-text bg-dark" id="from_to_area">~</span>
									</div>
									<input type="text" class="form-control datepicker return_date" onfocus="blur()">
								</div>
							</div>
						</div>  	
		 			</li>	
		 			
		      		<li class="nav-item active">
		 				<button class="btn btn-outline-success col-sm-1 mb-3" onclick="list_item('search_event')">검색</button>   	
		 			</li>   	
		    	</ul>
		  	</div>
		  			
		  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item active">
		        		<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		      		</li>
		      		<li class="nav-item">
		        		<a class="nav-link" href="#">Link</a>
		      		</li>
		      		<li class="nav-item dropdown">
		        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          			Dropdown
		        		</a>
		        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          			<a class="dropdown-item" href="#">Action</a>
		          			<a class="dropdown-item" href="#">Another action</a>
		          			<div class="dropdown-divider"></div>
		          			<a class="dropdown-item" href="#">Something else here</a>
		        		</div>
		      		</li>
		      		<li class="nav-item">
		        		<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
		      		</li>
		    	</ul>
		  	</div>
		</nav>
	
		<table class="table table-dark table-borderless" >
		  <thead>
		    <tr class="bg-success">
		      <th scope="col" class="fixed_select">선택</th>
		      <th scope="col">아이템</th>
		      <th scope="col" class="fix_state">상태</th>
		      <th scope="col" class="fix_quick">바로 대여</th>
		    </tr>
		  </thead>
		  <tbody id="item_tbody">
		  
		  </tbody>
		</table> 
	</body>
</html>