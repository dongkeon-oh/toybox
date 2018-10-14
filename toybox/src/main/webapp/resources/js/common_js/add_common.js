

function save_common(){
	var cat1 = $("#category1_sel").val();
	var cat2 = $("#category2_sel").val();
	var cat3 = $("#category3_sel").val();
	var name = $("#com_name").val();
	var useYn = "Y";
	
	valid_common(cat1, name);
	
	$.ajax({
        method:"POST",
        url:"ajax_put_common",
        data:{
        	com_category1 : cat1,
        	com_category2 : cat2,
        	com_category3 : cat3,
        	com_name : name
        },
        async:false,
        success:function(response){
        	var result = response;
        	if(result == 1){
            	alert("성공적으로 저장되었습니다.");	
        	}else if(result > 99){
        		alert("카테고리에 코드가 너무 많습니다. 새로운 카테고리 생성후 저장하시기 바랍니다.");	
        	}
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

}

function valid_common(category, name){
	var action = true;

	if(category == "deselected"){
		alert('카테고리1을 선택해주시기 바랍니다.');
		action = false;
	}else if(name == ""){
		alert('공통코드명을 입력해주시기 바랍니다.');
		action = false;
	}
	return action;
}