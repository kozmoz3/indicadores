package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartCotizacion;
import com.example.demo.controller.CotizacionsController;
import com.example.demo.model.AreaTable;
import com.example.demo.model.ChartModel;
import com.example.demo.model.CotizacionModel;
import com.example.demo.model.DetalleCotizacionModel;
import com.example.demo.model.Retrabajo;
import com.example.demo.util.CotizacionUtil;
import com.example.demo.util.PercentageUtil;
import com.example.demo.service.XxmpfBpmIndCotDetalleService;
import com.example.demo.service.XxmpfBpmIndCotizacionService;
import com.example.demo.service.impl.CotizacionServiceImpl;

@Service("chartCotizacionImpl")
public class ChartCotizacionImpl implements ChartCotizacion {

	private static final Logger logger = LogManager.getLogger(ChartCotizacionImpl.class);

	@Autowired
	@Qualifier("cotizacionImpl")
	private XxmpfBpmIndCotizacionService cotizacion;

	@Autowired
	@Qualifier("cotizacionDetalleImpl")
    private	XxmpfBpmIndCotDetalleService cotizacionDetalle;

	@Autowired
	private CotizacionServiceImpl cotizacionServiceImpl;

	@Autowired
	private PercentageUtil porcentaje;

	@Autowired
	@Qualifier("cotizacionUtil")
	private CotizacionUtil cotizacionUtil;

	@Override
	public List<CotizacionModel> drawTables() {
		List<CotizacionModel> lisCotizacion = drawChart();
		int totalRegistres = cotizacion.countCotizacion();
		lisCotizacion.add(new CotizacionModel("Global - Total", totalRegistres, "100"));
		return lisCotizacion;
	}

	@Override
	public List<ChartModel> drawChartModel(String dateStart, String dateFinish) {
		List<CotizacionModel> listFolios = chartByDate(dateStart, dateFinish);
		List<ChartModel> listChart = new ArrayList<ChartModel>();

		for (int i = 0; i < listFolios.size(); i++) {
			double y = Double.parseDouble(listFolios.get(i).getPorcentaje());
			listChart.add(new ChartModel(i, listFolios.get(i).getEstatus(), y));
		}
		return listChart;
	}

	@Override
	public List<CotizacionModel> chartByDate(String dateStart, String dateFinish) {
		List<String> listStatus = cotizacion.getStatusByBetween(dateStart, dateFinish);
		String status = "";
		int countStatus = listStatus.size();
		int totalRegistres = cotizacion.countEstatusByFechaInicioBetween(dateStart, dateFinish);
		List<CotizacionModel> listChart = getFoliosByStatus(dateStart, dateFinish, listStatus, totalRegistres);
		return listChart;
	}

	private List<CotizacionModel> getFoliosByStatus(String dateStart, String dateFinish, List<String> listStatus,
			int totalRegistres) {
		int countStatus = listStatus.size();
		List<CotizacionModel> listChart = new ArrayList<CotizacionModel>();
		String status = "";
		if (countStatus > 0) {
			for (int i = 0; i < countStatus; i++) {
				status = listStatus.get(i);
				
				int numFolios = cotizacion.countEstatusByFechaInicioBetweens(dateStart, dateFinish, status);
				String porcentaj = porcentaje.porcentaje(totalRegistres, numFolios);
				logger.info("--> getFoliosByStatus list add = [" + status + ", " + numFolios + ", " + porcentaj + "]");
				listChart.add(new CotizacionModel(status, numFolios, porcentaj));
			}
		} else {
			listChart.add(new CotizacionModel("no hay datos", 0, "0"));
		}
		return listChart;
	}

	@Override
	public List<ChartModel> drawChartModel() {
		List<CotizacionModel> chart = drawChart();
		return drawChartModel(chart);
	}

	public List<ChartModel> drawChartModel(List<CotizacionModel> chart) {

		List<ChartModel> modelChart = new ArrayList<ChartModel>();
		int index = 1;

		for (int i = 0; i < chart.size(); i++) {
			double y = Double.parseDouble(chart.get(i).getPorcentaje());
			modelChart.add(new ChartModel(index, chart.get(i).getEstatus(), y));
			index++;
		}
		return modelChart;
	}

