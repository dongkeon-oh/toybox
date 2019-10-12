var pagination_index = 1;
var pagination_cnt = 10;
var keyword_data = "";
var keytype_data = "id";

$(function() {
	list_user(pagination_index, pagination_cnt, keyword_data, keytype_data);
	
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_user(pagination_index, pagination_cnt, keyword_data, keytype_data);
	});
	
	$(document).on("click",".user_detail",function(){
		var u_id = $(this).attr("id");
		user_info(u_id);
	});
	
	$(".btn_modify").on("click", function(){
		var u_id = $(this).attr("id");
		modify_user(u_id);
	})
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

					var table_row_type = "";
					if (item.usr_active_code == "비활성화") {
						table_row_type = "bg-danger";
					}

					opt = opt + "<tr class='" + table_row_type + "' id='" + item.usr_id +"'>";
					opt = opt + "	<td>" + (start_idx + index) + "</td>";
					opt = opt + "	<td>" + item.usr_id + "</td>";
					opt = opt + "	<td>" + item.usr_name + "</td>";
					opt = opt + "	<td>" + item.usr_active_code + "</td>";
					opt = opt + "	<td>";
					opt = opt + "		<button type='button' class='btn btn-success btn-sm user_detail' data-toggle='modal' data-target='#user_modal' id='" + item.usr_id + "' >상세보기</button>";
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

function user_info(user_id) {
	$.ajax({
		method : "POST",
		url : "ajax_get_user",
		data : {
			"userId" : user_id,
			"userType" : "user_type",
			"userActive" : "user_active"
			
		},
		async : false,
		success : function(response) {
			var result = response;
			var u_info = result.user_info;
			var type_info = result.user_type;
			var active_info = result.user_active;

			$("#usr_id").val(u_info.usr_id);
			$("#usr_name").val(u_info.usr_name);
			
			var append_type = "";
			$.each(type_info, function(index, item) {
				append_type = append_type + "<option value='" + item.ccd_code + "'";
				if(u_info.usr_type == item.ccd_code){
					append_type = append_type + " selected='selected'";
				}
				append_type = append_type + ">" + item.ccd_codename + "</option>";
			});			
			
			$("#usr_type").html(append_type);		
			$("#usr_email").val(u_info.usr_email);
			$("#usr_sms").val(u_info.usr_sms);
			$("#usr_kakao").val(u_info.usr_kakao);
			
			var append_active = "";
			$.each(active_info, function(index, item) {
				append_active = append_active + "<option value='" + item.ccd_code + "'";
				if(u_info.usr_active == item.ccd_code){
					append_active = append_active + " selected='selected'";
				}
				append_active = append_active + ">" + item.ccd_codename + "</option>";
			});
			
			$("#usr_active").html(append_active);
			$("#usr_question_code").val(u_info.usr_question_code);
			$("#usr_question").val(u_info.usr_question);
			$("#usr_answer").val(u_info.usr_answer);

			$(".btn_modify").attr("id",user_id);
		},
		error : function(request, status, error) {
			alert("유저 조회에 실패하였습니다.");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
}

function modify_user(id) {
	var refresh = false;
	$.ajax({
		method : "POST",
		url : "ajax_modify_user",
		data : {
			"usr_id" : id,
			"usr_active" : $("#usr_active").val(),
			"usr_type" : $("#usr_type").val()
		},
		async : false,
		success : function(response) {
			var result = response;
			if(result > 0){
				refresh = true;
				alert(id+" 사용자 정보를 수정하였습니다.");
			}else{
				alert(id+" 사용자 정보 수정에 실패했습니다.");
			}
		},
		error : function(request, status, error) {
			alert(id+" 사용자 정보 수정에 실패했습니다.");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		},
		complete : function(){
			if(refresh){
				list_user(pagination_index, pagination_cnt, keyword_data, keytype_data);
			}
		}
	});
}

function change_page_count(page_cnt) {
	pagination_cnt = page_cnt;
	list_user("1", pagination_cnt, keyword_data, keytype_data);
}

//검색 버튼 클릭시 검색
function search_keyword() {
	var keyword_data = $("#keyword").val();
	var keytype_data = $("#keytype").val();

	list_user("1", pagination_cnt, keyword_data, keytype_data);
}

function search_enter() {
	if (event.keyCode == 13) {
		list_user("1", pagination_cnt, keyword_data, keytype_data);
	}
}
