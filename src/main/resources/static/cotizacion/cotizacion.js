

$(function($) {
	
	$('#div-retrabajo').hide();
	$('#div-tiempo').hide();
	$('#div-motivo').hide();
	$('#detalle-grafica').hide();
	
	$('#btn-grafica').click(function() {
		
		$('#div-retrabajo').hide();
		$('#div-tiempo').hide();
		$('#div-motivo').hide();
		$('#div-grafica').show();
		 $('#grafica').show();
		 $('#detalle-grafica').hide();
	});//btn-grafica
	
	$('#btn-retrabajos').click(function() {
		$('#div-grafica').hide();
		$('#div-tiempo').hide();
		$('#div-motivo').hide();
		$('#div-retrabajo').show();
	});//btn-retrabajo
	
$('#btn-tiempo').click(function() {
		
		$('#div-grafica').hide();
		$('#div-retrabajo').hide();
		$('#div-motivo').hide();
		$('#div-tiempo').show();
	});//btn-tiempo

$('#btn-motivo').click(function() {
	
	$('#div-grafica').hide();
	$('#div-retrabajo').hide();
	$('#div-tiempo').hide();
	$('#div-motivo').show();
});//btn-motivo
	

$('.sector1').submit(function(e) {
	 e.preventDefault();
	 
	 $('#grafica').hide();
	 $('#detalle-grafica').show();
	
	 
	 var sector = $.post("/indicador_cotizacion_sec_detalles",$(this).serialize() );
	 
	 sector.done(function( data ) {
		
		    $('#detalle-grafica').highcharts({
		    	chart : {type : 'pie',options3d : {enabled : true,alpha : 45,beta : 0}},
				title : {text : 'Grafica de Folios por Sector'},
				tooltip : {pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'},
				plotOptions : {pie : {allowPointSelect : true,cursor : 'pointer',depth : 35,dataLabels : {enabled : true,format : '{point.name}'
												}}},
										series : [ {
											type : 'pie',
											name : 'Sector',
											data : data

										} ]

									});/*highcharts*/
		  });
});

$('.agente1').submit(function(e) {
	 e.preventDefault();
	 
	 $('#grafica').hide();
	 $('#detalle-grafica').show();
	
	 
	 var sector = $.post("/indicador_cotizacion_agente",$(this).serialize() );
	 
	 sector.done(function( data ) {
		
		    $('#detalle-grafica').highcharts({
		    	chart : {type : 'pie',options3d : {enabled : true,alpha : 45,beta : 0}},
				title : {text : 'Grafica de Folios por Agente'},
				tooltip : {pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'},
				plotOptions : {pie : {allowPointSelect : true,cursor : 'pointer',depth : 35,dataLabels : {enabled : true,format : '{point.name}'
												}}},
										series : [ {
											type : 'pie',
											name : 'Agente',
											data : data

										} ]

									});/*highcharts*/
		  });
});

$('.retrabajosector').submit(function(e) {
	 e.preventDefault();
	 
	 $('#grafica').hide();
	 $('#retrabajo_grafica').show();
	
	 
	 var sector = $.post("/indicador_cotizacion_retrabajo_sector",$(this).serialize() );
	 
	 sector.done(function( data ) {
		
		    $('#retrabajo_grafica').highcharts({
		    	chart : {type : 'pie',options3d : {enabled : true,alpha : 45,beta : 0}},
				title : {text : 'Grafica de Folios por Retrabajo Sector'},
				tooltip : {pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'},
				plotOptions : {pie : {allowPointSelect : true,cursor : 'pointer',depth : 35,dataLabels : {enabled : true,format : '{point.name}'
												}}},
										series : [ {
											type : 'pie',
											name : 'Agente',
											data : data

										} ]

									});/*highcharts*/
		  });
});

$('.retrabajoagente').submit(function(e) {
	 e.preventDefault();
	 
	 $('#grafica').hide();
	 $('#retrabajo_grafica').show();
	
	 
	 var sector = $.post("/indicador_cotizacion_retrabajo_agente",$(this).serialize() );
	 
	 sector.done(function( data ) {
		
		    $('#retrabajo_grafica').highcharts({
		    	chart : {type : 'pie',options3d : {enabled : true,alpha : 45,beta : 0}},
				title : {text : 'Grafica de Folios por Retrabajo Agentes'},
				tooltip : {pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'},
				plotOptions : {pie : {allowPointSelect : true,cursor : 'pointer',depth : 35,dataLabels : {enabled : true,format : '{point.name}'
												}}},
										series : [ {
											type : 'pie',
											name : 'Agente',
											data : data

										} ]

									});/*highcharts*/
		  });
});
});//fin Function