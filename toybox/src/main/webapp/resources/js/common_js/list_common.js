$(function() {
	list_common(1);
});

function list_common(page_no){
	list_common(page_no, $("#idx").val());
}

function list_common(page_no, order_type){
	var target = $("#target").val();
    var	keyword = $("#keyword").val();
    var	end_idx = $("#idx").val()*1*page_no;
    var	start_idx = end_idx-(page_no*1)+1;
    
    if(order_type == undefined || order_type == ""){
    	order_type = "COM_ID";
    }
    
    var param = {
    	"target" : target,
    	"keyword" : keyword,
    	"start_idx" : start_idx,
    	"end_idx" : end_idx,
    	"order_type" : order_type
    }
    //var paramJson = JSON.stringify(param);
	
	$.ajax({
        method:"POST",
        url:"ajax_list_common",
//        datatype:"json",
        data:param,
//        contentType:"application/json;charset=UTF-8",
        async:false,
        success:function(response){
        	var result = response;
        	var opt = "";
        	var cnt = 0;
        	
        	$("table").html("");
        	$.each(result, function(index, item){
        		cnt++;
        		opt = opt + "<tr><td>"+(index+1)+"</td><td>"+item.com_id+"</td><td>"+item.com_name+"</td><td>"+item.com_category1+"</td><td>"+item.com_category2+"</td><td>"+item.com_category3+"</td></tr>";
        	});
        	$("table").append(opt);
        	
        	set_pagination(cnt, order_type, page_no)
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function set_pagination(cnt, order_type, page_no){
	var total_page = 0;
	if(cnt%order_type > 0){
		total_page = 1;
	}
	total_page = cnt/order_type + total_page;

	var page = "";
	var append = "";
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
