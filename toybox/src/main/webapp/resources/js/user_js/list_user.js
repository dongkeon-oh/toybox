$(function() {
	//generate_list(1, 10, "", "group");

	$(".page-link").on("click", function(){
		generate_list(1, 10, 11, null,  url, "", column_list, "", list_pagination_area);
	});
});

var url = "ajax_list_user";
var column_list = {
		usr_id:{type:"number", align:"center", value:""},
		usr_name:{type:"date", align:"right", value:""},
		usr_active:{type:"normal", align:"left", value:""}
};
var list_pagination_area = {list:"user_tbody", pagination:""}


function list_user(destination_page, page_size, keyword){
	var start_idx;
	var end_idx;
	
	if(destination_page == "F"){			// 첫 페이지 이동
		start_idx = 1;
		end_idx = 1 * page_size;
	}else if(destination_page == "L"){		// 마지막 페이지 이동
		
	}else if(destination_page == "P"){		// 이전 페이지 이동
		
	}else if(destination_page == "N"){		// 다음 페이지 이동
		
	}else{
		end_idx = destination_page * page_size;
		start_idx = end_idx - page_size + 1;
	}

	$.ajax({
		method : "POST",
		url : "ajax_list_user",
		data : {
			"keyword" : keyword,
			"start_idx" : start_idx,
			"end_idx" : end_idx
		},
		async : false,
		success : function(response) {
			result = response;
			generate_list_detail(result);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}
