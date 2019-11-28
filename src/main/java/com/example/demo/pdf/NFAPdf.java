package com.example.demo.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DateModel;
import com.example.demo.util.JFreeChartUtil;
import com.example.demo.util.PdfUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class NFAPdf {

	private static final Logger logger = LogManager.getLogger(NFAPdf.class);
	
	public ByteArrayInputStream create(DateModel datesmodel,List<ChartBarraModel> listGrafica) {
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfUtil.addHeader(writer, "Reporte de Numero de folios atendidos", "fecha: "+datesmodel.getDateStart()+" al "+datesmodel.getDateFinish());
			
			BufferedImage bufferedImage = getGrafica(listGrafica).createBufferedImage(500, 300);
			 Image image = Image.getInstance(bufferedImage, null);
			 document.add(image);
			document.close();
		}catch(Exception e) {
			logger.error("Method create error -- "+e);
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private JFreeChart getGrafica(List<ChartBarraModel> listGrafica) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
	    for(int i = 0; i<listGrafica.size(); i++) {
	    	dataset.addValue(listGrafica.get(i).getData()[0],listGrafica.get(i).getName(),"");
	    }
	    
	    return JFreeChartUtil.generateBarChartHorizontal(dataset, "Numero de Folios Atendidos", "", "");
	}
}
