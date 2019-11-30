package com.example.demo.util;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.model.ChartModel;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.NSEPdf;

public class JFreeChartUtil {
	
	private static final Logger logger = LogManager.getLogger(JFreeChartUtil.class);
	
	public static  JFreeChart simplePieChartByListChartModel(List<ChartModel> listGrafica,String title) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		
		for(ChartModel grafica: listGrafica) {
			logger.info("Method simplePieChartByListChartModel add[ name ="+grafica.getName()+", y= "+grafica.getY()+"]");
			dataSet.setValue(grafica.getName(), grafica.getY());
		}
		return generatePieChart(dataSet, title);
	}
	

	public  static  JFreeChart generatePieChart(DefaultPieDataset dataSet, String title) {
		return ChartFactory.createPieChart(title, dataSet, true, true, false);
	}
	
	public static JFreeChart barChartHorizontalByListChartBarraPorcentajeModel(List<ChartBarraPorcentajeModel> listGrafica, String title,String CategoriesAxis,String valueAxis) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
	    for(int j = 0; j<listGrafica.size(); j++) {
	    	List<BigDecimal>listData = listGrafica.get(j).getData();
	    	List<String>listCategoria =listGrafica.get(j).getCategoria();
	    	for(int i = 0; i<listData.size(); i++) {
	    		logger.info("Method barChartHorizontalByListChartBarraPorcentajeModel add[ data ="+listData.get(i)+", name = "+listGrafica.get(j).getName()+",categoria = "+listCategoria.get(i)+"]");
	    		dataset.addValue(listData.get(i),listGrafica.get(j).getName(),listCategoria.get(i));
	    	}
	    	//dataset.addValue(listGrafica.get(i).getData()[0],listGrafica.get(i).getName(),"");
	    }
	    
	    return generateBarChartHorizontal(dataset, title,CategoriesAxis,valueAxis);
	}
	
	public static JFreeChart simpleBarChartHorizontalByChartBarraModel(List<ChartBarraModel> listGrafica, String title,String CategoriesAxis,String valueAxis) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
	    for(int i = 0; i<listGrafica.size(); i++) {
	    	logger.info("Method simpleBarChartHorizontalByChartBarraModel add[ data ="+listGrafica.get(i).getData()[0]+", name ="+listGrafica.get(i).getName());
	    	dataset.addValue(listGrafica.get(i).getData()[0],listGrafica.get(i).getName(),"");
	    }
	    
	    return generateBarChartHorizontal(dataset, title,CategoriesAxis,valueAxis);
	}
	
	public  static  JFreeChart generateBarChartHorizontal(CategoryDataset dataSet, String title,String CategoriesAxis,String valueAxis) {
		return ChartFactory.createBarChart(title,
				                           CategoriesAxis,
				                           valueAxis, 
				                           dataSet,
				                           PlotOrientation.HORIZONTAL,
				                           true, true, false);
		
	}
	
}