	@Override
	public List<CotizacionModel> drawChart() {
		List<String> listStatus = cotizacion.distinctEstatus();
		return drawChart(listStatus);
	}

	@Override
	public List<CotizacionModel> drawChart(List<String> listStatus) {

		List<CotizacionModel> lisCotizacion = new ArrayList<CotizacionModel>();
		int totalRegistres = cotizacion.countCotizacion();
		String status = "";
		if (listStatus.size() > 0) {
			for (int i = 0; i < listStatus.size(); i++) {

				String statu = listStatus.get(i);
				if (statu == null) {
					status = "PENDIENTE";
				} else {

					status = cotizacionUtil.convertEstatus(statu);
				}
				int numEstatus = cotizacion.countRegistreByEstatus(statu);
				String porcentaj = porcentaje.porcentaje(totalRegistres, numEstatus);
				lisCotizacion.add(new CotizacionModel(status, numEstatus, porcentaj));
			}
		} else {
			lisCotizacion.add(new CotizacionModel("", 0, "0 %"));
		}
		return lisCotizacion;
	}

	public List<CotizacionModel> drawChart(List<String> listStatus, String porcentajes) {

		List<CotizacionModel> lisCotizacion = new ArrayList<CotizacionModel>();
		int totalRegistres = cotizacion.countCotizacion();
		String status = "";
		if (listStatus.size() > 0) {
			for (int i = 0; i < listStatus.size(); i++) {

				String statu = listStatus.get(i);
				if (statu == null) {
					status = "PENDIENTE";
				} else {

					status = cotizacionUtil.convertEstatus(statu);
				}
				int numEstatus = cotizacion.countRegistreByEstatus(statu);
				String porcentaj = porcentaje.porcentaje(totalRegistres, numEstatus);
				lisCotizacion.add(new CotizacionModel(status, numEstatus, porcentaj));
			}
		} else {
			lisCotizacion.add(new CotizacionModel("", 0, "0 %"));
		}
		return lisCotizacion;
	}

	@Override
	public List<Retrabajo> listRetrabajoByDateStart(String dateStart, String dateFinish) {
		List<Retrabajo> listRetrabajo = new ArrayList<Retrabajo>();
		int devolucion = cotizacion.countDevolucionByFechaInicioBetween(dateStart, dateFinish);
		int recotizacion = cotizacion.countRecotizacionByFechaInicioBetween(dateStart, dateFinish);
		listRetrabajo.add(new Retrabajo("DEVOLUCION", devolucion));
		listRetrabajo.add(new Retrabajo("RECOTIZACION", recotizacion));
		return listRetrabajo;
	}

	@Override
	public Integer drawTableProcesos(String dateStart, String dateFinish) {
		return cotizacion.getAVGTiempoDiasByFecaInicio(dateStart, dateFinish);
	}

	@Override
	public List<AreaTable> drawTableArea(String dateStart, String dateFinish) {
		List<String> areaList = cotizacionDetalle.getDistinctAreaByBetween(dateStart, dateFinish);
		List<AreaTable> listTable = new ArrayList<AreaTable>();
		for (int i = 0; i < areaList.size(); i++) {
			Integer dias = cotizacionDetalle.findAVGTiempoDiasByFechaInicio(dateStart, dateFinish, areaList.get(i));
			listTable.add(new AreaTable(i, areaList.get(i), dias));
		}
		return listTable;
	}

	@Override
	public List<CotizacionModel> drawTableMotivo(String dateStart, String dateFinish, String containStatus) {
		List<String> motivoList = cotizacion.findDistinctMotivoByFechaInicio(dateStart, dateFinish, containStatus);
		List<CotizacionModel> motivoTable = new ArrayList<CotizacionModel>();
		int totalRegistres = cotizacion.totalMotivosByFechaInicioBetween(dateStart, dateFinish, containStatus);

		for (int i = 0; i < motivoList.size(); i++) {

			int numFolios = cotizacion.countMotivosByFechaInicioBetween(dateStart, dateFinish, motivoList.get(i),
					containStatus);
			String porcentaj = porcentaje.porcentaje(totalRegistres, numFolios);
			logger.info("--> drawTableMotivo motivo list add status " + containStatus + " = [" + motivoList.get(i)
					+ ", " + numFolios + "," + porcentaj + " ]");
			motivoTable.add(new CotizacionModel(motivoList.get(i), numFolios, porcentaj));
		}
		return motivoTable;
	}
	
