package com.ibm.TreinamentoBTP.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.TreinamentoBTP.model.Cambio;
import com.ibm.TreinamentoBTP.service.CambioService;

@Configuration
public class CambioServiceImpl implements CambioService{
	
	@Override
	public Double calcularCambio(Double taxaCambio, Double valorTroca) {
		
		Cambio cambio = new Cambio();
		Double valorConvertido = valorTroca * taxaCambio;
/*		cambio.setTaxaCabmio(taxaCambio);
		cambio.setValorTroca(valorTroca);
		cambio.setValorConvertido(valorConvertido);*/
		return valorConvertido;		
	}
}
