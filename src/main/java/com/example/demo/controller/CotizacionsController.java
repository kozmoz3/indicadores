package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bussine.ChartCotizacion;
import com.example.demo.model.AreaTable;
import com.example.demo.model.ChartModel;
import com.example.demo.model.CotizacionModel;
import com.example.demo.model.DateModel;
import com.example.demo.model.DetalleCotizacionModel;
import com.example.demo.model.Retrabajo;
import com.example.demo.pdf.CotizacionPdf;

@Controller
public class CotizacionsController {
	
	  private static final Logger logger = LogManager.getLogger(CotizacionsController.class);

	@Autowired
	@Qualifier("chartCotizacionImpl")
	private ChartCotizacion cotizacion;
	
	@Autowired
	private CotizacionPdf pdfReport;

	@GetMapping("/indicador_cotizacion")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/cotizacion/index";
	}

	@PostMapping("/indicador_cotizacion_show")
	//public String show(Model model, @ModelAttribute("datesmodel") DateModel datemodel) {
	public String show(Model model,@Valid DateModel datemodel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "redirect:/indicador_cotizacion";
        }
		String dateStart = datemodel.getDateStart();
		String dateFinish = datemodel.getDateFinish();
		logger.info("show param = [dateStart = " +dateStart+ " dateFinish = "+dateFinish +" ]");
		
		List<ChartModel> grafica = cotizacion.drawChartModel(dateStart, dateFinish);
		List<CotizacionModel> tableCotizacion = cotizacion.chartByDate(dateStart, dateFinish);
		List<Retrabajo> tableRetrabajo = cotizacion.listRetrabajoByDateStart(dateStart, dateFinish);
	    Integer	tableProceso = cotizacion.drawTableProcesos(dateStart, dateFinish);
	    List<AreaTable> tableArea = cotizacion.drawTableArea(dateStart, dateFinish);
	    List<CotizacionModel> tableMotivo = cotizacion.drawTableMotivo(dateStart, dateFinish,"Cancelaci");
	    List<CotizacionModel> tableCancelacion = cotizacion.drawTableMotivo(dateStart, dateFinish,"Rechazo");
	    List<CotizacionModel> tableNOAceptacion = cotizacion.drawTableNoAceptado(dateStart, dateFinish,"No "); 
		model.addAttribute("grafica", grafica);
		model.addAttribute("tableCotizacion", tableCotizacion);
		model.addAttribute("tableRetrabajo", tableRetrabajo);
		model.addAttribute("tableProceso", tableProceso);
		model.addAttribute("tableArea", tableArea);
		model.addAttribute("tableMotivo", tableMotivo);
		model.addAttribute("tableCancelacion", tableCancelacion);
		model.addAttribute("tableNOAceptacion", tableNOAceptacion );
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		model.addAttribute("detallemodel", new DetalleCotizacionModel(dateStart, dateFinish ));
		
		return "mapfre/cotizacion/show";
	}
	
	@PostMapping("/download/cotizacion.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport(@ModelAttribute("datesmodel") DateModel datesmodel)
			throws IOException {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		System.out.println("entro reques2 xls-> " + dateStart + " final " + dateFinish);
		ByteArrayInputStream in = pdfReport.create(datesmodel);
		// return IOUtils.toByteArray(in);
		System.out.println("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=cotizacion.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(in));
	}
	
	@PostMapping("/indicador_cotizacion_sec_detalles")
	public @ResponseBody List<ChartModel> detalleSector(@ModelAttribute("detallemodel}") DetalleCotizacionModel detallemodel) {
		logger.info("detalleSector param = [estatus = "+detallemodel.getDetalle()+" dateStart = " +detallemodel.getDateStart()+ " dateFinish = "+detallemodel.getDateFinish() +" , total = "+detallemodel.getTotal() +" ]");
		
		return cotizacion.drawChartSector(detallemodel) ;
	}
	
	@PostMapping("/indicador_cotizacion_agente")
	public @ResponseBody List<ChartModel> agente(@ModelAttribute("detallemodel}") DetalleCotizacionModel detallemodel) {
		logger.info("agente param = [estatus = "+detallemodel.getDetalle()+" dateStart = " +detallemodel.getDateStart()+ " dateFinish = "+detallemodel.getDateFinish() +" , total = "+detallemodel.getTotal() +" ]");
		
		return cotizacion.drawChartAgente(detallemodel) ;
	}
	
	@PostMapping("/indicador_cotizacion_retrabajo_sector")
	public @ResponseBody List<ChartModel> retrabajoSector(@ModelAttribute("detallemodel}") DetalleCotizacionModel detallemodel) {
		logger.info("retrabajoSector = [estatus = "+detallemodel.getDetalle()+" dateStart = " +detallemodel.getDateStart()+ " dateFinish = "+detallemodel.getDateFinish() +" , total = "+detallemodel.getTotal() +" ]");
		return cotizacion.drawChartretrabajoSector(detallemodel) ;
		
	}
	
	@PostMapping("/indicador_cotizacion_retrabajo_agente")
	public @ResponseBody List<ChartModel> retrabajoAgente(@ModelAttribute("detallemodel}") DetalleCotizacionModel detallemodel) {
		logger.info("retrabajoAgente = [estatus = "+detallemodel.getDetalle()+" dateStart = " +detallemodel.getDateStart()+ " dateFinish = "+detallemodel.getDateFinish() +" , total = "+detallemodel.getTotal() +" ]");
		return cotizacion.drawChartretrabajoAgente(detallemodel) ;
		
	}

	@PostMapping("/cotizacion_table_retrabajo")
	public @ResponseBody List<Retrabajo> chartByDateStart(@ModelAttribute("datesmodel") DateModel datesmodel) {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		System.out.println("retrabajo  " + dateStart + " final " + dateFinish);
		return cotizacion.listRetrabajoByDateStart(dateStart, dateFinish);
	}
	
}
