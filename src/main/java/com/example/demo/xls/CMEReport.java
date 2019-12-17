package com.example.demo.xls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.example.demo.util.StringUtil;
import com.example.demo.util.xls.CellStyleUtil;

@Service
public class CMEReport {
	
	private static final Logger logger = LogManager.getLogger(CMEReport.class);

	private String[] columTitle = { "Número de folio", "ID", "Tipo de solicitud", "Area",
			"Actividad", "Divisional", "Regional", "Oficina Comercial",
			"Usuario analista", "Nombre analista", "Usuario emisor", "Nombre emisor",
			"•	Usuario suscriptor", "•	Nombre suscriptor","•Fecha Inicio",
			"Dias"};
	
	public ByteArrayInputStream create(List<Object[]> list) throws IOException {
		logger.info("methods create ");
		try (Workbook workbook = new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			createSheetOne(workbook, list);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	private void createSheetOne(Workbook workbook,List<Object[]> list) {
		logger.info("methods createSheetOne ");
		Sheet sheet = workbook.createSheet("FP (Folios pendientes)");
		CellStyleUtil style = new CellStyleUtil(workbook);
		cellTitle(sheet, style);
		cellBody(sheet, style, list);
	}
	
	private void cellBody(Sheet sheet, CellStyleUtil style, List<Object[]> results) {
		logger.info("methos cellBody ");
		
		logger.info("methos cellBody estatus = "+results.size());
		int rowId = 4;
		for (Object[] emision : results) {
			Row row = sheet.createRow(rowId++);
			valueColumns(row, emision, style); 	
			autoSizeColumns(sheet);
		}
	}
	private void autoSizeColumns(Sheet sheet) {
		for (int i = 0; i < columTitle.length; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	private void valueColumns(Row row, Object[]results, CellStyleUtil style) {
		logger.info("methods valueColumns");
		for (int i = 0; i < results.length; i++) {
			Cell cellFecha = row.createCell(i);
			cellFecha.setCellValue(StringUtil.containNull(results[i] + ""));
			cellFecha.setCellStyle(style.body());
		}
	}
	
	private void cellTitle(Sheet sheet, CellStyleUtil style) {
		logger.info("methods cellTitle ");
		Row headerRow = sheet.createRow(3);
		for (int col = 0; col < columTitle.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(columTitle[col]);
			cell.setCellStyle(style.title());
			sheet.autoSizeColumn(col);
		}
	}

}
