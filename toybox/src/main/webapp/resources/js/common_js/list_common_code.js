var target_group;
var target_code;

$(function() {
	// 공통코드 버튼
	$(document).on("click",".list_common_code",function(){
		var g_id = $(this).attr("id");
		list_common_code(g_id);
	});
});


var target_seq;

function list_common_code(group) {
	target_group = group;
	$("#code_title").text("[" + target_group + "]그룹 공통코드");

	$.ajax({
		method : "POST",
		url : "ajax_list_common_code",
		data : {
			"cgr_group" : target_group
		},
		async : false,
		success : function(response) {

			$("#code_tbody").html("");

			var opt = "";
			var result = response;
			if (result.length > 0) {
				$.each(
					result,
					function(index, item) {
						opt = opt + "<tr class='code_list'>"
							+ "	<td>" + item.ccd_code + "</td>"
							+ "	<td>" + item.ccd_codename + "</td>"
							+ "	<td>" + item.ccd_order + "</td>"
							+ "	<td>"
							+ "		<button type='button' class='btn btn-primary code_modify' data-toggle='modal' data-target='#detailModModal' id='" + item.ccd_code + "'>수정</button>"
							+ "		<button type='button' class='btn btn-danger code_delete' id='" + item.ccd_code + "'>삭제</button>"
							+ "	</td>"
							+ "</tr>";
					});
			} else {
				opt = "<tr id='empty_code'>"
					+ "	<td colspan='8' style='float:center;'>"
					+ "		<button type='button' class='btn btn-primary code_new' data-toggle='modal' data-target='#detailModModal'>공통코드 추가</button>를 클릭해 공통코드를 추가하시기 바랍니다."
					+ "	</td>"
					+"</tr>";
			}
			$("#code_tbody").html(opt);

			$("#code_tfooter").html("");
			$("#code_tfooter").append("<button type='button' class='btn btn-primary code_new' data-toggle='modal' data-target='#detailModModal'>공통코드 추가</button>");
			$("#code_tfooter").append("<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>");
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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

// 적합성 체크
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
	var dupCasvalidatione;

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
