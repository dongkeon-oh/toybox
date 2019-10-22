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
	$(".datepicker").datepicker( "setDate", new Date());
	search_date = $(".datepicker").val();
	$(".datepicker").on("propertychange change keyup paste input", function() {
		if(this.className.indexOf("search_date") > -1){
			search_date = this.value;
		}
	});
	request_item('search_event');
	
	$(document).on("click",".btn_rent_cancel", function(){
		var i_id = $(this).attr("id");
		request_cancel(i_id);
	});
});

function request_item(event_type) {
	var end_of_msg = "";
	if(event_type == 'search_event'){
		start_idx = 1;
		end_idx = 20
	}else{
		start_idx = start_idx + 10;
		end_idx = end_idx + 10;
		end_of_msg = "더이상 ";
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
			var opt = "";
			
			$.each(
				result,
				function(index, item) {
					var request_class = "";
					var request_state = "";
					if(item.cdt_condition == "rented"){
						request_state = "대여중";
					}else if(item.cdt_condition == "returned"){
						request_state = "반납";
					}else if(item.cdt_condition == "request_cancel"){
						request_state = "대여 취소";
					}else if(item.cdt_condition == "rent_requested"){
						request_class = "bg-success";
						request_state = "대여 신청";
					}else if(item.cdt_condition == "return_denied"){
						request_class = "bg-danger";
						request_state = "대여 거절";
					}
					
					opt = opt + "<tr class='" + request_class + "'>";
					opt = opt + "	<td>" + item.itm_name + "</td>";
					opt = opt + "	<td>" + request_state + "</td>";
					opt = opt + "	<td>" + item.cdt_fromdate + "<br>~ " + item.cdt_todate + "</td>";
					opt = opt + "	<td>"
					if(item.cdt_condition == "rent_requested"){
						opt = opt+ "<button type='button' class='btn btn-secondary btn-sm btn_rent_cancel' id='" + item.itm_id + "''>대여 취소</button>"
					}
					opt = opt + "	</td>";
					opt = opt + "</tr>";
				}
			);
			
			if(!result.length > 0){
				end_of_search = true;
				opt = "<tr><td colspan='4'>" + end_of_msg + "검색결과가 없습니다.</td></tr>";
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

function request_cancel(item_id) {
	if(confirm("대여를 취소하시겠습니까?")){
		$.ajax({
			method : "POST",
			url : "ajax_request_cancel",
			data : {
				"item_id" : item_id
			},
			async : false,
			success : function(response) {
				if(response > 0){
					alert("대여를 취소하였습니다.");
					request_item('search_event');
				}else{
					alert("대여 취소를 실패했습니다.");
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});	
	}
}
