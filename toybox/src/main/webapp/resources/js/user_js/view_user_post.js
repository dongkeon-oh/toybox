/**
 * 
 */
function active_user(userId, usrAcive) {
	var activeCondition = "Y";
	if(activeCondition == usrAcive){
		activeCondition = "N";
	}
	
    var $form = $('<form></form>');
    $form.attr('action', 'active_user');
    $form.attr('method', 'post');
    $form.append('<input type="hidden" value="'+userId+'" name="usr_id">');
    $form.append('<input type="hidden" value="'+activeCondition+'" name="usr_active">');
    $form.appendTo('body');
    
    $form.submit();
}

function delete_user(userId) {	
    var $form = $('<form></form>');
    $form.attr('action', 'delete_user');
    $form.attr('method', 'post');
    $form.append('<input type="hidden" value="'+userId+'" name="usr_id">');
    $form.appendTo('body');
    
    $form.submit();
}
