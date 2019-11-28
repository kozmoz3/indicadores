$(function($) {
	$("#graficadetalle").hide();
	$('#btn-grafica').click(function() {
		$("#grafica_principal").show();
		 $("#graficadetalle").hide();
	});
	
	$('.detalleNFA').submit(function(e) {
		 e.preventDefault();
		 $("#grafica_principal").hide();
		 $("#graficadetalle").show();
		 var chartStatus = $.post("/indicador_nfa_store",$(this).serialize() );
		 
		 chartStatus.done(function( ChartDatos ) {
			 console.log("detalle ",ChartDatos);
			 
			 var ArrayCategoria = [];
				var ArrayDatos = []; 
				for (var clave in ChartDatos){
					ArrayCategoria.push(ChartDatos[clave].name)
					ArrayDatos.push(ChartDatos[clave].data)
					console.log("La clave es " + clave+ " y el valor es " + ChartDatos[clave].name)
				}
			 
			 
			 $('#grafica_detalle').highcharts({
			    	chart : {type : 'bar',options3d : {enabled : true,alpha : 45,beta : 0}},
					title : {text : 'Detalle de Estatus'},
					xAxis: {
			            categories: ArrayCategoria
			        },
			        yAxis: {
			            title: {
			                text: 'Sectores'
			            }
			        },
					tooltip : {pointFormat : '{series.name}:'/* <b>{point.percentage:.1f}%</b>*/},
					/*plotOptions : {pie : {allowPointSelect : true,
						        cursor : 'pointer',
						         depth : 35,
						     dataLabels : {enabled : true,format : '{point.name}'
													}}},*/
											series : [ {
												
												name : '',
												data : ArrayDatos

											} ]

										});/*highcharts*/
			 
		 });//CharStatus
	});//formulario
	
});//function