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
		item_init();
		$("#item_modal_title").html("아이템 추가");
		$("#btn_modify").html("추가");
	});
	
	$(document).on("click", ".detail_item", function(){
		item_init();
		var item_id = $(this).attr("id");
		detail_item(item_id);
		$("#item_modal_title").html("아이템 수정");
		$("#btn_modify").html("수정");
	});
	
	$(".item_sub_option").on("click",function(){
		search_type = $(this).attr("id");	
		search_init("init");
	});
	
	$("#sub_search_btn").on("click",function(){
		search_sub('click');
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
	
	$("#btn_modify").on("click",function(){
		var item_id = $("#itm_id").val();
		if(item_id == undefined || item_id == ""){
			put_item();
		}else{
			update_item(item_id);
		}
	});
});

function put_item() {
	var item_name = $("#itm_name").val();
	var item_type = $("#itm_type").val();
	var item_owner = $("#itm_owner").val();
	var item_image = $("#itm_image").val();
	var item_mainitem = $("#itm_mainitem").val();
	var item_useyn = $("#itm_useyn").val();
	var item_note = $("#itm_note").val();

	if(item_name.replace(/(\s)/g,"") == "" || item_name == undefined){
		alert("아이템 이름을 확인하십시오.");
		$("#itm_name").focus();
	}else if(get_byte_calc(item_name) > 1000){
		alert("아이템 이름이 너무 깁니다. 100byte 미만으로 작성해주시기 바랍니다.");
		$("#itm_name").focus();
	}else if(item_type == ""){
		alert("아이템 상태를 입력해 주시기 바랍니다.");
		$("#sub_itm_type").focus();
	}else if(item_owner == ""){
		alert("소유자를 입력해 주시기 바랍니다.");
		$("#sub_itm_owner").focus();
	}else if(item_useyn == ""){
		alert("아이템 상태를 입력해 주시기 바랍니다.");
		$("#sub_itm_useyn").focus();
	}else if(get_byte_calc(item_note) > 1000){
		alert("설명이 너무 깁니다. 1000byte 미만으로 작성해주시기 바랍니다.");
		$("#itm_note").focus();
	}	
		
	$.ajax({
		method : "POST",
		url : "ajax_put_item",
		data : {
			"itm_name" : item_name,
			"itm_type" : item_type,
			"itm_owner" : item_owner,
			"itm_image" : item_image,
			"itm_mainitem" : item_mainitem,
			"itm_useyn" : item_useyn,
			"itm_note" : item_note,
			
			"cdt_condition" : "rentable",
//			"cdt_item" : ???,
			"cdt_user" : item_owner,
			"cdt_location" : item_owner,
			"cdt_return" : item_owner,
			"cdt_note" : "신규 생성",
			"cdt_fromdate" : "2100/12/31",
			"cdt_todate" : "1989/06/07"
		},
		async : false,
		success : function(response) {
			if(response > 0){
				alert("["+item_name+"] 아이템이 추가되었습니다.");
				list_item(pagination_index, pagination_cnt, keyword_data);
				$('#item_modal').modal("hide");
			}else{
				alert("["+item_name+"] 아이템 추가에 실패했습니다.");
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function item_init(){
	$("#itm_id").val("");
	$("#itm_name").val("");
	$("#itm_type").val("");
	$("#itm_type_code").val("");
	$("#itm_owner").val("");
	$("#itm_owner_name").val("");
	$("#itm_mainitem").val("");
	$("#itm_mainitem").val("");
	$("#itm_useyn").val("");
	$("#itm_useyn_code").val("");
	$("#itm_note").val("");
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
	var s_option = $("#sub_option").val();
	if(s_option == "_NODATA_" && value_type != "itm_mainitem"){
		alert($(".label_text").text()+"을(를) 확인해주십시오.");
	}else if(value_type == "itm_mainitem"){
		$("#"+value_type).val("");
		$("#"+value_type+value_tail).val("");
		$('#sub_modal').modal("hide");
	}else{
		$("#"+value_type).val(s_option);
		$("#"+value_type+value_tail).val($("#sub_option option:selected").text());
		$('#sub_modal').modal("hide");
	}	
}

function list_item(page_no, page_cnt, keyword) {

	var end_idx = page_cnt * page_no;
	var start_idx = end_idx - page_cnt + 1;

	$.ajax({
		method : "POST",
		url : "ajax_list_item_admin",
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
			$.each(result, function(index, item) {
					page_total = item.cnt;
					var table_row_type = "";	// 아이템 상태 추가시 사용
					
					opt = opt + "<tr class='" + table_row_type + "'>";
					opt = opt + "	<td>" + (start_idx + index) + "</td>";
					opt = opt + "	<td>" + item.itm_name + "</td>";
					opt = opt + "	<td>" + item.itm_type_code + "</td>";
					opt = opt + "	<td>" + item.itm_owner_name + "</td>";
					opt = opt + "	<td>";
					opt = opt + "		<button type='button' class='btn btn-success btn-sm detail_item' data-toggle='modal' data-target='#item_modal' id='" + item.itm_id + "' >상세보기</button>";
					opt = opt + "		<button type='button' class='btn btn-secondary btn-sm' data-toggle='modal' data-target='#' disabled='disabled' id='log_" + item.itm_id + "' >이력보기(미구현)</button>";
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			$("tbody").html(opt);

			set_pagination(pagination_cnt, page_total, pagination_index, keyword, "");
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

function search_sub(type) {
	if ((type == 'onkey' && event.keyCode == 13) || type == 'click') {
		if(search_type == "sub_itm_useyn"){
			get_common_code_search("item_useyn");
		}else if(search_type == "sub_itm_type"){
			get_common_code_search("item_type");
		}else if(search_type == "sub_itm_mainitem"){
			get_item_search();
		}else if(search_type == "sub_itm_owner"){
			get_user_search();
		}
	}
}

function detail_item(itm_id) {
	$.ajax({
		method : "POST",
		url : "ajax_detail_item",
		data : {
			"itm_id" : itm_id
		},
		async : false,
		success : function(response) {
			var result = response;

			$("#itm_id").val(result.itm_id);
			$("#itm_name").val(result.itm_name);
			$("#itm_type").val(result.itm_type);
			$("#itm_type_code").val(result.itm_type_code);
			$("#itm_owner").val(result.itm_owner);
			$("#itm_owner_name").val(result.itm_owner_name);
			$("#itm_mainitem").val(result.itm_mainitem);
			$("#itm_mainitem_name").val(result.itm_mainitem_name);
			$("#itm_useyn").val(result.itm_useyn);
			$("#itm_useyn_code").val(result.itm_useyn_code);
			$("#itm_note").val(result.itm_note);
		},
		error : function(request, status, error) {
			alert("아이템 조회에 실패하였습니다.");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
}

function update_item(item_id) {
	var item_name = $("#itm_name").val();
	var item_type = $("#itm_type").val();
	var item_owner = $("#itm_owner").val();
	var item_image = $("#itm_image").val();
	var item_mainitem = $("#itm_mainitem").val();
	var item_useyn = $("#itm_useyn").val();
	var item_note = $("#itm_note").val();

	if(item_name.replace(/(\s)/g,"") == "" || item_name == undefined){
		alert("아이템 이름을 확인하십시오.");
		$("#itm_name").focus();
	}else if(get_byte_calc(item_name) > 1000){
		alert("아이템 이름이 너무 깁니다. 100byte 미만으로 작성해주시기 바랍니다.");
		$("#itm_name").focus();
	}else if(item_type == ""){
		alert("아이템 상태를 입력해 주시기 바랍니다.");
		$("#sub_itm_type").focus();
	}else if(item_owner == ""){
		alert("소유자를 입력해 주시기 바랍니다.");
		$("#sub_itm_owner").focus();
	}else if(item_useyn == ""){
		alert("아이템 상태를 입력해 주시기 바랍니다.");
		$("#sub_itm_useyn").focus();
	}else if(get_byte_calc(item_note) > 1000){
		alert("설명이 너무 깁니다. 1000byte 미만으로 작성해주시기 바랍니다.");
		$("#itm_note").focus();
	}	
		
	$.ajax({
		method : "POST",
		url : "ajax_update_item",
		data : {
			"itm_id" : item_id,
			"itm_name" : item_name,
			"itm_type" : item_type,
			"itm_owner" : item_owner,
			"itm_image" : item_image,
			"itm_mainitem" : item_mainitem,
			"itm_note" : item_note,
			"itm_useyn" : item_useyn
		},
		async : false,
		success : function(response) {
			if(response > 0){
				alert("["+item_name+"] 아이템이 수정되었습니다.");
				list_item(pagination_index, pagination_cnt, keyword_data);
			}else{
				alert("["+item_name+"] 아이템 수정에 실패했습니다.");
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
		}
	});
}