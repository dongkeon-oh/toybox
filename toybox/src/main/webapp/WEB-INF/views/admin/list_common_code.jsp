<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link  href="${pageContext.request.contextPath}/css/admin_css/admin.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/admin_js/list_common_group.js"></script>
		<script src="${pageContext.request.contextPath}/js/admin_js/list_common_code.js"></script>
		<script src="${pageContext.request.contextPath}/js/admin_js/pagination.js"></script>
		
		<title>공통코드 관리</title>
	</head>
	<body class="bg-light">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="btn-group navbar-brand" role="group">
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			      	공통코드 관리
			    </button>
			    <div class="dropdown-menu bg-dark">
			      <a class="dropdown-item" href="/toybox/admin_list_user">사용자 관리</a>
			      <a class="dropdown-item" href="/toybox/admin_list_item">아이템 관리</a>
			    </div>
			  </div>
			</div>
			
			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <div class="form-inline my-2 my-lg-0">
				    <button type="button" class="btn btn-outline-success modify_common_group" id="putGrpBtn" data-toggle="modal" data-target="#grpModModal" style="margin-right: 8px;">공통코드 그룹생성</button>
			      	<div class="form-group" style="margin-right: 8px;">
				    	<select class="custom-select" id="keytype">
				      		<option value="group">공통코드</option>
				      		<option value="name">공통코드명</option>
				    	</select>
				  	</div>
				  	<input class="form-control mr-sm-2" id="keyword" type="text" placeholder="검색어를 입력하세요" onkeyup="search_enter()">
			      	<button class="btn btn-secondary my-2 my-sm-0" type="button" id="grpSrchBtn" onclick="search_keyword()">검색</button>
			    </div>
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
		
		<div class="modal" id="codeModModal" data-keyboard="false">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title" id="code_title">공통코드</h5>
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
					
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal" id="detailModModal" data-keyboard="false">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title" id="code_detail_title">[공통코드 타이틀]</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body" style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드" id="ccd_code" maxlength='100' readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드명</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드명" id="ccd_codename" maxlength='16'>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 정렬순서</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드 정렬순서" id="ccd_order" maxlength='5'>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 상세1</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드 상세1" id="ccd_detail1">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 상세2</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드 상세2" id="ccd_detail2">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">공통코드 상세3</label>
							<input type="text" class="form-control code_mod" placeholder="공통코드 상세3" id="ccd_detail3">
						</div>
					    <div class="form-group">
					      	<label for="ccd_note">설명</label>
					      	<textarea class="form-control code_mod" id="ccd_note" rows="5" style="resize: none;"></textarea>
					    </div>					    
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="code_modify_btn">[추가/수정]</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
		
		<table class="table table-hover table-borderless">
  <thead>
    <tr class="bg-success">
      <th scope="col">#</th>
      <th scope="col">공통코드 그룹</th>
      <th scope="col">공통코드 그룹명</th>
      <th scope="col">공통코드 그룹 설명</th>
      <th scope="col">사용유무</th>
      <th scope="col" class="th_btn_area_code"></th>
    </tr>
  </thead>
  <tbody id="grp_tbody">
  
  </tbody>
  
</table> 

<div class="row">
  <div class="col-md-4 offset-md-4">
  <ul class="pagination" id="pagination_area">

  </ul>
	</div>
  <div class="col-md-3 offset-md-1">
    <select class="custom-select" id="cnt" onchange="change_page_count(this.value)">
      <option value="10">10건씩</option>
      <option value="15">15건씩</option>
      <option value="2">2건씩 (테스트)</option>
    </select>
  </div>
</div>  





	</body>
</html>