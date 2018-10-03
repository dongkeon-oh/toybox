/**
 * 
 */
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
		var maxlen = 4;
		if(category_code == 3){
			maxlen = 3;
		}
		var cat_code = $('<input type="text" id="'+category_id+'_code" maxlength="'+maxlen+'"><br>');
		var cat_detail = $('<textarea id="'+category_id+'_detail">');
		
		$("#"+category_id+"_sel").hide();
		
		$("#"+category_id+"_topTd").append(cat_code);
		$("#"+category_id+"_topTd").append(cat_detail);	
	
		var cat_addBtn = $('<input type="button" id="'+category_id+'_saveBtn" value="추가" onclick="save_category()">');
		var cat_cancelBtn = $('<input type="button" id="'+category_id+'_cancelBtn" value="취소" onclick="cancel_category()">');
		
		$("#"+category_id+"_buttomTd > input").hide();	
		
		$("#"+category_id+"_buttomTd").append(cat_addBtn);
		$("#"+category_id+"_buttomTd").append(cat_cancelBtn);	
	}
}

function cancel_category() {
	var category_id = $('#category_id').val();
	
	$("#"+category_id+"_code").remove();
	$("#"+category_id+"_detail").remove();	
	$("#"+category_id+"_topTd > br").remove();
	$("#"+category_id+"_saveBtn").remove();	
	$("#"+category_id+"_cancelBtn").remove();

	$("#"+category_id+"_topTd > select").show();
	$("#"+category_id+"_buttomTd > input").show();	

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
	
	if(valid_result[0]){
		var category_id = $('#category_id').val();
		
    	reflesh(category_id);   
    	
		$.ajax({
	        method:"POST",
	        url:"add_common_category",
	        data:{
	        	cde_code : param_code, 
	        	cde_detail : param_detail, 
	        	cde_depth : param_depth, 
	        	cde_upperid : param_upperid
	        },
	        async:false,
	        success:function(response){
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

function change_category(category_id){
	var param_depth = category_id.replace("category","")*1;
	
	reflesh("category"+(param_depth+2));
	if(param_depth == 1){
		reflesh("category"+(param_depth+1));
	}
	
	upperid = $("#"+category_id+"_sel").val();
	
	$.ajax({
        method:"POST",
        url:"get_common_category",
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
	if(param_depth == 3){
		digit = 3;
		regexr = /[a-z0-9]{3}$/;
	}

	if(!regexr.test(param_code)){
		alert('카테고리 코드는 '+digit+'자리 영문 소문자와 숫자만 사용이 가능합니다.');
		action = false;
	}else if(param_detail == ""){
		alert('카테고리 설명는 반드시 작성해야 합니다.');
		action = false;
	}
	
	var validResult = [action, param_code, param_detail, param_depth, param_upperid];
	return validResult;
}

function reflesh(category_id){
    $("#"+category_id+"_sel").empty(); 
    $("#"+category_id+"_sel").append('<option id="deselected" value="deselected">선택</option>');
}
