package com.example.demo.bussine.impl;

import com.example.demo.bussine.CreateXls;
import com.example.demo.model.CotizacionModel;
import com.example.demo.util.xls.CellUtil;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service("createXlsImpl")
public class CreateXlsImpl implements CreateXls {
	
	@Autowired
	CellUtil utilcell;

	@Override
	public ByteArrayInputStream create(List<CotizacionModel> listCotizacion) throws IOException {
		String[] COLUMNs = { "Estatus", "No Folio", "% Foilio" };

		try (Workbook workbook =  new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			createSheetTable(workbook, COLUMNs,createHelper, listCotizacion);
			createSheetTable2(workbook, COLUMNs,createHelper);
			
             
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	private void createSheetTable(Workbook workbook, String[] COLUMNs,CreationHelper createHelper, List<CotizacionModel> listCotizacion) {
		Sheet sheet = workbook.createSheet("Customers");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Row for Header
		Row headerRow = sheet.createRow(1);
      int colStart = 3;
		// Header
		for (int col = 0; col < COLUMNs.length; col++) {
			Cell cell = headerRow.createCell(col+colStart);
			
			cell.setCellValue(COLUMNs[col]);
			cell.setCellStyle(headerCellStyle);
			
	            cell.setCellStyle(utilcell.headerTable(workbook));
		}
		CellStyle ageCellStyle = workbook.createCellStyle();
		ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
		int rowIdx = 2;
		
		for(CotizacionModel cotizacion : listCotizacion) {
			Row row = sheet.createRow(rowIdx++);
			Cell cell = row.createCell(3);
			cell.setCellValue(cotizacion.getEstatus());
			Cell cell2 =row.createCell(4);
			cell2.setCellValue(cotizacion.getNumFolios()+"");
			Cell cell3 =row.createCell(5);
			cell3.setCellValue(cotizacion.getPorcentaje());
			cell.setCellStyle(styleTable(workbook));
			cell2.setCellStyle(styleTable(workbook));
			cell3.setCellStyle(styleTable(workbook));
			Cell ageCell = row.createCell(colStart);
	        ageCell.setCellValue(cotizacion.getEstatus());
	        ageCell.setCellStyle(ageCellStyle);
		}
	}
	
	private CellStyle styleTable(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();  
		   style.setBorderBottom(BorderStyle.THIN);  
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
         style.setBorderRight(BorderStyle.THIN);  
         style.setBorderTop(BorderStyle.THIN);  
         style.setBorderLeft(BorderStyle.THIN);
         return style;
	}

	private void createSheetTable2(Workbook workbook, String[] COLUMNs,CreationHelper createHelper) {
		Sheet sheet = workbook.createSheet("Customers2");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Row for Header
		Row headerRow = sheet.createRow(1);

		// Header
		for (int col = 0; col < COLUMNs.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(COLUMNs[col]);
			cell.setCellStyle(headerCellStyle);
		}
		CellStyle ageCellStyle = workbook.createCellStyle();
		ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
		
	}

}
