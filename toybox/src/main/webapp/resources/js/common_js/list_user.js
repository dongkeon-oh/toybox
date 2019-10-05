var pagination_index = 1;

var keyword_data = "";
var keytype_data = "id";

$(function() {
	list_user(1, 10, "", "id");
	
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_user(pagination_index, $("#cnt").val(), keyword_data, keytype_data);
	});
});

function list_user(page_no, page_cnt, keyword, keytype) {

	var end_idx = page_cnt * page_no;
	var start_idx = end_idx - page_cnt + 1;

	$.ajax({
		method : "POST",
		url : "ajax_list_user",
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

			$("tbody").html("");
			$.each(
				result,
				function(index, item) {
					page_total = item.cnt;

					var usr_active = "활성화";
					var table_row_type = "";
					if (item.usr_active == "N") {
						usr_active = "비활성화";
						table_row_type = "bg-danger";
					}else if (item.usr_active == "D"){
						usr_active = "탈퇴";
						table_row_type = "bg-danger";
					}

					opt = opt + "<tr class='" + table_row_type + "' id='" + item.usr_id +"'>";
					opt = opt + "	<td>" + (start_idx + index) + "</td>";
					opt = opt + "	<td>" + item.usr_id + "</td>";
					opt = opt + "	<td>" + item.usr_name + "</td>";
					opt = opt + "	<td id='" + item.usr_id +"_active'>" + usr_active + "</td>";
					opt = opt + "	<td>";
					if(item.usr_active != "D"){
						opt = opt + "		<button type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#user_modal' onClick='user_info(\"" + item.usr_id + "\")' >상세보기</button>";
					}
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			$("tbody").html(opt);

			set_pagination(page_cnt, page_total, page_no, keyword, keytype);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function change_page_count(page_cnt) {
	list_user("1", page_cnt, keyword_data, keytype_data);
}

function search_enter() {
	if (event.keyCode == 13) {
		list_user("1", $("#cnt").val(), keyword_data, keytype_data);
	}
}

function user_info(user_id) {
	$.ajax({
		method : "POST",
		url : "ajax_get_user",
		data : {
			"user_id" : user_id
		},
		async : false,
		success : function(response) {
			var result = response;
			if(result.usr_active == "Y"){
				var user_active = "활성화";
				$("#btn_active").html("비활성화");
				$("#btn_active").val("N");
				$("#btn_active").addClass("btn-danger");
				$("#btn_active").removeClass("btn-primary");
			}else if(result.usr_active == "N"){
				user_active = "비활성화";
				$("#btn_active").html("활성화");
				$("#btn_active").val("Y");
				$("#btn_active").addClass("btn-primary");
				$("#btn_active").removeClass("btn-danger");
			}else{
				user_active = "탈퇴";
			}

			$("#usr_id").val(result.usr_id);
			$("#usr_name").val(result.usr_name);
			$("#usr_type").val(result.usr_type);
			$("#usr_sms").val(result.usr_sms);
			$("#usr_kakao").val(result.usr_kakao);
			$("#usr_active").val(user_active);
			$("#usr_question").val(result.usr_question);
			$("#usr_answer").val(result.usr_answer);

		},
		error : function(request, status, error) {
			alert("유저 조회에 실패하였습니다.");
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function mod_user_active() {
	var id = $("#usr_id").val();
	var active = $("#btn_active").val();
	
	$.ajax({
		method : "POST",
		url : "ajax_active_user",
		data : {
			"usr_id" : id,
			"usr_active" :active
		},
		async : false,
		success : function(response) {
			var result = response;
			if(result > 0){
				if(active == "N"){
					$("#usr_active").val("비활성화");
					$("#"+id).addClass("bg-danger");
					$("#"+id+"_active").html("비활성화");
					$("#btn_active").html("활성화");
					$("#btn_active").val("Y");
					$("#btn_active").addClass("btn-primary");
					$("#btn_active").removeClass("btn-danger");
				}else if(active == "Y"){
					$("#usr_active").val("활성화");
					$("#"+id).removeClass("bg-danger");
					$("#"+id+"_active").html("활성화");
					$("#btn_active").html("비활성화");
					$("#btn_active").val("N");
					$("#btn_active").addClass("btn-danger");
					$("#btn_active").removeClass("btn-primary");
				}				
				mod_user_active_msg("success",active);
			}else{
				mod_user_active_msg("fail",active);
			}
			var user_active = "활성화";
			if(result.usr_type == "N"){
				user_active = "비활성화";
			}
		},
		error : function(request, status, error) {
			mod_user_active_msg("fail",active);
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function mod_user_active_msg(result, active){
	var result_msg = "실패";
	var active_type = "비활성화";
	if(result == "success"){
		result_msg = "성공";
	}
	if(active == "Y"){
		active_type = "활성화";
	}	
	alert("유저 "+active_type+"에 "+result_msg+"했습니다.");
}
