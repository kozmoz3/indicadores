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
import com.example.demo.service.FPRService;
import com.example.demo.service.NMRService;
import com.example.demo.util.StringUtil;
import com.example.demo.util.xls.CellStyleUtil;

@Service
public class FPRReport {

	private String[] columTitle = {"SECTOR","RANGO DE HORARIO","FOLIOS RECIBIDOS","FOLIOS ATENDIDOS"};
	
	private String[] rangoHoras = { "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59",
			"13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 18:00", };

	private int[] horario = { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	
	private String[] title = {"  FECHA  "," USUARIO EMISOR ","  SECTOR  "," HORA ","FOLIO RECIBIDO"};
	
	@Autowired
	private FPRService fprService;
	
	public ByteArrayInputStream create(DateModel datesmodel) throws IOException {
		try (Workbook workbook = new HSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			List<String> listSectores = fprService.getSectores();
			List<Object []> listRecibidos = fprService.allFolioRecibidoByFechaInicio(datesmodel);
			List<Object []> listAtendidos = fprService.allFolioAtendidoByFechaFin(datesmodel);
			CellStyleUtil style = new CellStyleUtil(workbook);
			createSheetOne(workbook, style,listSectores,listRecibidos,listAtendidos);
			createSheetRecibidos(workbook,style,"Detalle de Folios Recibidos", listRecibidos);
			createSheetRecibidos(workbook,style,"Detalle de Folios Atendidos", listAtendidos);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	private void createSheetRecibidos(Workbook workbook,CellStyleUtil style,String nameSheet,List<Object []> listRecibidos) {
		Sheet sheet = workbook.createSheet(nameSheet);
		int rowId = 4;
		cellRecibidoAndAtendido(sheet, style);
		for (Object[] emision : listRecibidos) {
			Row row = sheet.createRow(rowId++);
			valueColumns(row, emision, style); 	
			//autoSizeColumns(sheet);
		}
	}
	
	private void valueColumns(Row row, Object[]results, CellStyleUtil style) {
		for (int i = 0; i < results.length; i++) {
			Cell cellFecha = row.createCell(i);
			if(i == 0) {
				cellFecha.setCellValue(StringUtil.getFecha(results[i].toString()));
				Cell cellHora = row.createCell(3);
				System.out.println("hhhhh "+StringUtil.getHora(results[i].toString()));
				cellHora.setCellValue(StringUtil.getHora(results[i].toString()));
				cellHora.setCellStyle(style.body());
			}
			
			if(i == 1 || i == 2 ) {
			cellFecha.setCellValue(StringUtil.containNull(results[i] + ""));
			}
			
			if(i == 3) {
				
				Cell cellFolio = row.createCell(4);
				cellFolio.setCellValue(results[i].toString());
				cellFolio.setCellStyle(style.body());
			}
			cellFecha.setCellStyle(style.body());
		}
	}
	private void cellRecibidoAndAtendido(Sheet sheet, CellStyleUtil style) {
		Row headerRow = sheet.createRow(3);
		for (int col = 0; col < title.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(title[col]);
			cell.setCellStyle(style.title());
			sheet.autoSizeColumn(col);
		}
	}
	
		private void createSheetOne(Workbook workbook,CellStyleUtil style,List<String> listSectores,List<Object []> listRecibidos,List<Object []> listAtendidos ) {
			Sheet sheet = workbook.createSheet("FPR (Folios atendidos y recibidos)");
			cellTitle(sheet, style);
			cellBody(sheet, style, listSectores,listRecibidos,listAtendidos);
		}
		
		private void cellBody(Sheet sheet, CellStyleUtil style, List<String> listSectores,List<Object []> listRecibidos,List<Object []> listAtendidos) {
			int rowId = 4;
			for (int j = 0; j < listSectores.size(); j++) {
				String sector = listSectores.get(j);
				int numRow = rangoHoras.length * j;
				valueColumns(sheet, style ,sector, listRecibidos,listAtendidos, numRow); 	
				//autoSizeColumns(sheet);
			}
		}
		
		private void valueColumns(Sheet sheet, CellStyleUtil style, String sector, List<Object []> listRecibidos,List<Object []> listAtendidos,int numRow) {
			int rowId = 4 + numRow;
			for (int i = 0; i < rangoHoras.length; i++) {
				Row row = sheet.createRow(rowId++);
				Cell cellSector = row.createCell(0);
				cellSector.setCellValue(sector);
				cellSector.setCellStyle(style.body());
				
				String hora = rangoHoras[i];
				Cell cellHora = row.createCell(1);
				cellHora.setCellValue(hora);
				cellHora.setCellStyle(style.body());
				
				Cell cellRecibido = row.createCell(2);
				cellRecibido.setCellValue(count(listRecibidos,horario[i],sector)+"");
				cellRecibido.setCellStyle(style.body());
				
				Cell cellAtendido = row.createCell(3);
				cellAtendido.setCellValue(count(listAtendidos,horario[i],sector)+"");
				cellAtendido.setCellStyle(style.body());
			}
		}
		
		
		private int count( List<Object []> folios, int hora, String sector) {
			int count = 0;
			for (int i = 0; i <  folios.size(); i++) {
				
				if(StringUtil.getHoraByFecha(folios.get(i)[0].toString())== hora && folios.get(i)[2].toString().equals(sector)) {
		//			logger.info("method-: count param [ "+folios.get(i)[0].toString()+ " hora = "+hora+ " sector"+folios.get(i)[2].toString() + " == "+sector);
					count++;
				}
			}
			return count;
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
