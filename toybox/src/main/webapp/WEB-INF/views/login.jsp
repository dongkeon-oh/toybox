<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>	
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
	    
	    <title>로그인</title>
				
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
					
	    <link rel="icon" href="${pageContext.request.contextPath}/image/toybox_favicon.ico">
	    <style>
	    	h2, label {
		  		color: white;
			};   	
	    </style>
	</head>

	<body class="bg-dark">	
		<div class="container">
			<div class="py-5 text-center">
			  	<img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/image/toybox_simbol.png" alt="" width="72" height="72">
			  	<h2>로그인</h2>
			</div>
		
		  	<div class="row">
			    <div class="col-md-8 offset-md-2">
			      	<form action="login_prog" method="post" class="needs-validation" id="modify_user">
				        <div class="mb-3">
				          	<label for="usr_id">아이디</label>
				  			<input type="text" class="form-control" id="usr_id" name="usr_id" placeholder="아이디" maxlength="20" value="hellgogi">
				        </div>
			
			        	<div class="mb-3">
						   <label for="usr_password">패스워드</label>
						   <input type="password" class="form-control" name="usr_password" id="usr_password" placeholder="패스워드" maxlength="20" value="1">
			        	</div>
			
			        	<hr class="mb-4">
			        	<c:if test="${login_fail != null}">
				        	<div class="alert alert-danger" role="alert">
							  	아이디 또는 패스워드를 확인해 주십시오.
							</div>
			        		<hr class="mb-4">
			        	</c:if>
			        	<button class="btn btn-success btn-lg btn-block mb-3" type="submit">로그인</button>
		      		</form>
		      		<form action="join_user" method="get">
		      			<button class="btn btn-outline-success btn-lg btn-block" type="submit">회원가입</button>
					</form>
		    	</div>
		  	</div>
		</div>	
	</body>
</html>