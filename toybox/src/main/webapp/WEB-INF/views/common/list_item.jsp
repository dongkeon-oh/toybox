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
		<link  href="${pageContext.request.contextPath}/css/common_css/common.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/common_util.js"></script>
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
		  		<button type="button" class="btn btn-outline-success" id="put_item" data-toggle="modal" data-target="#item_modal" style="margin-right: 8px;">아이템 추가</button>
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
					  	<h5 class="modal-title" id="item_modal_title">아이템 정보</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body"  style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이템 이름</label>
							<input type="text" class="form-control" id="itm_name" placeholder="아이템 이름">
							<input type="hidden" id="itm_id">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이템 타입</label>
							<div class="input-group">
								<input type="text" class="form-control" id="itm_type_code" readonly="readonly" placeholder="아이템 타입">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary item_sub_option" id="sub_itm_type" type="button" data-toggle='modal' data-target='#sub_modal'>변경</button>
								</div>
							</div>
							<input type="hidden" id="itm_type">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">소유자</label>
							<div class="input-group">
								<input type="text" class="form-control" id="itm_owner_name" readonly="readonly" placeholder="소유자">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary item_sub_option" id="sub_itm_owner" type="button" data-toggle='modal' data-target='#sub_modal'>변경</button>
								</div>
							</div>
							<input type="hidden" id="itm_owner">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">이미지</label>
							<input type="text" class="form-control" id="itm_image" readonly="readonly" value="준비중입니다.">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">메인 아이템</label>
							<div class="input-group">
								<input type="text" class="form-control" id="itm_mainitem_name" readonly="readonly" placeholder="메인 아이템">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary item_sub_option" id="sub_itm_mainitem" type="button" data-toggle='modal' data-target='#sub_modal'>변경</button>
								</div>
							</div>
							<input type="hidden" id="itm_mainitem">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아아템 상태</label>
							<div class="input-group">
								<input type="text" class="form-control" id="itm_useyn_code" readonly="readonly" placeholder="아이템 상태">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary item_sub_option" id="sub_itm_useyn" type="button" data-toggle='modal' data-target='#sub_modal'>변경</button>
								</div>
							</div>
							<input type="hidden" id="itm_useyn">
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
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content sub_modal_height">
					<div class="modal-header">
					  	<h5 class="modal-title title_text">검색</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body"  style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label title_text" for="inputDefault">검색</label>
							<div class="input-group">
								<input type="text" class="form-control" id="sub_keyword" placeholder="검색어를 입력하세요." onkeyup="search_sub('onkey')">
								<div class="input-group-append">
									<button class="btn btn-outline-secondary" id="sub_search_btn" type="button">검색</button>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-form-label label_text" for="inputDefault">아이템 타입</label>
							<select class="custom-select" id="sub_option">
					      		
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