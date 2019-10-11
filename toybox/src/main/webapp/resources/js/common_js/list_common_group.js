var pagination_index = 1;
var pagination_cnt = 10;
var keyword_data = "";
var keytype_data = "group";

var common_group_mode;		// 공통코드 그룹 추가인지 생성인지 구분하는 변수

////////////////////////////////////////
// 공통코드 그룹 관련 함수//////////////////////
////////////////////////////////////////

$(function() {
	list_common_group("1", pagination_cnt, keyword_data, keytype_data);
	
	// 페이지네이션 onclick
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_common_group(pagination_index, pagination_cnt, keyword_data, keytype_data);
	});
	
	$("#grpSrchBtn").on("click",function(){
		search_keyword();
	})
	
	// 공통코드 그룹 추가 버튼을 누르거나 공통코드 코드 수정 버튼을 누를 경우 경우에 따라 모달창 세팅
	$(document).on("click",".modify_common_group",function(){
		var g_id = $(this).attr("id");
		if(g_id == "putGrpBtn"){
			new_group();
		}else{
			modify_group(g_id);
		}
	});
	
	// 공통코드 그룹 추가 버튼을 누르거나 공통코드 코드 수정 버튼을 누를 경우 각각 세팅
	$("#putModifyGrpBtn").on("click",function(){
		modify_common_group();
	});
	
	// 공통코드 그룹 삭제
	$(document).on("click",".delete_common_group",function(){
		var g_id = $(this).attr("id");
		delete_group(g_id);
	});	
});

