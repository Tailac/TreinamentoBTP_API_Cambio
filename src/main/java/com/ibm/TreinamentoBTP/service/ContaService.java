package com.ibm.TreinamentoBTP.service;

import com.ibm.TreinamentoBTP.model.Conta;

public interface ContaService {
	
	Conta buscarConta(Integer numConta);
	Conta criarConta(Conta conta);
	Conta atualizarConta(Conta conta);
	boolean deletarConta(Long id);
}
