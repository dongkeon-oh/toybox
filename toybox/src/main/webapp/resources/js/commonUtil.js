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
 * @param keytype				//	검색어 종류
 * @param keyword				//	검색어
 * @param url					//	ajax url
 * @param scroll_use			//	스크롤 사용 여부
 * @param column_list			//	리턴받은 목록중 사용할 column 선택 [list]
 * @param list_pagination_area	//	페이지를 세팅할 위치 [array]
 */
function generate_list(destination_page, page_size, total_size, keytype, keyword, url, scroll_use, column_list, list_pagination_area){
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
			"keytype" : keytype,
			"start_idx" : start_idx,
			"end_idx" : end_idx
		},
		async : false,
		success : function(response) {
			$("#"+list_pagination_area[0]).html("");
//			$.each(
//					result,
//					function(index, item) {
//						page_total = item.cnt;
//
//						var note = item.cgr_note;
//						if (note.length > 30) {
//							note = "<marquee behavior=scroll>"
//									+ note + "</marquee>";
//						}
//
//						var table_row_type = "";// =
//												// "table-secondary";
//						var use_yn = "사용";
//						if (item.cgr_useyn == 'N') {
//							use_yn = "삭제됨";
//							table_row_type = "table-danger";
//						}
//
//						opt = opt + "<tr class='"
//								+ table_row_type + "'>";
//						opt = opt + "	<td>"
//								+ (start_idx + index) + "</td>";
//						opt = opt + "	<td id='trGrp"
//								+ (start_idx + index) + "'>"
//								+ item.cgr_group + "</td>";
//						opt = opt + "	<td id='trGrpNm"
//								+ (start_idx + index) + "'>"
//								+ item.cgr_group_name + "</td>";
//						opt = opt + "	<td id='trNote"
//								+ (start_idx + index) + "'>"
//								+ note + "</td>";
//						opt = opt + "	<td>" + use_yn + "</td>";
//						opt = opt + "	<td>";
//						if (item.cgr_useyn != 'N') {
//							opt = opt
//									+ "		<button type='button' class='btn btn-primary btn-sm modGrpBtn'  data-toggle='modal' data-target='#grpModModal' onClick='modify_group("
//									+ (start_idx + index)
//									+ ")' >수정</button>";
//							opt = opt
//									+ "		<button type='button' class='btn btn-danger btn-sm' onClick='delete_group("
//									+ (start_idx + index)
//									+ ")'>삭제</button>";
//							opt = opt
//									+ "		<button type='button' class='btn btn-secondary btn-sm' data-toggle='modal' data-target='#codeModModal' onClick='list_common_code(\""
//									+ item.cgr_group
//									+ "\")'>코드추가</button>";
//						}
//						opt = opt + "	</td>";
//						opt = opt + "</tr>";
//					});
			$("#"+list_pagination_area[0]).html(opt);

			set_pagination(page_cnt, page_total, page_no, keyword,
					keytype);

		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}


