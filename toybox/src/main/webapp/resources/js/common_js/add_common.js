/**
 * 
 */
function add_category(category_id) {
	$('#category_id').val(category_id);
	
	var cat_code = $('<input type="text" id="'+category_id+'_code"><br>');
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
	// validation check.

	var category_id = $('#category_id').val();
	var param_code = $("#"+category_id+"_code").val();
	var param_detail = $("#"+category_id+"_detail").val();
	var param_depth = category_id.replace("category",""); 
	var param_upperid = '0';
	
	if(param_depth == 2){
		param_upperid = $("#"+category_id+"_sel").val();
	}else if(param_depth == 3){
		param_upperid = $("#"+category_id+"_sel").val();
	}
	
	$.ajax({
        method:"POST",
        url:"search_common_detail",
        data:{
        	cde_code : param_code, 
        	cde_detail : param_detail, 
        	cde_depth : param_depth , 
        	cde_upperid : param_upperid 
        },
        async:false,
        success:function(response){
        	var result = response;
            $("#"+category_id+"_sel").empty();            
        	for(var i = 0; i < result.length; i++){
        		var opt = $('<option value="'+result[i].cde_id+'">'+result[i].cde_code+'</option>');
        		$("#"+category_id+"_sel").append(opt);
        	}
        	$("#"+category_id+"_sel option:eq("+(result.length-1)+")").prop("selected", true);
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

	cancel_category();
}
