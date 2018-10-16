$(function() {
	list_common(1, "");
});

function list_common(pageNo, orderType){
	var target = $("#target").val();
    var	keyword = $("#keyword").val();
    var	end_idx = $("#idx").val()*1*pageNo;
    var	start_idx = end_idx-pageNo+1;
    
    if(orderType == undefined || orderType == ""){
    	orderType = "COM_ID";
    }
    
    var param = {
    	"target" : target,
    	"keyword" : keyword,
    	"start_idx" : start_idx,
    	"end_idx" : end_idx,
    	"orderType" : orderType
    }
    //var paramJson = JSON.stringify(param);
	
	$.ajax({
        method:"POST",
        url:"ajax_list_common",
//        datatype:"json",
        data:{
        	"target" : target,
        	"keyword" : keyword
        },
//        contentType:"application/json;charset=UTF-8",
        async:false,
        success:function(response){
        	var result = response;
        	var opt = "";
        	$("table").html("");
        	$.each(result, function(index, item){
        		opt = opt + "<tr><td>"+(index+1)+"</td><td>"+item.com_id+"</td><td>"+item.com_name+"</td><td>"+item.com_category1+"</td><td>"+item.com_category2+"</td><td>"+item.com_category3+"</td></tr>";
        	});
        	$("table").append(opt);
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}
