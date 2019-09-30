//datepicker 기본 세팅
$.datepicker.regional['ko'] = {
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
};

//아이템 조회에 사용
var start_idx = 1;
var end_idx = 20;
var keyword = "";
var end_of_search = false;

//아이템 대여에 사용
var rent_date;
var return_date;

//아이템 대여할 목록이 담긴 배열
var rent_item = [];

// 선택 대여 버튼 활설화시 힐요함.
var rent_selected = 0;

$(function() {
    $.datepicker.setDefaults($.datepicker.regional['ko']);
	
    $('.rent_date').datepicker();
    $(".rent_date").datepicker("setDate", new Date());
    $('.rent_date').datepicker("option", "minDate", 0);
    $('.rent_date').datepicker("option", "maxDate", $(".return_date").val());
    $('.rent_date').datepicker("option", "onClose", function ( selectedDate ) {
        $(".return_date").datepicker( "option", "minDate", selectedDate );
    });
 
    $('.return_date').datepicker();
    $(".return_date").datepicker("setDate", new Date());
    $('.return_date').datepicker("option", "minDate", $(".rent_date").val());
    $('.return_date').datepicker("option", "onClose", function ( selectedDate ) {
        $("#sdate").datepicker( "option", "maxDate", selectedDate );
    });
  
	rent_date = $(".datepicker").val();
	return_date = $(".datepicker").val();
	
	$(".datepicker").on("propertychange change keyup paste input", function() {
		if(this.className.indexOf("rent_date") > -1){
			rent_date = this.value;
		}else if(this.className.indexOf("return_date") > -1){
			return_date = this.value;
		}
	});
	
	// 스크롤
	$(document).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
	    var maxHeight = $(document).height();
	    var currentScroll = $(window).scrollTop() + $(window).height();

	    if (maxHeight <= currentScroll + 100 && !end_of_search) {
	    	list_item('scroll_event');
	    } 
	});
	list_item('search_event');	//테스트중	
});

function list_item(event_type) {
	var end_of_msg = "더이상 "
	if(event_type == 'search_event'){
		start_idx = 1;
		end_idx = 20;
		end_of_msg = "";
		end_of_search = false;
	}
	
	$.ajax({
		method : "POST",
		url : "ajax_list_item",
		data : {
			"keyword" : $("#keyword").val(),
			"start_idx" : start_idx,
			"end_idx" : end_idx,
			"rent_date" : rent_date,
			"return_date" : return_date
		},
		async : false,
		success : function(response) {
			var result = response;
			var opt = ""

			$.each(
				result,
				function(index, item) {
					var rent_class = "bg-danger";
					var rent_state = "대여불가";
					var button_state = "";
					var quick_rent = "";
					if(item.cdt_condition == "rentable"){
						rent_class = "";
						rent_state = "대여가능";
						button_state = "<button type='button' class='btn btn-outline-success btn-sm btn-unselect' value='" + item.itm_id + "' onclick='list_item_sel(this)'>선택</button>";
						quick_rent = "<button type='button' class='btn btn-success btn-sm'  onclick='quick_rent(" + item.itm_id + ")'>대여</button>";
					}
					
					opt = opt + "<tr class=' " + rent_class + "'>";
					opt = opt + "	<td>" + button_state + "</td>";
					opt = opt + "	<td><div class='marquee'><p>" + item.itm_name + "</p></div></td>";
					opt = opt + "	<td>" + rent_state + "</td>";
					opt = opt + "	<td>" + quick_rent + "</td>";
					opt = opt + "</tr>";
				}
			);
			
			
			if(!result.length > 0 || (end_idx - start_idx + 1) != result.length){
				end_of_search = true;
				opt = opt + "<tr><td colspan='4'>" + end_of_msg + "검색결과가 없습니다.</td></tr>";
			}
			if(event_type == 'search_event'){
				$("#item_tbody").html(opt);
			}else{
				$("#item_tbody").append(opt);
			}
			
			// 다음 검색 준비 인덱스
			start_idx = start_idx + end_idx;
			end_idx = end_idx + 10;
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
}

//아이템 선택, 취소
function list_item_sel(target_this){
	var btn_state = target_this.className.indexOf('btn-unselect');
	if(btn_state > -1){	//선택 버튼 클릭됨
		rent_item.push(target_this.value);

		$(target_this).removeClass('btn-unselect btn-outline-success').addClass('btn-select btn-success');
		$(target_this).html('취소');			
		
		rent_selected = rent_selected + 1;
	}else if(btn_state == -1){	//취소 버튼 클릭됨
		var del_index = rent_item.indexOf(target_this.value);	
		if (del_index > -1) rent_item.splice(del_index, 1);

		$(target_this).removeClass('btn-select btn-success').addClass('btn-unselect btn-outline-success');
		$(target_this).html('선택');	
		
		rent_selected = rent_selected - 1;	
	}
	
	if(rent_selected == 0){
		$("#rent_selected").attr("disabled",true);
	}else{
		$("#rent_selected").attr("disabled",false);		
	}
}

function quick_rent(quick_item){
	var item = [quick_item];
	total_rent(item);
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