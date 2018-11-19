package com.ibm.TreinamentoBTP.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoBTP.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoBTP.model.Correntista;
import com.ibm.TreinamentoBTP.repository.CorrentistaRepository;
import com.ibm.TreinamentoBTP.service.CorrentistaService;

@Service
public class CorrentistaServiceImpl implements CorrentistaService {
	
	private CorrentistaRepository correntistaRepository;
	
	@Autowired
	public CorrentistaServiceImpl(CorrentistaRepository correntistaRepository) {
		this.correntistaRepository = correntistaRepository;
	}
	
	@Override
	public Correntista buscarCorrentista(Long id) {
		Optional<Correntista> correntistaOptional = correntistaRepository.findById(id);
		return correntistaOptional.orElseThrow(() ->
		new ObjetoNaoEncontradoException("Nao foi possivel localizar o correntista de id " + id));
	}

	@Override
	public List<Correntista> buscarTodosCorrentistas(String filtro) {
		if (filtro != null && !filtro.isEmpty())
			return correntistaRepository.findAllByNomeContains(filtro);
		return correntistaRepository.findAll();
	}

	@Override
	public Correntista salvarCorrentista(Correntista correntista) {
		
		return correntistaRepository.save(correntista);
	}

	@Override
	public Correntista atualizarCorrentista(Correntista correntista) {
		if (correntista == null || correntista.getId() == null)
			throw new RuntimeException("Id nao encontrado");
		if (!correntistaRepository.existsById(correntista.getId()))
			throw new ObjetoNaoEncontradoException("");
		return correntistaRepository.save(correntista);
	}

	@Override
	public boolean deletarCorrentista(Long id) {
		if (id == null)
			return false;
		if (!correntistaRepository.existsById(id))
			return false;
		correntistaRepository.deleteById(id);
		return true;
	}

}
