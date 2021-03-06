package com.ibm.TreinamentoBTP.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import com.ibm.TreinamentoBTP.model.Cambio;


public interface CambioService {
	
	final Double Taxa_USD_BRL = 3.75;
	final Double Taxa_BRL_USD = 0.27;
	final Double Taxa_CNA_BRL = 2.85;
	final Double Taxa_BRL_CNA = 0.35;
	final Double taxa_BRL_BRL = 1.00;
	
	
	Double calcularCambio(Double taxaCambio, Double valorTroca);
}
