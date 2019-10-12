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
		<script src="${pageContext.request.contextPath}/js/common_js/list_user.js"></script>
		<script src="${pageContext.request.contextPath}/js/common_js/pagination.js"></script>
		
		<title>사용자 관리</title>
	</head>
	<body class="bg-light">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="btn-group navbar-brand" role="group">
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			      	사용자 관리
			    </button>
			    <div class="dropdown-menu bg-dark">
			      <a class="dropdown-item" href="/toybox/admin_list_common">공통코드 관리</a>
			      <a class="dropdown-item" href="/toybox/admin_list_item">아이템 관리</a>
			    </div>
			  </div>
			</div>			


			<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="navbar-collapse collapse" id="navbarColor02" style="">
		  		<ul class="navbar-nav mr-auto"></ul>
			    <form class="form-inline my-2 my-lg-0">
			      	<div class="form-group" style="margin-right: 8px;">
				    	<select class="custom-select" id="keytype">
				      		<option value="id">아이디</option>
				      		<option value="name">이름</option>
				    	</select>
				  	</div>
				  	<input class="form-control mr-sm-2" id="keyword" type="text" placeholder="검색어를 입력하세요" onkeyup="search_enter()">
			      	<button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="search_keyword()">검색</button>
			    </form>
		    </div>
		</nav>
		
		<div class="modal" id="user_modal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					  	<h5 class="modal-title">사용자 정보</h5>
					  	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					  	</button>
					</div>
					<div class="modal-body"  style="overflow:auto; height:450px;">
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">아이디</label>
							<input type="text" class="form-control user_info" id="usr_id" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">이름</label>
							<input type="text" class="form-control user_info" id="usr_name" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">등급</label>
							<select class="form-control" id="usr_type">
								
							</select>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">프로필 사진</label>
							<input type="text" class="form-control user_info" id="usr_image" readonly="readonly" value="준비중입니다.">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">E-mail</label>
							<input type="text" class="form-control user_info" id="usr_email" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">SMS</label>
							<input type="text" class="form-control user_info" id="usr_sms" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">KakaoTalk</label>
							<input type="text" class="form-control user_info" id="usr_kakao" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">유저 상태</label>
							<select class="form-control" id="usr_active">
								
							</select>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">비밀번호 질문</label>
							<input type="text" class="form-control user_info" id="usr_question_code" readonly="readonly">
							<input type="hidden" id="usr_question">
						</div>
						<div class="form-group">
							<label class="col-form-label" for="inputDefault">비밀번호 질문 답변</label>
							<input type="text" class="form-control user_info" id="usr_answer" readonly="readonly">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary btn_modify">수정</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		
		<table class="table table-hover table-borderless">
  <thead>
    <tr class="bg-success">
      <th scope="col">#</th>
      <th scope="col">아이디</th>
      <th scope="col">이름</th>
      <th scope="col">사용자 상태</th>
      <th scope="col" class="th_btn_area_user"></th>
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