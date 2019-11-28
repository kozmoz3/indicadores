package com.example.demo.xls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.service.NMRService;
import com.example.demo.util.StringUtil;
import com.example.demo.util.xls.CellStyleUtil;

@Service
public class NMRReport {
	
	@Autowired
	private NMRService nmrService;

	private String[] columTitle = { "Número de folio", "•	Sector (ramo)", "Tipo de solicitud", "•	Divisional",
			"Regional", "•	Oficina comercial", "•	Agente", "•	Estatus",
			"•	Motivo (en caso de devoluciones y rechazos)", "•	Usuario analista", "•	Usuario emisor",
			"•	Usuario suscriptor", "•Fecha y hora en la que el folio fue atendido" };
	
	public ByteArrayInputStream create(DateModel datesmodel) throws IOException {
		try (Workbook workbook = new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			createSheetOne(workbook, datesmodel);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	private void createSheetOne(Workbook workbook, DateModel datesmodel) {
		Sheet sheet = workbook.createSheet("NMR (Numero de movimientos realizados)");
		CellStyleUtil style = new CellStyleUtil(workbook);
		cellTitle(sheet, style);
		cellBody(sheet, style, datesmodel);
	}
	
	private void cellBody(Sheet sheet, CellStyleUtil style, DateModel datesmodel) {
		List<Object[]> results = nmrService.allNumMovimientosRealizados(datesmodel);
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
