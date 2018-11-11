$(function() {
	list_common_group(1, 10, "");
});

function put_common_group(){
	var cgr_group = $("#grpGroup").val();
    var	cgr_group_name = $("#grpGroupName").val();
    var	cgr_note = $("#grpNote").val();
    
	$.ajax({
        method:"POST",
        url:"ajax_put_common_group",
        data:{
        	"cgr_group" : cgr_group,
        	"cgr_group_name" : cgr_group_name,
        	"cgr_note" : cgr_note,
        },
        async:false,
        success:function(response){
        	var result = response;
        	if(result > 0){
        		alert(cgr_group+"그룹을 생성하였습니다.");
        	}else{
        		alert(cgr_group+"그룹 생성을 실해하였습니다.");
        	}
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function valid_common_group(code, detail, depth){
	var action = true;
	
	var regexr = /[a-z0-9][-]$/;
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

function list_common_group(page_no, page_cnt, keyword){
    
	if(page_cnt == undefined || page_cnt == ""){
		page_cnt = "10";
    }
    if(keyword == undefined){
    	keyword = "";
    }
    
    var end_idx = page_cnt * page_no;
    var start_idx = end_idx - page_cnt + 1;
    
	$.ajax({
        method:"POST",
        url:"ajax_list_common_group",
        data:{
        	"keyword" : keyword,
        	"start_idx" : start_idx,
        	"end_idx" : end_idx
        },
        async:false,
        success:function(response){
        	var result = response;
        	var opt = "";
        	var table_row_type = "table-secondary";
        	
        	$("tbody").html("");
        	$.each(result, function(index, item){
        		if(index%2==0){
                	var table_row_type = "table-secondary";
        		}else{
        			var table_row_type = "";
        		}
        		opt = opt + "<tr class='"+table_row_type+"'>";
        		opt = opt + "	<td>"+(start_idx+index)+"</td>";
        		opt = opt + "	<td>"+item.cgr_group+"</td>";
        		opt = opt + "	<td>"+item.cgr_group_name+"</td>";
        		opt = opt + "	<td>"+item.cgr_note+"</td>";
        		opt = opt + "	<td>";
        		opt = opt + "		<button type='button' class='btn btn-primary' onClick='' >수정</button>";
        		opt = opt + "		<button type='button' class='btn btn-danger' onClick='' >삭제</button>";
        		opt = opt + "	</td>";
        		opt = opt + "</tr>";
        	});
        	$("tbody").html(opt);
        	
        	//set_pagination(cnt, index, page_no)
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function set_pagination(cnt, index, page_no){
	var total_page = 0;
	if(cnt%index > 0){
		total_page = 1;
	}
	total_page = cnt/index + total_page;

	var page = "";
	var append = "";
	$("#pagination_area").html("");
	for(var i = 1; total_page >= i; i++){
		if(i == page_no){
			append = '<li class="disabled"><span onclick="list_common('+i+')">'+i+'</span></li>';
		}else{
			append = '<li class="active"><span onclick="list_common('+i+')">'+i+'</span></li>';
		}
		page = page + append;
	}
	$("#pagination_area").append(page);
}
