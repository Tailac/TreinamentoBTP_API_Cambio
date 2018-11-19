package com.ibm.TreinamentoBTP.service.impl;

import com.ibm.TreinamentoBTP.model.Cambio;
import com.ibm.TreinamentoBTP.service.CambioService;

public class CambioServiceImpl implements CambioService{

	@Override
	public Cambio calcularCambio(Double taxaCambio, Double valorTroca) {
		
		Cambio cambio = new Cambio();
		Double valorConvertido = valorTroca * taxaCambio;
		cambio.setTaxaCabmio(taxaCambio);
		cambio.setValorTroca(valorTroca);
		cambio.setValorConvertido(valorConvertido);
		return cambio;
	}

}
