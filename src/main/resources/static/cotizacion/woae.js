

$(function($) {
	
	$('#div-rechazos').hide();
	$('#div-pendiente').hide();
	$('#div-sistema').hide();
	
$('#btn-dev').click(function() {
		
		$('#div-devoluciones').show();
		$('#div-rechazos').hide();
		$('#div-pendiente').hide();
		$('#div-sistema').hide();
		
	});
	
$('#btn-rechazos').click(function() {
		
	$('#div-devoluciones').hide();
	$('#div-rechazos').show();
	$('#div-pendiente').hide();
	$('#div-sistema').hide();
		
	});

$('#btn-pendiente').click(function() {
	
	$('#div-devoluciones').hide();
	$('#div-rechazos').hide();
	$('#div-pendiente').show();
	$('#div-sistema').hide();
	
})

$('#btn-sistema').click(function() {
	
	$('#div-devoluciones').hide();
	$('#div-rechazos').hide();
	$('#div-pendiente').hide();
	$('#div-sistema').show();
	
});


});