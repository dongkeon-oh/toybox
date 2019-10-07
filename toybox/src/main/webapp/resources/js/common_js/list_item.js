var pagination_index = 1;

var keyword_data = "";
var pagination_cnt = 10;

$(function() {
	list_item(1, 10, "");
	
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_item(pagination_index, pagination_cnt, keyword_data);
	});
});

function list_item(page_no, page_cnt, keyword) {

	var end_idx = page_cnt * page_no;
	var start_idx = end_idx - page_cnt + 1;

	$.ajax({
		method : "POST",
		url : "ajax_admin_item",
		data : {
			"keyword" : keyword,
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

					var item_conditon = "사용중";
					var table_row_type = "";
					if (item.itm_useyn == "M") {
						item_conditon = "분실";
						table_row_type = "bg-danger";
					}else if (item.itm_useyn == "D"){
						item_conditon = "폐기";
						table_row_type = "bg-danger";
					}

					opt = opt + "<tr class='" + table_row_type + "' id='" + item.itm_id +"'>";
					opt = opt + "	<td>" + (start_idx + index) + "</td>";
					opt = opt + "	<td>" + item.itm_name + "</td>";
					opt = opt + "	<td>" + item_conditon + "</td>";
					opt = opt + "	<td>" + item.itm_owner + "</td>";
					opt = opt + "	<td>";
					opt = opt + "		<button type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#item_modal' onClick='item_info(\"" + item.itm_id + "\")' >상세보기</button>";
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			$("tbody").html(opt);

			set_pagination(page_cnt, page_total, page_no, keyword, "");
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function change_page_count(page_cnt) {
	pagination_cnt = page_cnt;
	list_item("1", pagination_cnt, keyword_data);
}

function search_enter(type) {
	if ((type == 'onkey' && event.keyCode == 13) || type == 'click') {
		keyword_data = $("#keyword").val();
		list_item("1", pagination_cnt, keyword_data);
	}
}

function item_info(itm_id) {
	$.ajax({
		method : "POST",
		url : "ajax_detail_item",
		data : {
			"itm_id" : itm_id
		},
		async : false,
		success : function(response) {
			var result = response;
			
			var useyn = "사용중";
			if(result.itm_useyn == "M"){
				useyn = "분실";
			}else if(result.itm_useyn == "D"){
				useyn = "폐기";
			}

			$("#itm_name").val(result.itm_name);
			$("#itm_type").val(result.itm_type);
			$("#itm_owner").val(result.itm_owner);
			$("#itm_mainitem").val(result.itm_mainitem);
			$("#itm_useyn").val(useyn);
			$("#itm_note").val(result.itm_note);

			$("#btn_modify").html("수정");
			$("#btn_modify").val("M");
		},
		error : function(request, status, error) {
			alert("아이템 조회에 실패하였습니다.");
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