<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<link  href="${pageContext.request.contextPath}/css/bootstrap/dist/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/common_js/list_common.js"></script>
		<title>회원가입</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">검색</a>
			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <form class="form-inline my-2 my-lg-0">
				    <div class="form-group" style="margin-right: 8px;">
				    	<select class="custom-select">
				      		<option selected="selected">Open this select menu</option>
				      		<option value="1">One</option>
				      		<option value="2">Two</option>
				    	</select>
				  	</div>
			      	<input class="form-control mr-sm-2" type="text" placeholder="Search">
			      	<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			    </form>
		    </div>
		</nav>	
		
	
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#grpModModal" >공통코드 그룹생성</button>
		<button type="button" class="btn btn-danger btn-lg" onclick="list_common_group(1, 10, '')">공통코드</button>
	
		<div class="modal" id="grpModModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">공통코그 그룹생성</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 그룹</label>
							<input type="text" class="form-control" placeholder="공통코드 그룹명" id="grpGroup" maxlength="16">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 그룹명</label>
							<input type="text" class="form-control" placeholder="공통코드 그룹명" id="grpGroupName" maxlength="100">
						</div>
					    <div class="form-group">
					      	<label for="grpNote">설명</label>
					      	<textarea class="form-control" id="grpNote" rows="5" style="resize: none;"></textarea>
					    </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" onClick="put_common_group()" >생성</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
		
		<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">No</th>
      <th scope="col">공통코드 그룹</th>
      <th scope="col">공통코드 그룹명</th>
      <th scope="col" colspan="2">공통코드 그룹 설명</th>
    </tr>
  </thead>
  <tbody>
  
  </tbody>
</table> 
	</body>
</html>