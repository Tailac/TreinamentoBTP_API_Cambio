package com.ibm.TreinamentoBTP.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoBTP.exception.ObjetoExistenteException;
import com.ibm.TreinamentoBTP.exception.InternalException;
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
		new InternalException("Nao foi possivel localizar o correntista de id " + id));
	}

	@Override
	public List<Correntista> buscarTodosCorrentistas(String filtro) {
		if (filtro != null && !filtro.isEmpty())
			return correntistaRepository.findAllByNomeContains(filtro);
		return correntistaRepository.findAll();
	}

	@Override
	public Correntista salvarCorrentista(Correntista correntista) {
		if(correntista == null || correntista.getCpf() == null)
			throw new InternalException("Correntista não encontrado");		
			Optional<Correntista> retCorrentista = correntistaRepository.findByCpf(correntista.getCpf());
			if(retCorrentista.isPresent()){
				throw new InternalException("Correntista já cadastrado");
			}else {
				return correntistaRepository.save(correntista);
			}
		
	}

	@Override
	public Correntista atualizarCorrentista(Correntista correntista) {
		if (correntista == null || correntista.getId() == null)
			throw new InternalException("Correntista não encontrado");
		if (!correntistaRepository.existsById(correntista.getId()))
			throw new InternalException("Correntista náo encontrado");
		Optional<Correntista> retCorrentista = correntistaRepository.findByCpf(correntista.getCpf());
		if(retCorrentista.isPresent()) {
			throw new InternalException("Já existe correntista com este CPF");
		}
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