	@Override
	public List<CotizacionModel> drawTableNoAceptado(String dateStart, String dateFinish, String containStatus) {
		List<String> motivoList = listNoaceptado(dateStart, dateFinish, containStatus);
				
		List<CotizacionModel> motivoTable = new ArrayList<CotizacionModel>();
		int totalRegistres = cotizacion.totalMotivosByFechaInicioBetween(dateStart, dateFinish, containStatus)+ cotizacion.totalMotivosByFechaInicioBetween(dateStart, dateFinish,"Cierre ");
             int numFolios = 0;
		for (int i = 0; i < motivoList.size(); i++) {
           if(motivoList.get(i).equals("Cierre automático")) {
        	   numFolios = cotizacion.countMotivosByFechaInicioBetween(dateStart, dateFinish, motivoList.get(i),
        			   "Cierre automático");
           }else {
			numFolios = cotizacion.countMotivosByFechaInicioBetween(dateStart, dateFinish, motivoList.get(i),
					containStatus);
           }
			String porcentaj = porcentaje.porcentaje(totalRegistres, numFolios);
			
			logger.info("--> drawTableMotivo motivo list add status " + containStatus + " = [" + motivoList.get(i)
					+ ", " + numFolios + "," + porcentaj + " ]");
			motivoTable.add(new CotizacionModel(motivoList.get(i), numFolios, porcentaj));
		}
		return motivoTable;
	}
	
	
	
	private List<String> listNoaceptado(String dateStart, String dateFinish, String containStatus){
		List<String> motivoList = cotizacion.findDistinctMotivoByFechaInicio(dateStart, dateFinish, containStatus);
		List<String> cierreLista = cotizacion.findDistinctMotivoByFechaInicio(dateStart, dateFinish, "Cierre ");
		for (int i = 0; i < cierreLista.size(); i++) {
			motivoList.add(cierreLista.get(i));
		}
		return motivoList;
	}

	@Override
	public List<CotizacionModel> drawTableNoAceptado(String dateStart, String dateFinish) {
		String containStatus = "";
		return drawTableMotivo(dateStart, dateFinish, containStatus);
	}

	/*
	 * @Override public List<ChartModel> drawChartSector(DetalleCotizacionModel
	 * detallemodel) { String dateStart = detallemodel.getDateStart(); String
	 * dateFinish = detallemodel.getDateFinish(); String status =
	 * detallemodel.getDetalle(); List<String> listCodSector =
	 * cotizacionServiceImpl.findDistinctSectorByFechaInicio(dateStart, dateFinish,
	 * status);
	 * 
	 * int sizeCodsector = listCodSector.size(); List<ChartModel> listSector = new
	 * ArrayList<ChartModel>(); Integer totalRegistres = detallemodel.getTotal();
	 * for(int i = 0; i < sizeCodsector; i++) { Integer numFolios =
	 * cotizacionServiceImpl.countSectorByFechaInicioBetween(dateStart,
	 * dateFinish,status, listCodSector.get(i)); Double y =
	 * Double.parseDouble(porcentaje.porcentaje(totalRegistres, numFolios));
	 * listSector.add(new ChartModel(i,listCodSector.get(i),y));
	 * logger.info("--> drawChartSector Sector = "+listCodSector.get(i)
	 * +" numFolios = "+numFolios); }
	 * logger.info("--> drawChartSector return "+listSector); return listSector; }
	 */
	@Override
	public List<ChartModel> drawChartSector(DetalleCotizacionModel detallemodel) {
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		String status = detallemodel.getDetalle();
		List<String> listCodSector = cotizacionServiceImpl.findDistinctSectorByFechaInicio(dateStart, dateFinish,
				status);
		return listChartModel(listCodSector, detallemodel, "sector1");
	}

