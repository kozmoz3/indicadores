package com.example.demo.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.util.JFreeChartUtil;
import com.example.demo.util.PdfUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class TPMPdf {

private static final Logger logger = LogManager.getLogger(TPMPdf.class);
	
	public ByteArrayInputStream create(List<ChartBarraPorcentajeModel> listGrafica) {
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfUtil.addHeader(writer, "Reporte de Tiempo promedio por los emisores", " ");
			
			BufferedImage bufferedImage = JFreeChartUtil.barChartHorizontalByListChartBarraPorcentajeModel(listGrafica, "Tiempo promedio por movimiento realizado por los emisores", "", "").createBufferedImage(500, 300);
			 Image image = Image.getInstance(bufferedImage, null);
			 document.add(image);
			document.close();
		}catch(Exception e) {
			logger.error("Method create error -- "+e);
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
