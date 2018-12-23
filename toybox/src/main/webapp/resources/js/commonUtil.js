/**
 * 공통 기능 추가중
 */

/**
 * @function generate_list
 * @describe 게시물 목록을 세팅하고 페이징 영역을 세팅한다.
 * 
 * @param destination_page		//	이동하려는 페이지 번호
 * @param page_size				//	한 페이지에 출력할 리스트 크기
 * @param total_size			//	게시물의 총 크기
 * @param pagination_size		//	페이징 영역에 보여질 페이지 이동 버튼의 크기
 * @param keyword				//	검색어 [map]
 * @param url					//	ajax url
 * @param scroll_use			//	스크롤 사용 여부
 * @param column_list			//	리턴받은 목록중 사용할 column 선택 [list<map>]	{컬럼명, 표현법}
 * @param button_list			//	버튼 세팅 [list<map>]	{버튼명, 타입, 기능}
 * @param list_pagination_area	//	페이지를 세팅할 위치 [array]
 */
function generate_list(destination_page, page_size, total_size, keyword, url, scroll_use, column_list, button_list, list_pagination_area){
	var start_idx;
	var end_idx;
	
	if(destination_page == "F"){			// 첫 페이지 이동
		start_idx = 1;
		end_idx = 1 * page_size;
	}else if(destination_page == "L"){		// 마지막 페이지 이동
		
	}else{
		end_idx = destination_page * page_size;
		start_idx = end_idx - page_size + 1;
	}

	$.ajax({
		method : "POST",
		url : url,
		data : {
			"keyword" : keyword,
			"start_idx" : start_idx,
			"end_idx" : end_idx
		},
		async : false,
		success : function(response) {
			var result = response;
			var append_tag = "<tr>";
			var data_array = new Array();
			alert(list_pagination_area.list);
			$("#"+list_pagination_area.list).html("");
			
			// 필요한 데이터만 배열에 추가한다.
			result.forEach(function (item, index) {
				$.each(item, function (key, value) {
					if(column_list[key] != undefined){
						column_list[key]["value"] = value;
					}
				});
				data_array.push(column_list);
			});
			
			// 필요한 데이터를 이용해 페이지 영역을 세팅한다.
			data_array.forEach(function (item, index) {
				$.each(item, function (key, value) {
					// 타입별 처리
					if(key == "type" && (item["type"] == "number" || item["type"] == "date")){
						// 숫자 형태 확인
						if(!$.isNumeric(item["value"])){
							item["value"] = "Not Number";
						}else if(item["type"] == "number"){
							// 작업예정
						}else if(item["type"] == "date"){
							// 작업예정
						}
					}
				});
				append_tag = append_tag + "<td align='"+ item.align +"'>"
				append_tag = append_tag + item.value
				append_tag = append_tag + "</td>"
			});
			append_tag = "</tr>";
			
			$("#"+list_pagination_area.list).html(append_tag);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}


