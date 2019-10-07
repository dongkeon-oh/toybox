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
		<script src="${pageContext.request.contextPath}/js/common_js/list_item.js"></script>
		<script src="${pageContext.request.contextPath}/js/common_js/pagination.js"></script>
		
		<title>아이템 관리</title>
	</head>
	<body class="bg-light">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">아이템 관리</a>
			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <div class="form-inline my-2 my-lg-0">
				  	<input class="form-control mr-sm-2" id="keyword" type="text" placeholder="검색어를 입력하세요" onkeyup="search_enter('onkey')">
			      	<button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="search_enter('click')">검색</button>
			    </div>
		    </div>
		</nav>
		
		<div class="modal" id="item_modal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">아이템 정보</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body"  style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이템</label>
							<input type="text" class="form-control" id="itm_name">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이템 타입</label>
							<input type="text" class="form-control item_sub_option" id="itm_type">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">소유자</label>
							<input type="text" class="form-control item_sub_option" id="itm_owner">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">이미지</label>
							<input type="text" class="form-control" id="itm_image" readonly="readonly" value="준비중입니다.">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">메인 아이템</label>
							<input type="text" class="form-control item_sub_option" id="itm_mainitem">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아아템 상태</label>
							<input type="text" class="form-control item_sub_option" id="itm_useyn">
						</div>
					    <div class="form-group">
					      	<label for="itm_note">설명</label>
					      	<textarea class="form-control" id="itm_note" rows="5" style="resize: none;"></textarea>
					    </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id='btn_modify'>추가</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal" id="sub_modal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">검색</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body"  style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">검색</label>
							<input type="text" class="form-control" id="sub_keyword">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이템 타입</label>
							<select class="custom-select" id="sub_option">
					      		<option value="_NODATA_">선택하세요</option>
					    	</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id='btn_apply'>선택</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		
		<table class="table table-hover table-borderless">
  <thead>
    <tr class="bg-success">
      <th scope="col">#</th>
      <th scope="col">아이템</th>
      <th scope="col">상태</th>
      <th scope="col">소유자</th>
      <th scope="col" class="th_btn_area"></th>
    </tr>
  </thead>
  <tbody>
  
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