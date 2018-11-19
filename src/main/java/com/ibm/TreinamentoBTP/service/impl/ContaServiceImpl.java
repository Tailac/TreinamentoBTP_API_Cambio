package com.ibm.TreinamentoBTP.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoBTP.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoBTP.model.Conta;
import com.ibm.TreinamentoBTP.repository.ContaRepository;
import com.ibm.TreinamentoBTP.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService{
	
	private ContaRepository contaRepository;
	
    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

	@Override
	public Conta buscarConta(Integer numConta) {
		Optional<Conta> optionalConta = contaRepository.findBynumConta(numConta);
        return optionalConta.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Não foi possivel localizar a conta de id " + numConta));
	}

	@Override
	public Conta criarConta(Conta conta) {
		return contaRepository.save(conta);
	}

	@Override
	public Conta atualizarConta(Conta conta) {
		if (conta == null || conta.getId() == null)
            throw new RuntimeException("Conta não encontrado");
        if (!contaRepository.existsById(conta.getId()))
            throw new RuntimeException();
        return contaRepository.save(conta);
	}

	@Override
	public boolean deletarConta(Long id) {
        if (id == null)
            return false;
        if (!contaRepository.existsById(id))
            return false;
        contaRepository.deleteById(id);
        return true;
	}




}
