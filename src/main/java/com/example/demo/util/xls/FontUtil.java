package com.example.demo.util.xls;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class FontUtil {
	private Workbook workbook;
	
	public FontUtil() {
	}

	public FontUtil(Workbook workbook) {
		super();
		this.workbook = workbook;
	}

	public Font black() {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		return headerFont;
	}
	
	public Font white() {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		return headerFont;
	}
}
