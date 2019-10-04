var pagination_index = 1;

var keyword_data = "";
var keytype_data = "id";

$(function() {
	list_common_group(1, 10, "", "id");
	
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_common_group(pagination_index, $("#cnt").val(), keyword_data, keytype_data);
	});
});


var target_group;
var target_code;
var target_seq;


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
					if (note.length > 30) {
						note = "<marquee behavior=scroll>"
								+ note + "</marquee>";
					}

					var table_row_type = "";// =
											// "table-secondary";
					var use_yn = "사용";
					if (item.cgr_useyn == 'N') {
						use_yn = "삭제됨";
						table_row_type = "bg-danger";
					}

					opt = opt + "<tr class='"
							+ table_row_type + "'>";
					opt = opt + "	<td>"
							+ (start_idx + index) + "</td>";
					opt = opt + "	<td id='trGrp"
							+ (start_idx + index) + "'>"
							+ item.cgr_group + "</td>";
					opt = opt + "	<td id='trGrpNm"
							+ (start_idx + index) + "'>"
							+ item.cgr_group_name + "</td>";
					opt = opt + "	<td id='trNote"
							+ (start_idx + index) + "'>"
							+ note + "</td>";
					opt = opt + "	<td>" + use_yn + "</td>";
					opt = opt + "	<td>";
					if (item.cgr_useyn != 'N') {
						opt = opt
								+ "		<button type='button' class='btn btn-primary btn-sm modGrpBtn'  data-toggle='modal' data-target='#grpModModal' onClick='modify_group("
								+ (start_idx + index)
								+ ")' >수정</button>";
						opt = opt
								+ "		<button type='button' class='btn btn-danger btn-sm' onClick='delete_group("
								+ (start_idx + index)
								+ ")'>삭제</button>";
						opt = opt
								+ "		<button type='button' class='btn btn-secondary btn-sm' data-toggle='modal' data-target='#codeModModal' onClick='list_common_code(\""
								+ item.cgr_group
								+ "\")'>코드추가</button>";
					}
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			$("#grp_tbody").html(opt);

			set_pagination(page_cnt, page_total, page_no, keyword, keytype);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function change_page_count(page_cnt) {
	list_common_group("1", page_cnt, $("#keyword").val(), $("#keytype").val());
}

function search_keyword(type) {
	if (type == 'refresh') {
		$("#keyword").val("");
		$("#keytype option:eq(0)").prop("selected", true);
	} else if (keytype == 'sel' && type == 'onkey') {
		alert("검색조건을 선택하시기 바랍니다.");
		return;
	}

	var page_cnt = $("#cnt").val();
	var keyword = $("#keyword").val();
	var keytype = $("#keytype").val();

	list_common_group("1", page_cnt, keyword, keytype);
}

function search_enter() {
	if (event.keyCode == 13) {
		search_keyword();
	}
}

function modify_code() {
	var msg = "수정";
	var url = "ajax_modify_common_code";
	if (target_seq == "_NEW_") {
		msg = "추가";
		url = "ajax_put_common_code";
	}
	$("#modify_code").text(msg);
	
	var code = $("#ccd_code").val();
	var name = $("#ccd_codename").val();
	var order = $("#ccd_order").val();
	var detail1 = $("#ccd_detail1").val();
	var detail2 = $("#ccd_detail2").val();
	var detail3 = $("#ccd_detail3").val();
	var note = $("#ccd_note").val();
	var valid = true;

	valid = valid_common_code(code, name, order, detail1, detail2, detail3, note);
	if (!valid){
		return;
	}
		
	valid = duplication_common_code(target_group, code, order, target_seq);
	if (!valid){
		return;
	}

	$.ajax({
		method : "POST",
		url : url,
		data : {
			"ccd_seq" : target_seq,
			"ccd_code" : code,
			"ccd_group" : target_group,
			"ccd_codename" : name,
			"ccd_order" : order,
			"ccd_detail1" : detail1,
			"ccd_detail2" : detail2,
			"ccd_detail3" : detail3,
			"ccd_note" : note
		},
		async : false,
		success : function(response) {
			if (response == '1') {
				alert(target_group + "그룹에 " + code + "코드를 " + msg
						+ "하였습니다.");
			} else {
				alert(target_group + "그룹에 " + code + "코드 " + msg
						+ "에 실패하였습니다.");
			}
			$("#detailModModal").modal("hide");
			list_common_code(target_group);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function valid_common_code(code, name, order, detail1, detail2, detail3, note) {
	var validation = true;
	var regexr_code = /[a-zA-Z0-9_\/]{1,100}$/;
	var regexr_order = /[0-9]$/;

	if (code.length == 0) {
		alert('공통코드를 입력하시기 바랍니다.');
		validation = false;
		return validation;
	}
	if (!regexr_code.test(code)) {
		alert('공통코드는 1자리 이상 100자리 이하의 영문과 숫자, 특수문자 "_" 만 사용이 가능합니다.');
		validation = false;
		return validation;
	}

	var byte_codename = get_byte_calc(name);

	if (byte_codename > 100) {
		alert('공통코드명은 100바이트 미만으로 가능합니다.');
		validation = false;
		return validation;
	} else if (byte_codename == 0) {
		alert('공통코드명을 입력하시기 바랍니다.');
		validation = false;
		return validation;
	}

	if (!regexr_order.test(order)) {
		alert('정렬순서는 1자리 이상 4자리 이하의 숫자만 사용이 가능합니다.');
		validation = false;
		return validation;
	}

	var byte_detail1 = get_byte_calc(detail1);
	var byte_detail2 = get_byte_calc(detail2);
	var byte_detail3 = get_byte_calc(detail3);

	if ((byte_detail1 > 1000) || (byte_detail2 > 1000) || (byte_detail3 > 1000)) {
		alert('공통코드 상세는 1000바이트 미만으로 가능합니다.');
		validation = false;
		return validation;
	}

	var byte_note = get_byte_calc(note);

	if (byte_note > 1000) {
		alert('공통코드 설명은 1000바이트 미만으로 가능합니다.');
		validation = false;
		return validation;
	}

	return validation;
}

function duplication_common_code(group, code, order, type) {
	var dupCase = true;

	$.ajax({
		method : "POST",
		url : "ajax_dup_common_code",
		data : {
			"ccd_group" : group,
			"ccd_code" : code,
			"ccd_order" : order
		},
		async : false,
		success : function(response) {
			var result = response;
			if (result.cnt > 0 && (type == "_NEW_")) { // 중복
				alert(code + "는 사용할 수 없는 공통코드입니다.");
				dupCase = false;
			} else if (result.ccd_order > 0 && result.ccd_code != code) {
				alert("정렬순서 " + order + "번는 이미 사용중입니다.");
				dupCase = false;
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});

	return dupCase;
}

function view_common_code(seq) {
	$.ajax({
		method : "POST",
		url : "ajax_view_common_code",
		data : {
			"ccd_seq" : seq
		},
		async : false,
		success : function(response) {
			var result = response;
			if (result.ccd_seq > 0) { // 중복
				$("#ccd_code").val(result.ccd_code);
				$("#ccd_codename").val(result.ccd_codename);
				$("#ccd_order").val(result.ccd_order);
				$("#ccd_detail1").val(result.ccd_detail1);
				$("#ccd_detail2").val(result.ccd_detail2);
				$("#ccd_detail3").val(result.ccd_detail3);
				$("#ccd_note").val(result.ccd_note);

				target_seq = result.ccd_seq;
			} else {
				alert("공통코드 조회에 실패하였습니다.");
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}
