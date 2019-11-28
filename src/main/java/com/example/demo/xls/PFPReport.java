package com.example.demo.xls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.service.PFPService;
import com.example.demo.util.StringUtil;
import com.example.demo.util.xls.CellStyleUtil;

@Service
public class PFPReport {

	@Autowired
	PFPService pfpService;

	private String[] columTitle = { "Número de folio", "•	Sector (ramo)", "Tipo de solicitud", "•	Divisional",
			"Regional", "•	Oficina comercial", "•	Agente", "•	Estatus",
			"•	Motivo (en caso de devoluciones y rechazos)", "•	Usuario analista", "•	Usuario emisor",
			"•	Usuario suscriptor", "•Fecha y hora en la que el folio fue atendido" };

	public ByteArrayInputStream create() throws IOException {
		try (Workbook workbook = new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			createSheetOne(workbook);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	private void createSheetOne(Workbook workbook) {
		Sheet sheet = workbook.createSheet("PFP (Promedio de folios pendientes)");
		CellStyleUtil style = new CellStyleUtil(workbook);
		cellTitle(sheet, style);
		cellBody(sheet, style);
	}

	private void cellBody(Sheet sheet, CellStyleUtil style) {
		List<Object[]> results = pfpService.allFoliosPendiente();
		int rowId = 4;
		for (Object[] emision : results) {
			Row row = sheet.createRow(rowId++);
			valueColumns(row, emision, style); 	
			//autoSizeColumns(sheet);
		}
	}
	
	private void valueColumns(Row row, Object[]results, CellStyleUtil style) {
		for (int i = 0; i < results.length; i++) {
			Cell cellFecha = row.createCell(i);
			cellFecha.setCellValue(StringUtil.containNull(results[i] + ""));
			cellFecha.setCellStyle(style.body());
		}
	}

	private void cellTitle(Sheet sheet, CellStyleUtil style) {
		Row headerRow = sheet.createRow(3);
		for (int col = 0; col < columTitle.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(columTitle[col]);
			cell.setCellStyle(style.title());
			sheet.autoSizeColumn(col);
		}
	}

}