function list_common_group(page_no, page_cnt, keyword, keytype) {
	var end_idx = page_cnt * page_no;
	var start_idx = end_idx - page_cnt + 1;

	$.ajax({
		method : "POST",
		url : "ajax_list_common_group",
		data : {
			"keyword" : keyword,
			"keytype" : keytype,
			"start_idx" : start_idx,
			"end_idx" : end_idx
		},
		async : false,
		success : function(response) {
			var result = response;
			var opt = "";
			var page_total = 0;

			$("#grp_tbody").html("");
			$.each(
				result,
				function(index, item) {
					page_total = item.cnt;

					var note = item.cgr_note;
					var table_row_type = "";// =
											// "table-secondary";
					var use_yn = "사용";
					if (item.cgr_useyn == 'N') {
						use_yn = "삭제됨";
						table_row_type = "bg-danger";
					}

					opt = opt + "<tr class='" + table_row_type + "'>";
					opt = opt + "	<td>" + (start_idx + index) + "</td>";
					opt = opt + "	<td>" + item.cgr_group + "</td>";
					opt = opt + "	<td>" + item.cgr_group_name + "</td>";
					opt = opt + "	<td>" + note + "</td>";
					opt = opt + "	<td>" + use_yn + "</td>";
					opt = opt + "	<td>";
					if (item.cgr_useyn != 'N') {
						opt = opt + "<button type='button' class='btn btn-primary btn-sm modify_common_group' data-toggle='modal' data-target='#grpModModal' id='" + item.cgr_group + "'>수정</button>";
						opt = opt + "<button type='button' class='btn btn-danger btn-sm delete_common_group' id='" + item.cgr_group + "'>삭제</button>";
						opt = opt + "<button type='button' class='btn btn-secondary btn-sm list_common_code' data-toggle='modal' data-target='#codeModModal' id='" + item.cgr_group + "'>공통코드</button>";
					}
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			$("#grp_tbody").html(opt);

			set_pagination(page_cnt, page_total, page_no, keyword, keytype);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function new_group() {
	$("#grpGroup").val("");
	$("#grpGroupName").val("");
	$("#grpNote").val("");
	$("#grpGroup").attr("readonly", false); // 공통코드 입력가능

	// mod_common_group 기능 변경 PUT
	common_group_mode = "PUT";
	$("#putModifyGrpBtn").text("추가");
	$("#code_detail_title").text("공통코드 상세");
}

function modify_group(id) {
	// 수정인 경우 대상 공통그룹 불러오기
	get_common_group(id)
	
	$("#grpGroup").attr("readonly", true); // 공통코드 변경불가

	// mod_common_group 기능 변경 MODIFY
	common_group_mode = "MODIFY";
	$("#putModifyGrpBtn").text("수정");
	$("#code_detail_title").text("공통코드 수정");
	
}

// 수정인 경우 대상 공통그룹 불러오기
function get_common_group(cgr_group) {
	$.ajax({
		method : "POST",
		url : "ajax_get_common_group",
		data : {
			"cgr_group" : cgr_group
		},
		async : false,
		success : function(response) {
			var result = response;
			var opt = "";
			var page_total = 0;

			$("#grpGroup").val(result.cgr_group);
			$("#grpGroupName").val(result.cgr_group_name);
			$("#grpNote").val(result.cgr_note);			
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

// 공통코드 그룹 수정 or 생성
function modify_common_group() {
	var actionType		= common_group_mode
	var cgr_group 		= $("#grpGroup").val();
	var cgr_group_name 	= $("#grpGroupName").val();
	var cgr_note 		= $("#grpNote").val();

	var msg = "생성";
	var url = "ajax_put_common_group";
	if (actionType == "MODIFY") {
		msg = "수정";
		url = "ajax_modify_common_group";
	}

	//공통코드 그룹 적합성 체크
	if (!valid_common_group(cgr_group, cgr_group_name, cgr_note)) {
		return;
	}

	if (actionType == "PUT") {
		if (!duplication_common_group(cgr_group)) {
			return;
		}
	}

	if (!confirm(cgr_group + "그룹을 " + msg + "하시겠습니까?")) {
		return;
	}

	$.ajax({
		method : "POST",
		url : url,
		data : {
			"cgr_group" : cgr_group,
			"cgr_group_name" : cgr_group_name,
			"cgr_note" : cgr_note,
		},
		async : false,
		success : function(response) {
			var result = response;
			if (result > 0) {
				alert(cgr_group + "그룹을 " + msg + "하였습니다.");
				list_common_group("1", pagination_cnt, keyword_data, keytype_data);	// 새로 고침
			} else {
				alert(cgr_group + "그룹 " + msg + "에 실패하였습니다.");
			}
			$("#grpModModal").modal("hide");

		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

// 공통 그룹명 중복확인
function duplication_common_group(cgr_group) {
	var validation = true;

	$.ajax({
		method : "POST",
		url : "ajax_dup_common_group",
		data : {
			"cgr_group" : cgr_group
		},
		async : false,
		success : function(response) {
			var result = response;
			if (result > 0) { // 중복
				alert(cgr_group + "그룹은 이미 존재합니다.");
				$("#grpGroup").focus();
				validation = false;
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});

	return validation;
}

// 공통코드 그룹 데이터 적합성 체크
function valid_common_group(cgr_group, cgr_group_name, cgr_note) {
	var validation = true;
	var regexr = /[a-z0-9_]{8,16}$/;

	if (!regexr.test(cgr_group)) {
		alert('공통코드 그룹은 8자리 이상 16자리 이하의 영문 소문자와 숫자만 사용이 가능합니다.');
		validation = false;
		return validation;
	}

	if (cgr_group_name.length < 1) {
		alert('공통코드 그룹명을 입력하시기 바랍니다.'); // 25자까지
		validation = false;
		return validation;
	}

	var note_byte = get_byte_calc(cgr_note);

	if (note_byte > 1000) {
		alert('공통코드 설명은 1000바이트 미만으로 가능합니다.');
		validation = false;
		return validation;
	}

	if (note_byte == 0) {
		alert('공통코드 설명을 입력하시기 바랍니다.');
		validation = false;
		return validation;
	}

	return validation;
}

// 바이트 계산
function get_byte_calc(target) {
	var calc_byte = 0;
	for (var idx = 0; idx < target.length; idx++) {
		var note_char = escape(target.charAt(idx));

		if (note_char.length == 1)
			calc_byte++;
		else if (note_char.indexOf("%u") != -1)
			calc_byte += 2;
		else if (note_char.indexOf("%") != -1)
			calc_byte += note_char.length / 3;
	}

	return calc_byte;
}

// 공통코드 그룹 삭제
function delete_group(id) {
	if(confirm("정말 " + id + "그룹을 삭제하시겠습니까?")) {
		$.ajax({
			method : "POST",
			url : "ajax_delete_common_group",
			data : {
				"cgr_group" : id
			},
			async : false,
			success : function(response) {
				var result = response;
				if (result > 0) {
					alert(id + "그룹을 삭제하였습니다.");
					list_common_group("1", pagination_cnt, keyword_data, keytype_data);	// 새로 고침
				} else {
					alert(id + "그룹 삭제를 실패하였습니다.");
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
	}
}

////////////////////////////////////////
//공통코드 그룹 검색 함수///////////////////////
////////////////////////////////////////

// 페이지 표시 갯수에 따른 제검색
function change_page_count(page_cnt) {
	pagination_cnt = page_cnt;
	list_common_group("1", pagination_cnt, keyword_data, keytype_data);
}

// 검색 버튼 클릭시 검색
function search_keyword() {
	var keyword_data = $("#keyword").val();
	var keytype_data = $("#keytype").val();

	list_common_group("1", pagination_cnt, keyword_data, keytype_data);
}

// 앤터 입력시 
function search_enter() {
	if (event.keyCode == 13) {
		search_keyword();
	}
}
