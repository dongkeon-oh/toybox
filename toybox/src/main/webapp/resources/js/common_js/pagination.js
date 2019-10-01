function set_pagination(content_size, content_total, page_no, keyword, keytype) {
	//content_size : 페이지당 컨텐츠 갯수
	//content_total : 건텐츠 총 갯수
	//page_no : 현재 페이지 번호
	
	// initialization
	$("#pagination_area").html("");
	var this_page = "";
	var append = '<li class="page-item disable">'
				+'<a class="page-link" href="#" aria-label="Previous">'
				+'<span aria-hidden="true">&laquo;</span>'
				+'</a>'
				+'</li>'
	
	//페이지에 보여줄 페이지 수. 10개 초과시 다음 버튼으로 이동
	var pagination_size = Math.ceil(content_total / content_size);
	if (pagination_size > 10) {
		pagination_size = 10;
	}
	
	var arrow_location = (page_no/10) + 1;
	if(arrow_location != 1){

	}

	
	for (var i = 1; pagination_size >= i; i++) {
		if (i == page_no) {
			this_page = " active";
		} else {
			this_page = "";
		}

		append = append
				+ '<li class="page-item'
				+ this_page
				+ '"><a class="page-link" href="#" onclick="list_common_group(\''
				+ i + '\', \'' + content_size + '\', \'' + keyword + '\', \''
				+ keytype + '\');">' + i + '</a></li>';
	}
	$("#pagination_area").html(append);
}