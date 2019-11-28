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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.CotizacionsController;
import com.example.demo.service.FPPTService;
import com.example.demo.util.StringUtil;
import com.example.demo.util.xls.CellStyleUtil;

@Service
public class FPPTReport {
	
	private static final Logger logger = LogManager.getLogger(FPPTReport.class);

	private String[] columTitle = { "Número de folio", "•	Sector (ramo)", "Tipo de solicitud", "•	Divisional",
			"Regional", "•	Oficina comercial", "•	Agente", "•	Estatus",
			"•	Motivo (en caso de devoluciones y rechazos)", "•	Usuario analista", "•	Usuario emisor",
			"•	Usuario suscriptor", "•Fecha y hora en la que el folio fue atendido" };

	@Autowired
	private FPPTService fpptService;
	
	public ByteArrayInputStream create() throws IOException {
		logger.info("methods create ");
		try (Workbook workbook = new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			createSheetOne(workbook);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	private void createSheetOne(Workbook workbook) {
		logger.info("methods createSheetOne ");
		Sheet sheet = workbook.createSheet("FP (Folios pendientes)");
		CellStyleUtil style = new CellStyleUtil(workbook);
		cellTitle(sheet, style);
		cellBody(sheet, style);
	}

	private void cellBody(Sheet sheet, CellStyleUtil style) {
		logger.info("methos cellBody ");
		List<Object[]> results = fpptService.allByEstatus();
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
