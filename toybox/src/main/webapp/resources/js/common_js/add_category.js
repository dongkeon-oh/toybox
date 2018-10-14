/**
 * 
 */
$(function() {
	change_category("category0");
});

function add_category(category_id) {
	$('#category_id').val(category_id);

	var lower_category = true;
	var category_code = category_id.replace("category",""); 
	
	if(category_code > 1){
		if($("#category"+(category_code-1)+"_sel").val()=="deselected"){
			alert("상위 카테고리를 선택해야 합니다.");
			lower_category = false;
		}
	}
	
	if(lower_category){
		$('.add_btn').attr("disabled",true);
		
		var maxlen = 4;
		if(category_code == 3){
			maxlen = 3;
		}
		
//		var cat_code = $('<div class="control-group info">'
//				+'<div class="controls">'
//				+'<input type="text" id="'+category_id+'_code" maxlength="'+maxlen+'" onkeyup="duplication_category()"><br>'
//				+'<span class="help-inline">사용가능한 카테고리입니다.</span>'
//				+'</div>'
//				+'</div>');
		var cat_code = $('<input type="text" id="'+category_id+'_code" maxlength="'+maxlen+'" onkeyup="duplication_category()"><br>');
		var cat_detail = $('<textarea id="'+category_id+'_detail">');
		
		$("#"+category_id+"_sel").hide();
		
		$("#"+category_id+"_topTd").append(cat_code);
		$("#"+category_id+"_topTd").append(cat_detail);	
	
		var cat_addBtn = $('<input type="button" id="'+category_id+'_saveBtn" class="btn btn-primary save_btn" value="추가" onclick="save_category()">');
		var cat_cancelBtn = $('<input type="button" id="'+category_id+'_cancelBtn" value="취소" onclick="cancel_category()">');
		
		$("#"+category_id+"_buttomTd > input").hide();	
		
		$("#"+category_id+"_buttomTd").append(cat_addBtn);
		$("#"+category_id+"_buttomTd").append(cat_cancelBtn);	
	}
}

function cancel_category() {
	var category_id = $('#category_id').val();
	var category_cnt = category_id.replace("category", "")*1;
	
	$("#"+category_id+"_code").remove();
	$("#"+category_id+"_detail").remove();	
	$("#"+category_id+"_topTd > br").remove();
	$("#"+category_id+"_saveBtn").remove();	
	$("#"+category_id+"_cancelBtn").remove();

	$("#"+category_id+"_topTd > select").show();
	$("#"+category_id+"_buttomTd > input").show();	

	$('.add_btn').attr("disabled",false);

	$('#category_id').val("");
}

function save_category(){
	var category_id = $('#category_id').val();
	
	var param_code = $("#"+category_id+"_code").val();
	var param_detail = $("#"+category_id+"_detail").val();
	var param_depth = category_id.replace("category",""); 
	var param_upperid = '0';
	if(param_depth > 1){
		param_upperid = $("#category"+(param_depth-1)+"_sel").val();
	}
	
	// validation check here.
	var valid_result = valid_category(param_code, param_detail, param_depth);
	var ajax_result = false;
	
	if(valid_result){
		var category_id = $('#category_id').val();
    	
		$.ajax({
	        method:"POST",
	        url:"ajax_put_category",
	        data:{
	        	cde_code : param_code, 
	        	cde_detail : param_detail, 
	        	cde_depth : param_depth, 
	        	cde_upperid : param_upperid
	        },
	        async:false,
	        success:function(response){
	    		reflesh(category_id);
	        	var result = response;
	        	for(var i = 0; i < result.length; i++){
	        		var opt = $('<option value="'+result[i].cde_id+'">'+result[i].cde_code+'</option>');
	        		if(result[i].cde_code == $("#"+category_id+"_code").val()){
	        			opt = $('<option value="'+result[i].cde_id+'" selected="true">'+result[i].cde_code+'</option>');
	        		}
	        		$("#"+category_id+"_sel").append(opt);
	        	}
	        },
	        error:function(request,status,error){
	            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	    });
		
		cancel_category();
	}
}


function duplication_category(){
	var category_id =  $('#category_id').val();
	var param_code =$("#"+category_id+"_code").val();
	var param_depth = category_id.replace("category",""); 
	var param_upperid = 0;
	if(param_depth != '1'){
		param_upperid = $("#category"+(param_depth*1-1)+"_sel").val();
	}

	var result = 0;
	
	if(param_code == 'null' && param_depth == '2'){
		response = 1;
	}else if(param_code == 'nul' && param_depth == '3'){
		response = 1;
	}
	
	if(result == 0){
		// 상위 카테고리까지 검색조건에 포함되어야 함
		$.ajax({
	        method:"POST",
	        url:"ajax_get_category_duplication",
	        data:{
	        	cde_code : param_code,  
	        	cde_depth : param_depth,  
	        	cde_upperid : param_upperid
	        },
	        async:false,
	        success:function(response){
	        	result = response;
	        },
	        error:function(request,status,error){
	            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	    });
	}

	if(result > 0){
		$(".save_btn").attr("disabled", true);
		alert("카테고리 키워드가 중복됩니다.");
	}else{
		$(".save_btn").attr("disabled", false);
	}
}

function change_category(category_id){
	var param_depth = category_id.replace("category","")*1;
		
	for(var i = param_depth+1; i <= 3; i++){
		var target = "category"+i; 
		reflesh(target);
	}
	
	var upperid = 0;
	if(param_depth > 0){
		upperid = $("#"+category_id+"_sel").val();
	}
	
	$.ajax({
        method:"POST",
        url:"ajax_get_category",
        data:{
        	cde_upperid : upperid
        },
        async:false,
        success:function(response){        	
        	var result = response;
        	for(var i = 0; i < result.length; i++){
        		var opt = $('<option value="'+result[i].cde_id+'">'+result[i].cde_code+'</option>');
        		$("#category"+(param_depth+1)+"_sel").append(opt);
        	}
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

}

function valid_category(code, detail, depth){
	var action = true;
	
	var digit = 4;
	var regexr = /[a-z0-9]{4}$/;
	if(depth == 3){
		digit = 3;
		regexr = /[a-z0-9]{3}$/;
	}

	if(!regexr.test(code)){
		alert('카테고리 코드는 '+digit+'자리 영문 소문자와 숫자만 사용이 가능합니다.');
		action = false;
	}else if(detail == ""){
		alert('카테고리 설명는 반드시 작성해야 합니다.');
		action = false;
	}
	
	return action;
}

function reflesh(category_id){
    $("#"+category_id+"_sel").empty(); 
    $("#"+category_id+"_sel").append('<option id="deselected" value="deselected">선택</option>');
}