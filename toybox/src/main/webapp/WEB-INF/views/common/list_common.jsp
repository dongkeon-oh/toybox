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
		<title>공통코드 관리</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-light">
			<a class="navbar-brand" href="#">검색</a>
			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <form class="form-inline my-2 my-lg-0">
				    <button type="button" class="btn btn-primary btn-lg" id="putGrpBtn" data-toggle="modal" data-target="#grpModModal" style="margin-right: 8px;" onClick="clear_group()">공통코드 그룹생성</button>
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
	
		<div class="modal" id="grpModModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">공통코드 그룹</h5>
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
							<input type="text" class="form-control" placeholder="공통코드 그룹명" id="grpGroupName" maxlength="25">
						</div>
					    <div class="form-group">
					      	<label for="grpNote">설명</label>
					      	<textarea class="form-control" id="grpNote" rows="5" style="resize: none;"></textarea>
					    </div>
					</div>
					<div class="modal-footer">
						<button type="button" id="putModifyGrpBtn" class="btn btn-primary" onClick="mod_common_group()">생성</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal" id="codeModModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">공통코드</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body" id="modal-body" style="overflow:auto; height:500px;">
							<table class="table table-hover">
							  <thead>
							    <tr>
							      <th scope="col">공통코드</th>
							      <th scope="col">공통코드명</th>
							      <th scope="col">공통코드 정렬순서</th>
							      <th scope="col"></th>
							    </tr>
							  </thead>
							  <tbody id="code_tbody">
							  
							  </tbody>
							  
							</table> 
					</div>
					<div class="modal-footer" id="code_tfooter">
<!-- 						<button type='button' class='btn btn-primary' onClick='add_code("")' >공통코드 추가</button> -->
<!-- 						<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button> -->
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal" id="detailModModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">공통코드 상세</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 그룹</label>
							<input type="text" class="form-control" placeholder="공통코드 상세1" id="ccd_detail1">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 그룹명</label>
							<input type="text" class="form-control" placeholder="공통코드 상세2" id="ccd_detail2">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 그룹명</label>
							<input type="text" class="form-control" placeholder="공통코드 상세3" id="ccd_detail3">
						</div>
					    <div class="form-group">
					      	<label for="ccd_note">설명</label>
					      	<textarea class="form-control" id="ccd_note" rows="5" style="resize: none;"></textarea>
					    </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" onClick="">수정</button>
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
      <th scope="col">공통코드 그룹 설명</th>
      <th scope="col">사용유무</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody id="grp_tbody">
  
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




	</body>
</html>