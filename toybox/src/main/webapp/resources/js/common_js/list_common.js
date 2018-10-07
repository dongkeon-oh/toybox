$(function() {
	list_common();
});

function list_common(){
	$.ajax({
        method:"POST",
        url:"ajax_list_common",
        data:{
//        	target : $("#target").val(),
//        	keyword : $("#keyword").val()
        	target : "1",
        	keyword : $("#keyword").val()
        },
        async:false,
        success:function(response){
        	var result = response;
        	var opt = "";
        	$.each(result, function(index, item){
        		opt = opt + "<tr><td>"+index+"</td><td>"+item.com_id+"</td><td>"+item.com_category1+"</td><td>"+item.com_category2+"</td><td>"+item.com_category3+"</td></tr>";
        	});
        	$("table").append(opt);
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

}