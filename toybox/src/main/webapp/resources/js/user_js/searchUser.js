/**
 * 
 */
function view_user(userId) {
    var $form = $('<form></form>');
    $form.attr('action', 'view_user');
    $form.attr('method', 'post');
    $form.append('<input type="hidden" value="'+userId+'" name="userId">');
    $form.appendTo('body');
    
    $form.submit();
}
