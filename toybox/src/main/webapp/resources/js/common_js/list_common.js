$(function() {
	list_common_group(1, 10, "");
});

function mod_common_group(){	
	var cgr_group = $("#grpGroup").val();
    var	cgr_group_name = $("#grpGroupName").val();
    var	cgr_note = $("#grpNote").val();

    var valid = valid_common_group(cgr_group, cgr_group_name, cgr_note);
    
    if(!valid){
    	return;
    }
    
    var msg = "생성";
    var url = "ajax_put_common_group";
//    if(actionType == "MODIFY"){
//    	msg = "수정";
//        url = "ajax_modify_common_group";
//    }
    
	$.ajax({
        method:"POST",
        url:url,
        data:{
        	"cgr_group" : cgr_group,
        	"cgr_group_name" : cgr_group_name,
        	"cgr_note" : cgr_note,
        },
        async:false,
        success:function(response){
        	var result = response;
        	if(result > 0){
        		alert(cgr_group+"그룹을 "+msg+"하였습니다.");
        		clear_common_group();
        	}else{
        		alert(cgr_group+"그룹 "+msg+"을 실패하였습니다.");
        	}
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function valid_common_group(cgr_group, cgr_group_name, cgr_note){
	var validation = true;
	var regexr = /[a-z0-9_]{8,16}$/;
	
	if(!regexr.test(cgr_group)){
		alert('공통코드 그룹은 8자리 이상 16자리 이하의 영문 소문자와 숫자만 사용이 가능합니다.');
		validation = false;
		return validation;
	}

	if(cgr_group_name.length < 1){
		alert('공통코드 그룹명을 입력하시기 바랍니다.');	//25자까지
		validation = false;
		return validation;
	}

	var note_byte = 0;    
    for(var idx=0; idx < cgr_note.length; idx++) {
        var note_char = escape(cgr_note.charAt(idx));
         
        if( note_char.length==1 ) note_byte++;
        else if( note_char.indexOf("%u")!=-1 ) note_byte += 2;
        else if( note_char.indexOf("%")!=-1 ) note_byte += note_char.length/3;
    }     
    
	if(note_byte > 1000){		
		alert('공통코드 설명은 1000바이트 미만으로 가능합니다.');
		validation = false;
		return validation;
	}
	
	if(note_byte == 0){		
		alert('공통코드 설명을 입력하시기 바랍니다.');
		validation = false;
		return validation;
	}
	
	return validation;
}

function clear_common_group(){
	$("#grpGroup").val("");
	$("#grpGroupName").val("");
	$("#grpNote").val("");
	$("#grpGroup").attr("readonly", false);
}

function modify_common_group(idx){
	$("#grpGroup").val($("#trGrp"+idx).text());
	$("#grpGroupName").val($("#trGrpNm"+idx).text());
	$("#grpNote").val($("#trNote"+idx).text());
	$("#grpGroup").attr("readonly", true);
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
        		var note = item.cgr_note;
        		if(note.length > 30){
        			note = "<marquee behavior=scroll>"+note+"</marquee>";
        		}
        		
        		if(index%2==0){
                	var table_row_type = "table-secondary";
        		}else{
        			var table_row_type = "";
        		}
        		opt = opt + "<tr class='"+table_row_type+"'>";
        		opt = opt + "	<td>"+(start_idx+index)+"</td>";
        		opt = opt + "	<td id='trGrp"+(start_idx+index)+"'>"+item.cgr_group+"</td>";
        		opt = opt + "	<td id='trGrpNm"+(start_idx+index)+"'>"+item.cgr_group_name+"</td>";
        		opt = opt + "	<td id='trNote"+(start_idx+index)+"'>"+note+"</td>";
        		opt = opt + "	<td>";
        		opt = opt + "		<button type='button' class='btn btn-primary modGrpBtn'  data-toggle='modal' data-target='#grpModModal' onClick='modify_common_group("+(start_idx+index)+")' >수정</button>";
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
