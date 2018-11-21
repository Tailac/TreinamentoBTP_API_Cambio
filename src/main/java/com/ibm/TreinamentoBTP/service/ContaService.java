package com.ibm.TreinamentoBTP.service;

import java.util.List;

import com.ibm.TreinamentoBTP.model.Cambio;
import com.ibm.TreinamentoBTP.model.Conta;

public interface ContaService {
	
	Conta buscarConta(Long id);
	Conta criarConta(Conta conta);
	Conta atualizarConta(Conta conta);
	List<Conta> buscarTodosConta(Integer filtro);
	boolean deletarConta(Long id);
	Conta depositar(Integer numConta, Double valor,Double taxaCambio);
	Conta sacar(Integer numConta, Double valor, Double taxaCambio);
	Cambio ConsultarCambio(Double taxaCambio, Double valor);	
}
