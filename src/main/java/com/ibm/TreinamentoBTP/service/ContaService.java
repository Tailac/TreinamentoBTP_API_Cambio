package com.ibm.TreinamentoBTP.service;

import java.util.List;

import com.ibm.TreinamentoBTP.model.Cambio;
import com.ibm.TreinamentoBTP.model.Conta;

public interface ContaService {
	
	Conta buscarConta(Long id);
	Conta criarConta(Conta conta);
	Conta atualizarConta(Conta conta);
	boolean deletarConta(Long id);
	Conta depositar(Conta conta, Double valor,Double taxaCambio);
	Conta sacar(Conta conta, Double valor, Double taxaCambio);
	//TODO:FAZER TABELA PARA CAMBIO
	Cambio listarTaxaCambio();
	Cambio ConsultarCambio(Double taxaCambio, Double valor);	
}
