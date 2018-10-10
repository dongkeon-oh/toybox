$(function() {
	list_common();
});

function list_common(){
	var target = $("#target").val();
    var	keyword = $("#keyword").val();
    var param = {
    	"target" : target,
    	"keyword" : keyword
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
        		opt = opt + "<tr><td>"+(index+1)+"</td><td>"+item.com_id+"</td><td>"+item.com_category1+"</td><td>"+item.com_category2+"</td><td>"+item.com_category3+"</td></tr>";
        	});
        	$("table").append(opt);
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}
