package com.example.demo.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartUtil {

	public  static  JFreeChart generatePieChart(DefaultPieDataset dataSet, String title) {
		return ChartFactory.createPieChart(title, dataSet, true, true, false);
	}
	
	public  static  JFreeChart generateBarChart(CategoryDataset dataSet, String title,String CategoriesAxis,String valueAxis) {
		return ChartFactory.createBarChart(title,
				                           CategoriesAxis,
				                           valueAxis, 
				                           dataSet,
				                           PlotOrientation.HORIZONTAL,
				                           true, true, false);
		
	}
}
