package com.example.demo.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartFTE;
import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DateModel;
import com.example.demo.util.JFreeChartUtil;
import com.example.demo.util.PdfUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FTEPdf {

	private static final Logger logger = LogManager.getLogger(FTEPdf.class);

	@Autowired
	private ChartFTE service;

	public ByteArrayInputStream create() {
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfUtil.addHeader(writer, "Reporte de Efectividad por FTP", " ");
			document.add(new Paragraph("Graficas del Mes Actual \n \r"));
			document.add(new Paragraph(""));
			document.add(chartMonthCurrent());
			document.add(new Paragraph("Graficas del Mes Anterior \n \r"));
			document.add(chartMonthPrevious());
			document.close();
		} catch (Exception e) {
			logger.error("Method create error -- " + e);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private PdfPTable chartMonthCurrent() {
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 15, 15, });
			table.setTotalWidth(527);
			BufferedImage bufferedImageAutos = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothCurrent("Autos"),
							"Autos", "", "")
					.createBufferedImage(500, 300);
			Image imageAutos = Image.getInstance(bufferedImageAutos, null);
			table.addCell(imageAutos);
			BufferedImage bufferedImageAyE = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothCurrent("AyE"),
							"AyE", "", "")
					.createBufferedImage(500, 300);
			Image imageAyE = Image.getInstance(bufferedImageAyE, null);
			table.addCell(imageAyE);
			BufferedImage bufferedImageDaños = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothCurrent("Daños"),
							"Daños", "", "")
					.createBufferedImage(500, 300);
			Image imageDaños = Image.getInstance(bufferedImageDaños, null);
			table.addCell(imageDaños);
			BufferedImage bufferedImageVida = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothCurrent("Vida"),
							"Vida", "", "")
					.createBufferedImage(500, 300);
			Image imageVida = Image.getInstance(bufferedImageVida, null);
			table.addCell(imageVida);
			
		} catch (Exception e) {
			logger.error("Method getEstatuse error -- " + e);
		}
		return table;
	}
	
	private PdfPTable chartMonthPrevious() {
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 15, 15, });
			table.setTotalWidth(527);
			BufferedImage bufferedImageAutos = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothPrevious("Autos"),
							"Autos", "", "")
					.createBufferedImage(500, 300);
			Image imageAutos = Image.getInstance(bufferedImageAutos, null);
			table.addCell(imageAutos);
			BufferedImage bufferedImageAyE = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothPrevious("AyE"),
							"AyE", "", "")
					.createBufferedImage(500, 300);
			Image imageAyE = Image.getInstance(bufferedImageAyE, null);
			table.addCell(imageAyE);
			BufferedImage bufferedImageDaños = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothPrevious("Daños"),
							"Daños", "", "")
					.createBufferedImage(500, 300);
			Image imageDaños = Image.getInstance(bufferedImageDaños, null);
			table.addCell(imageDaños);
			BufferedImage bufferedImageVida = JFreeChartUtil
					.verticalBarChartByListChartCombinationModel(service.createMothPrevious("Vida"),
							"Vida", "", "")
					.createBufferedImage(500, 300);
			Image imageVida = Image.getInstance(bufferedImageVida, null);
			table.addCell(imageVida);
			
		} catch (Exception e) {
			logger.error("Method getEstatuse error -- " + e);
		}
		return table;
	}

}
