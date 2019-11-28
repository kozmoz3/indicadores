package com.example.demo.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartCotizacion;
import com.example.demo.model.AreaTable;
import com.example.demo.model.ChartModel;
import com.example.demo.model.CotizacionModel;
import com.example.demo.model.DateModel;
import com.example.demo.model.Retrabajo;
import com.example.demo.util.JFreeChartUtil;
import com.example.demo.util.PdfUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class CotizacionPdf {
	
	private static final Logger logger = LogManager.getLogger(CotizacionPdf.class);
	
	@Autowired
	private ChartCotizacion cotizacion;

	public ByteArrayInputStream create(DateModel datesmodel) {
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfUtil.addHeader(writer, "Reporte de Cotizacion", "fecha: "+datesmodel.getDateStart()+" al "+datesmodel.getDateFinish());
			document.add(new Paragraph("Estatus."));
			document.add(getEstatus(datesmodel));
			document.add(new Paragraph("Retrabajo."));
			document.add(getTableRetrabajo(datesmodel));
			document.add(new Paragraph("Tiempos."));
			document.add(getTableTiempos(datesmodel));
			document.add(new Paragraph("Motivos."));
			document.add(getTableMotivos(datesmodel));
			document.close();
		}catch(Exception e) {
			logger.error("Method create error -- "+e);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private  PdfPTable getTableMotivos(DateModel datesmodel){
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[]{10, 10,10});
			table.setTotalWidth(527);
			table.getDefaultCell().setBorder(Rectangle.BOTTOM);
			table.addCell(getTableCancelacion(datesmodel));
			table.addCell(getTableRechazo(datesmodel));
			table.addCell(getTableNoAceptado(datesmodel));
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableNoAceptado(DateModel datesmodel){
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[]{10, 10});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Motivo de rechazo",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("# Folios",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("%",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           List<CotizacionModel> tableMotivo = cotizacion.drawTableNoAceptado(datesmodel.getDateStart(), datesmodel.getDateFinish(),"No ");
			for(CotizacionModel motivo : tableMotivo) {
				PdfPCell cell;

                cell = new PdfPCell(new Phrase(motivo.getEstatus()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getNumFolios()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getPorcentaje()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableRechazo(DateModel datesmodel){
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[]{10, 10});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Motivo de rechazo",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("# Folios",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("%",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           List<CotizacionModel> tableMotivo = cotizacion.drawTableMotivo(datesmodel.getDateStart(), datesmodel.getDateFinish(),"Rechazo");
			for(CotizacionModel motivo : tableMotivo) {
				PdfPCell cell;

                cell = new PdfPCell(new Phrase(motivo.getEstatus()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getNumFolios()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getPorcentaje()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableCancelacion(DateModel datesmodel){
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[]{10, 10});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Motivo de Cancelacion",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("# Folios",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("%",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           List<CotizacionModel> tableMotivo = cotizacion.drawTableMotivo(datesmodel.getDateStart(), datesmodel.getDateFinish(),"Cancelaci");
			for(CotizacionModel motivo : tableMotivo) {
				PdfPCell cell;

                cell = new PdfPCell(new Phrase(motivo.getEstatus()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getNumFolios()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(motivo.getPorcentaje()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableTiempos(DateModel datesmodel){
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{15, 15});
			table.setTotalWidth(527);
			table.getDefaultCell().setBorder(Rectangle.BOTTOM);
			table.addCell(getTableProceso(datesmodel));
			table.addCell(getTableArea(datesmodel));
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableArea(DateModel datesmodel){
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{15, 15});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Area",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("Dias",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           List<AreaTable> tableArea = cotizacion.drawTableArea(datesmodel.getDateStart(), datesmodel.getDateFinish());
			for(AreaTable area : tableArea) {
				PdfPCell cell;

                cell = new PdfPCell(new Phrase(area.getArea()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(area.getDias()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableProceso(DateModel datesmodel){
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{15, 15});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Procesos",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("Dias",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           Integer	tableProceso = cotizacion.drawTableProcesos(datesmodel.getDateStart(), datesmodel.getDateFinish());
			
				PdfPCell cell;

                cell = new PdfPCell(new Phrase("Cotizacion"));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(tableProceso+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getTableRetrabajo(DateModel datesmodel){
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{15, 15});
			table.setTotalWidth(527);
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Retrabajo",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
           hcell = new PdfPCell(new Phrase("#Folios",headFont));
           hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(hcell);
           
			List<Retrabajo> tableRetrabajo = cotizacion.listRetrabajoByDateStart(datesmodel.getDateStart(), datesmodel.getDateFinish());
			for(Retrabajo retrabajoTable : tableRetrabajo) {
				PdfPCell cell;

                cell = new PdfPCell(new Phrase(retrabajoTable.getRecotizacion()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(retrabajoTable.getFolios()+""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	private  PdfPTable getEstatus(DateModel datesmodel){
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{15, 15});
			table.setTotalWidth(527);
			table.getDefaultCell().setBorder(Rectangle.BOTTOM);
			table.addCell(getTableEstatus(datesmodel));
			BufferedImage bufferedImage = getGrafica(datesmodel).createBufferedImage(500, 300);
			 Image image = Image.getInstance(bufferedImage, null);
			table.addCell(image);
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
	
	
	private JFreeChart getGrafica(DateModel datesmodel) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<ChartModel> listGrafica = cotizacion.drawChartModel(datesmodel.getDateStart(), datesmodel.getDateFinish());
		for(ChartModel grafica: listGrafica) {
			dataSet.setValue(grafica.getName(), grafica.getY());
		}
		return JFreeChartUtil.generatePieChart(dataSet, "");
	}
	
	private  PdfPTable getTableEstatus(DateModel datesmodel){
		PdfPTable table = new PdfPTable(3); 
		try {
			
			PdfPCell hcell;
			 Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			hcell = new PdfPCell(new Phrase("Estatus",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("# Folios",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("% Folios",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            List<CotizacionModel> tableCotizacion = cotizacion.chartByDate(datesmodel.getDateStart(), datesmodel.getDateFinish());
			
            for(CotizacionModel cotizacion : tableCotizacion) {
            	PdfPCell cell;

                cell = new PdfPCell(new Phrase(cotizacion.getEstatus()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(cotizacion.getNumFolios()+""));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(cotizacion.getPorcentaje()+"%")));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }
            
		}catch(Exception e) {
			logger.error("Method getEstatuse error -- "+e);
		}
		return table;
	}
}
