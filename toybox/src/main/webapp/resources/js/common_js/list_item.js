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
	
	$(document).on("click",".item_sub_option",function(){
		var id_type = $(this).attr("id");
		sub_option(id_type, "");
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

//item_sub_option
function sub_option(type, keyword) {
	var sub_url = "ajax_item_type";
	if(type == 'itm_owner'){
		sub_url = "ajax_item_owner"
	}else if(type == 'btn_itm_mainitem'){
		sub_url = "ajax_mainitem_list"
	}else if(type == 'itm_useyn'){
		sub_url = "ajax_item_useyn"
	}
	
	$.ajax({
		method : "POST",
		url : sub_url,
		data : {
			"keyword" : keyword
		},
		async : false,
		success : function(response) {
			$("#sub_option").html("");
			
			var result = response;
			var append = "<option value='_NODATA_'>선택하세요</option>";
			$.each(
				result,
				function(index, item) {
					var id;
					var name;
					if(item.usr_id != null){
						id = item.usr_id;
						name = item.usr_name;
					}else if(item.ccd_seq != null){
						id = item.ccd_seq;
						name = item.ccd_codename;
					}else if(item.itm_id != null){
						id = item.itm_id;
						name = item.itm_name;
					}
					append = append + "<option value='" + id + "'>" + name + "</option>"
				}
			)
			$("#sub_option").html(append);
		},
		error : function(request, status, error) {
			mod_user_active_msg("fail",active);
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}