//var pagination_index = 1;
//
//$(function() {
//	$(".page-item").on("click",function(){
//		var class_set = $(this).attr("class");
//		var class_index = class_set.indexOf("page_");
//		var pagination_index = class_set.substring(class_index);
//		pagination_index = pagination_index.replace("page_");
//	});
//});


function set_pagination(content_size, content_total, page_no, keyword, keytype) {
	//content_size : 페이지당 컨텐츠 갯수
	//content_total : 건텐츠 총 갯수
	//page_no : 현재 페이지 번호
	
	// initialization
	$("#pagination_area").html("");
	var this_page = "";
	var append = "";	
	
	var pagination_total = Math.ceil(content_total / content_size);	//페이지에 보여줄 페이지 수. 10개 초과시 다음 버튼으로 이동
	var pagination_point = page_no - 1;								//보여줄 페이지 영역 탐색
	var arrow_left = ' disabled';
	var arrow_right = ' disabled';
	
	var pagination_no = 0;
		
	if(pagination_point >= 10){
		pagination_no = (Math.floor(pagination_point/10)*10);									//왼쪽 화살표 세팅
		arrow_left = 'page_'+pagination_no;
	}
	
	if(pagination_total-1 != 0){									//오른쪽 화살표 세팅
		var p_total = Math.floor((pagination_total-1)/10);
		var p_point = 0;
		if(pagination_point != 0){
			p_point = Math.floor(pagination_point/10);
		}
		if(p_total > p_point){
			arrow_right = 'page_'+(((p_point+1)*10)+1);
		}
	}
	
	append = '<li class="page-item ' + arrow_left + '">'
		+'<a class="page-link" href="#" aria-label="Previous">'
		+'<span aria-hidden="true">&laquo;</span>'
		+'</a>'
		+'</li>';
		
	
	for (var i = 1; i <= 10; i++) {
		if(pagination_no+i > pagination_total){
			break;
		}
		
		if(pagination_no+i == page_no) {
			this_page = " active";
		} else {
			this_page = "";
		}

		append = append
			+'<li class="page-item' + this_page + ' page_' + (pagination_no+i) + '">'
			+'<a class="page-link" href="#">' + (pagination_no+i) + '</a>'
			+'</li>';
	}
	
	append = append
		+'<li class="page-item ' + arrow_right + '">'
	    +'<a class="page-link" href="#" aria-label="Next">'
	    +'<span aria-hidden="true">&raquo;</span>'
	    +'</a>'
	    +'</li>';
	    
	$("#pagination_area").html(append);
}