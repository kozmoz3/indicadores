package com.example.demo.util.xls;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyleUtil {
	
	private Workbook workbook;

	public CellStyleUtil(){
	}
	
	FontUtil font;
	
	public CellStyleUtil(Workbook workbook) {
		super();
		this.workbook = workbook;
		font = new FontUtil(workbook);
	}

	public CellStyle title() {
		CellStyle cell = body();
		cell.setFillBackgroundColor((short) 12);
		cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setFont(font.white());
		return cell;
	}
	
	public CellStyle body() {
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(font.black());
		headerCellStyle.setAlignment(HorizontalAlignment.JUSTIFY);
		headerCellStyle.setBorderBottom(BorderStyle.THIN);
		headerCellStyle.setBorderLeft(BorderStyle.THIN);
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		headerCellStyle.setBorderTop(BorderStyle.THIN);
		return headerCellStyle;
	}
	
	
}
