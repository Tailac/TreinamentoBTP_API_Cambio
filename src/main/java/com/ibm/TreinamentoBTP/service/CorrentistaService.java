package com.ibm.TreinamentoBTP.service;

import java.util.List;

import com.ibm.TreinamentoBTP.model.Correntista;

public interface CorrentistaService {
	Correntista buscarCorrentista(Long id);
	List<Correntista> buscarTodosCorrentistas(String filtro);
	Correntista salvarCorrentista(Correntista correntista);
	Correntista atualizarCorrentista(Correntista correntista);
	boolean deletarCorrentista(Long id);
}
