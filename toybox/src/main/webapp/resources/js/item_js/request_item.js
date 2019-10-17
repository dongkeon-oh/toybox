$.datepicker.setDefaults({
    dateFormat: 'yy/mm/dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});

//아이템 조회에 사용
var start_idx = 1;
var end_idx = 20;
var end_of_search = false;

$(function() {
	$(".datepicker").datepicker();	
	$(".datepicker").datepicker( "setDate", "2019/09/04");
	search_date = $(".datepicker").val();
	$(".datepicker").on("propertychange change keyup paste input", function() {
		if(this.className.indexOf("search_date") > -1){
			search_date = this.value;
		}
	});
	request_item('search_event');
});

function request_item(event_type) {
	var end_of_msg = "더이상 "
	if(event_type == 'search_event'){
		start_idx = 1;
		end_idx = 20
		end_of_msg = "";
	}
	
	$.ajax({
		method : "POST",
		url : "ajax_request_item",
		data : {
			"keyword" : $("#keyword").val(),
			"start_idx" : start_idx,
			"end_idx" : end_idx,
			"search_date" : $(".datepicker").val()
		},
		async : false,
		success : function(response) {
			var result = response;
			var opt = ""

			start_idx = start_idx + end_idx;
			end_idx = end_idx + 10;
			
			$.each(
				result,
				function(index, item) {
					var request_class = "bg-danger";
					var request_state = "대여 거절"
					if(item.cdt_condition == "rent_requested"){
						request_class = "bg-secondary";
						request_state = "대여신청";
					}else if(item.cdt_condition == "rented"){
						request_class = "";
						request_state = "대여중";
					}else if(item.cdt_condition == "returned"){
						request_class = "bg-success";
						request_state = "반납";
					}
					
					opt = opt + "<tr class='" + request_class + "'>";
					opt = opt + "	<td>" + item.itm_name + "</td>";
					opt = opt + "	<td>" + request_state + "</td>";
					opt = opt + "	<td>" + item.cdt_fromdate + "<br>~ " + item.cdt_todate + "</td>";
					opt = opt + "</tr>";
				}
			);
			
			if(!result.length > 0){
				end_of_search = true;
				opt = "<tr><td colspan='3'>" + end_of_msg + "검색결과가 없습니다.</td></tr>";
			}else if(event_type == 'search_event' && result.length < (end_idx - start_idx)){
				
			}			
			if(event_type == 'search_event'){
				$("#cdt_tbody").html(opt);
			}else{
				$("#cdt_tbody").append(opt);
			}
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

function total_rent(item_list){
	// 대여기간 확인, 반납 위치 설정, 대여지 확인
	var rent_data = {
		"item_list":item_list
		, "rent_date":rent_date
		, "return_date":return_date
	}
	
    var $form = $('<form></form>');
    $form.attr('action', 'apply_item');
    $form.attr('method', 'post');
    $form.appendTo('body');
    
    var json_rent_data = $("<input type='hidden' value='" + JSON.stringify(rent_data) + "' name='rent_data'>");
    $form.append(json_rent_data);
    $form.submit();
}