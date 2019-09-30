$(function() {
	$("form").submit(function(){
		var arr = $(this).serializeArray();
		var result = [];

		var obj = new Object();
		$.each(arr, function(index){
			if(this.name.indexOf("cdt_location") > -1){
				obj.cdt_location = this.value;
			}else if(this.name.indexOf("cdt_return") > -1){
				obj.cdt_return = this.value;
			}else if(this.name.indexOf("cdt_item") > -1){
				obj.cdt_item = this.value;
			} 
			
			if(obj.cdt_location != undefined
				&& obj.cdt_return != undefined
				&& obj.cdt_item != undefined){
				result.push(obj);
				
				obj = new Object();
			}
		})
		
		$("input[name='result']").val('{"result":'+JSON.stringify(result)+'}');		
	})
});