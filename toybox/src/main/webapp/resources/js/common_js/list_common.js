$(function() {
	list_common_group(1, 10, "", "group");
});

var target_group;
var target_code;
var target_seq;

function mod_common_group(actionType) {
	var cgr_group = $("#grpGroup").val();
	var cgr_group_name = $("#grpGroupName").val();
	var cgr_note = $("#grpNote").val();

	var msg = "생성";
	var url = "ajax_put_common_group";
	if (actionType == "MODIFY") {
		msg = "수정";
		url = "ajax_modify_common_group";
	}

	var modConfirm = confirm(cgr_group + "그룹을 " + msg + "하시겠습니까?");
	if (!modConfirm) {
		return;
	}

	var valid = valid_common_group(cgr_group, cgr_group_name, cgr_note);
	if (!valid) {
		return;
	}

	if (actionType == "PUT") {
		var dupCase = duplication_common_group(cgr_group);
		if (dupCase) {
			return;
		}
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
				clear_group();
			} else {
				alert(cgr_group + "그룹 " + msg + "에 실패하였습니다.");
			}
			$("#grpModModal").modal("hide");
			search_keyword("refresh");
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function duplication_common_group(cgr_group) {
	var dupCase = false;

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
				dupCase = true;
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});

	return dupCase;
}

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

	// 바이트 계산
	// var note_byte = 0;
	// for(var idx=0; idx < cgr_note.length; idx++) {
	// var note_char = escape(cgr_note.charAt(idx));
	//         
	// if( note_char.length==1 ) note_byte++;
	// else if( note_char.indexOf("%u")!=-1 ) note_byte += 2;
	// else if( note_char.indexOf("%")!=-1 ) note_byte += note_char.length/3;
	// }
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

