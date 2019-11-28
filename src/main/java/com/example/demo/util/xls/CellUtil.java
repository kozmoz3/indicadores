package com.example.demo.util.xls;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class CellUtil {
	
	public CellStyle headerTable(Workbook workbook) {
		CellStyle style =  colorAndBorder(workbook);
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		return style;
	}
	public CellStyle colorAndBorder(Workbook workbook) {
		CellStyle style = allBorder(workbook);
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());  
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		return style;
	}
	
	public CellStyle allBorder(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();  
		   style.setBorderBottom(BorderStyle.THIN);  
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
         style.setBorderRight(BorderStyle.THIN);  
         style.setBorderTop(BorderStyle.THIN);  
         style.setBorderLeft(BorderStyle.THIN);
         return style;
	}

}
