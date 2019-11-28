package com.example.demo.bussine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.model.CotizacionModel;

public interface CreateXls {

	public ByteArrayInputStream  create(List<CotizacionModel> cotizacion)throws IOException;
}