function get_byte_calc(target) {
	// 바이트 계산
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

function clear_group() {
	$("#grpGroup").val("");
	$("#grpGroupName").val("");
	$("#grpNote").val("");
	$("#grpGroup").attr("readonly", false); // 공통코드 변경가능

	// mod_common_group 기능 변경 PUT
	$("#putModifyGrpBtn").removeAttr("onclick");
	$("#putModifyGrpBtn").attr("onclick", "mod_common_group('PUT')");
	$("#putModifyGrpBtn").text("추가");
}

function modify_group(idx) {
	$("#grpGroup").val($("#trGrp" + idx).text());
	$("#grpGroupName").val($("#trGrpNm" + idx).text());
	$("#grpNote").val($("#trNote" + idx).text());
	$("#grpGroup").attr("readonly", true); // 공통코드 변경불가

	// mod_common_group 기능 변경 MODIFY
	$("#putModifyGrpBtn").removeAttr("onclick");
	$("#putModifyGrpBtn").attr("onclick", "mod_common_group('MODIFY')");
	$("#putModifyGrpBtn").text("수정");
	$("#code_detail_title").text("공통코드 수정");
}

function delete_group(idx) {
	var cgr_group = $("#trGrp" + idx).text().trim();

	var delConfirm = confirm("정말 " + cgr_group + "그룹을 삭제하시겠습니까?");
	if (delConfirm) {

		$.ajax({
			method : "POST",
			url : "ajax_delete_common_group",
			data : {
				"cgr_group" : cgr_group
			},
			async : false,
			success : function(response) {
				var result = response;
				if (result > 0) {
					alert(cgr_group + "그룹을 삭제하였습니다.");
				} else {
					alert(cgr_group + "그룹 삭제를 실패하였습니다.");
				}
				search_keyword("refresh");
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
	}
}

function list_common_group(page_no, page_cnt, keyword, keytype) {

	if (page_cnt == undefined || page_cnt == "") {
		page_cnt = "10";
	}
	if (keyword == undefined) {
		keyword = "";
	}

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
						table_row_type = "table-danger";
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

			set_pagination(page_cnt, page_total, page_no, keyword,
					keytype);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

// 에러 수정 필요
function set_pagination(page_cnt, page_total, page_no, keyword, keytype) {
	var page_area_full_size = Math.ceil(page_total / page_cnt);
	if (page_area_full_size > 5) {
		page_area_full_size = 5;
	}

	var append = "";
	var this_page = "";
	$("#pagination_area").html("");
	for (var i = 1; page_area_full_size >= i; i++) {
		if (i == page_no) {
			this_page = " active";
		} else {
			this_page = "";
		}

		append = append
				+ '<li class="page-item'
				+ this_page
				+ '"><a class="page-link" href="#" onclick="list_common_group(\''
				+ i + '\', \'' + page_cnt + '\', \'' + keyword + '\', \''
				+ keytype + '\');">' + i + '</a></li>';
	}
	$("#pagination_area").html(append);
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

function list_common_code(group) {
	target_group = group;
	$("#code_title").text("[" + group + "]그룹 공통코드");

	$
			.ajax({
				method : "POST",
				url : "ajax_list_common_code",
				data : {
					"cgr_group" : group
				},
				async : false,
				success : function(response) {

					$("#code_tbody").html("");

					var opt = "";
					var result = response;
					if (result.length > 0) {
						$
								.each(
										result,
										function(index, item) {
											if (item.ccd_useyn != 'N') {
												opt = opt
														+ "<tr class='code_list'>";
											} else {
												opt = opt
														+ "<tr class='code_list table-danger'>";
											}

											opt = opt + "	<td>" + item.ccd_code
													+ "</td>";
											opt = opt + "	<td>"
													+ item.ccd_codename
													+ "</td>";
											opt = opt + "	<td>"
													+ item.ccd_order + "</td>";
											opt = opt + "	<td>";
											if (item.ccd_useyn != 'N') {
												opt = opt
														+ "		<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#detailModModal' onClick='add_code(\""
														+ item.ccd_group
														+ "\", \""
														+ item.ccd_seq
														+ "\")' >수정</button>";
												opt = opt
														+ "		<button type='button' class='btn btn-danger' onClick='delete_code(\""
														+ item.ccd_seq
														+ "\", \""
														+ item.ccd_code
														+ "\", \"" + group
														+ "\")'>삭제</button>";
											} else {
												opt = opt + "삭제됨";
											}
											opt = opt + "	</td>";
											opt = opt + "</tr>";
										});
					} else {
						opt = "<tr id='empty_code'><td colspan='8' style='float:center;'><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#detailModModal' onClick='add_code(\""
								+ group
								+ "\", \"_NEW_\")' >공통코드 추가</button>를 클릭해 공통코드를 추가하시기 바랍니다.</td></tr>";
					}

					$("#code_tbody").html(opt);

					$("#code_tfooter").html("");
					$("#code_tfooter")
							.append(
									"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#detailModModal' onClick='add_code(\""
											+ group
											+ "\", \"_NEW_\")' >공통코드 추가</button>");
					$("#code_tfooter")
							.append(
									"<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>");
				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});
}

function clear_code() {
	$(".code_mod").val("");
	$("#ccd_code").attr("readonly", false); // 공통코드 변경가능
}

function add_code(group, seq) {
	target_code = group;
	target_seq = seq;

	clear_code();

	if (seq != "_NEW_") {
		view_common_code(seq);
		$("#ccd_code").attr("readonly", true); // 공통코드 변경가능
		$("#code_detail_title").text("공통코드 수정");
		$("#modify_code").text("수정");
	}else{
		$("#code_detail_title").text("공통코드 추가");
		$("#modify_code").text("추가");
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

function delete_code(seq, code, group) {

	var delConfirm = confirm("정말 " + code + "코드를 삭제하시겠습니까?");
	if (delConfirm) {

		$.ajax({
			method : "POST",
			url : "ajax_delete_common_code",
			data : {
				"ccd_seq" : seq
			},
			async : false,
			success : function(response) {
				var result = response;
				if (result > 0) {
					alert(code + "코드를 삭제하였습니다.");
					list_common_code(group);
				} else {
					alert(code + "코드 삭제를 실패하였습니다.");
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
	}
}

// 모달 닫을 시 이전 모달이 움직이는 상황을 위한 함수
// 사용하지 않음
function close_detail_Modal(){
	$("#detailModModal").css("display","none");
	$(".modal-backdrop.show").eq(1).removeClass();
}