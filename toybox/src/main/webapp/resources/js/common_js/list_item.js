var pagination_index = 1;
var pagination_cnt = 10;
var keyword_data = "";

var code_group;
var search_type;

$(function() {
	list_item(1, 10, "");
	
	$(document).on("click",".page-item",function(){
		var class_set = $(this).attr("class");
		var class_index = class_set.indexOf("page_");
		var pagination_index = class_set.substring(class_index);
		pagination_index = pagination_index.replace("page_","");

		list_item(pagination_index, pagination_cnt, keyword_data);
	});
	
	$("#put_item").on("click",function(){
		$("#item_modal_title").html("아이템 추가");
	});
	
	$(".item_sub_option").on("click",function(){
		search_type = $(this).attr("id");	
		search_init("init");
	});
	
	$("#sub_search_btn").on("click",function(){
		if(search_type == "sub_itm_useyn"){
			get_common_code_search("item_useyn");
		}else if(search_type == "sub_itm_type"){
			get_common_code_search("item_type");
		}else if(search_type == "sub_itm_mainitem"){
			get_item_search();
		}else if(search_type == "sub_itm_owner"){
			get_user_search();
		}
	});
	
	$("#btn_apply").on("click",function(){
		if(search_type == "sub_itm_useyn"){
			search_value("itm_useyn", "_code");
		}else if(search_type == "sub_itm_type"){
			search_value("itm_type", "_code");
		}else if(search_type == "sub_itm_mainitem"){
			search_value("itm_mainitem", "_name");
		}else if(search_type == "sub_itm_owner"){
			search_value("itm_owner", "_name");
		}
	});	
});

function put_item() {
	var item_name = $("#itm_name").val();
	var item_type = $("#itm_type").val();
	var item_owner = $("#itm_owner").val();
	var item_mainitem = $("#itm_mainitem").val();
	var item_note = $("#itm_note").val();

	$.ajax({
		method : "POST",
		url : "ajax_put_item",
		data : {
			"itm_name" : item_name,
			"itm_type" : item_type,
			"itm_owner" : item_owner,
			"itm_mainitem" : item_mainitem,
			"itm_note" : item_note
		},
		async : false,
		success : function(response) {
			if(response > 0){
				alert("["+item_name+"] 아이템이 추가되었습니다.");
			}else{
				alert("["+item_name+"] 아이템 추가에 실패했습니다.");
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function get_common_code_search(c_group) {
	code_group = c_group;
	var ccd_codename = $("#sub_keyword").val();

	$.ajax({
		method : "POST",
		url : "ajax_commoncode_search",
		data : {
			"ccd_group" : code_group,
			"ccd_codename" : ccd_codename,
			"search_type" : "_SIMPLE_"
		},
		async : false,
		success : function(response) {
			if(response.length == 0){
				search_init("no_data");
			}else{
				var append_data = "";	//임시
				$.each(response, function(index, item) {
					append_data = append_data + "<option value='" + item.ccd_code + "'>" + item.ccd_codename + "</option>";
				});	
				$("#sub_option").html(append_data);
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function get_user_search() {
	var usr_name = $("#sub_keyword").val();

	$.ajax({
		method : "POST",
		url : "ajax_user_search",
		data : {
			"usr_name" : usr_name,
			"search_type" : "_SIMPLE_"
		},
		async : false,
		success : function(response) {
			if(response.length == 0){
				search_init("no_data");
			}else{
				var append_data = "";	//임시
				$.each(response, function(index, item) {
					append_data = append_data + "<option value='" + item.usr_id + "'>" + item.usr_name + "</option>";
				});	
				$("#sub_option").html(append_data);
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function get_item_search() {
	var itm_name = $("#sub_keyword").val();

	$.ajax({
		method : "POST",
		url : "ajax_item_search",
		data : {
			"itm_name" : itm_name,
			"search_type" : "_SIMPLE_"
		},
		async : false,
		success : function(response) {
			if(response.length == 0){
				search_init("no_data");
			}else{
				var append_data = "";	//임시
				$.each(response, function(index, item) {
					append_data = append_data + "<option value='" + item.itm_id + "'>" + item.itm_name + "</option>";
				});	
				$("#sub_option").html(append_data);
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function search_init(init_type){
	var title_text;
	
	if(search_type == "sub_itm_useyn") 			title_text = "아이템 상태";
	else if(search_type == "sub_itm_type") 		title_text = "아이템 종류";
	else if(search_type == "sub_itm_mainitem") 	title_text = "메인 아이템";
	else if(search_type == "sub_itm_owner")		title_text = "소유자";

	$(".label_text").text(title_text);
	$(".title_text").text(title_text+" 검색");
	
	if(init_type == "init"){
		$("#sub_keyword").val("");
	}
	$("#sub_option").html('<option value="_NODATA_">검색결과 없음</option>');
}

function search_value(value_type, value_tail){
	if($("#sub_option").val() == "_NODATA_"){
		alert($(".label_text").text()+"을(를) 확인해주십시오.");
		return;
	}
	$("#"+value_type).val($("#sub_option").val());
	$("#"+value_type+value_tail).val($("#sub_option option:selected").text());
	
	$('#sub_modal').modal("hide");
}

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