	@Override
	public List<ChartModel> drawChartAgente(DetalleCotizacionModel detallemodel) {
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		String status = detallemodel.getDetalle();
		List<String> listCodSector = cotizacionServiceImpl.findDistinctAgenteByFechaInicio(dateStart, dateFinish,status);
		return listChartModel(listCodSector, detallemodel,"agente1" );
	}
	
	@Override
	public List<ChartModel> drawChartretrabajoSector(DetalleCotizacionModel detallemodel) {
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		String retrabajo = detallemodel.getDetalle();
		if(retrabajo.equals("DEVOLUCION")) {
		List<String> listCodSector = cotizacionServiceImpl.distinctSectorByDevolucionesAndFechaInicio(dateStart, dateFinish);
		return listChartModel(listCodSector, detallemodel,"retrabajoSectorDevolucion" );
		}else {
			List<String> listCodSector = cotizacionServiceImpl.distinctSectorByRecotizacionAndFechaInicio(dateStart, dateFinish);	
			return listChartModel(listCodSector, detallemodel,"retrabajoRecotizacion" );
		}
	}
	
	@Override
	public List<ChartModel> drawChartretrabajoAgente(DetalleCotizacionModel detallemodel) {
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		String retrabajo = detallemodel.getDetalle();
		if(retrabajo.equals("DEVOLUCION")) {
		List<String> listCodSector = cotizacionServiceImpl.distinctAgenteByDevolucionesAndFechaInicio(dateStart, dateFinish);
		return listChartModel(listCodSector, detallemodel,"retrabajoAgenteDevolucion" );
		}else {
			List<String> listCodSector = cotizacionServiceImpl.distinctAgenteByRecotizacionAndFechaInicio(dateStart, dateFinish);	
			return listChartModel(listCodSector, detallemodel,"retrabajoRecotizacionAgente" );
		}
	}
	
	private List<ChartModel> listChartModel(List<String> listDetalle, DetalleCotizacionModel detallemodel, String opcion) {
		List<ChartModel> listChartModel = new ArrayList<ChartModel>();
		int sizeCodsector = listDetalle.size();
		Integer totalRegistres = detallemodel.getTotal();

		for (int i = 0; i < sizeCodsector; i++) {
			
			Integer numFolios = countFolio(detallemodel,listDetalle.get(i),opcion);
			Double y = Double.parseDouble(porcentaje.porcentaje(totalRegistres, numFolios));
			listChartModel.add(new ChartModel(i, listDetalle.get(i), y));
			logger.info("--> listChartModel = " + listDetalle.get(i) + " numFolios = " + numFolios);
		}
		return listChartModel;
	}
	
	private Integer countFolio(DetalleCotizacionModel detallemodel,String detalle, String opcion) {
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		String status = detallemodel.getDetalle();
		if(opcion.equals("sector1")) {
			return cotizacionServiceImpl.countSectorByFechaInicioBetween(dateStart, dateFinish, status,
					detalle);
		}if(opcion.equals("agente1")) {
			return cotizacionServiceImpl.countAgenteByFechaInicioBetween(dateStart, dateFinish, status, detalle);
		}if(opcion.equals("retrabajoSectorDevolucion")){
			return cotizacionServiceImpl.countRecotizacionBySectorAndFechaInicio(dateStart, dateFinish, detalle);
		}if(opcion.equals("retrabajoRecotizacion")){
			return cotizacionServiceImpl.countRetrabajoRecotizacionBySectorAndFechaInicio(dateStart, dateFinish, detalle);
		}if(opcion.equals("retrabajoAgenteDevolucion")){
			return cotizacionServiceImpl.countRetrabajoDevolucionAgenteBySectorAndFechaInicio(dateStart, dateFinish, detalle);
		}if(opcion.equals("retrabajoRecotizacionAgente")){
			return cotizacionServiceImpl.countRetrabajoRecotizacionAgenteBySectorAndFechaInicio(dateStart, dateFinish, detalle);
		}else {
		
			return 1;
		}
	}
}
