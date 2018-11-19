package com.ibm.TreinamentoBTP.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoBTP.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoBTP.model.Cambio;
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
	public Conta buscarConta(Long id) {
		Optional<Conta> optionalConta = contaRepository.findById(id);
        return optionalConta.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Não foi possivel localizar a conta de id " + id));
	}

	@Override
	public Conta criarConta(Conta conta) {
		
//		Optional<Conta> novaConta = contaRepository.findBynumConta(conta.getNumConta());
//		if(novaConta == null) {
//			return contaRepository.save(conta);
//		}else {
//			throw new ObjetoNaoEncontradoException("Conta já existente ");
//		}
		
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

	@Override
	public Conta depositar(Conta conta, Double valor, Double taxaCambio) {
		if(conta == null) {
			throw new RuntimeException("Conta não encontrado");
		}
		conta.setSaldo(conta.getSaldo() + (valor * taxaCambio));
		contaRepository.save(conta);
		return conta;
	}

	@Override
	public Conta sacar(Conta conta, Double valor, Double taxaCambio) {
		
		valor *= taxaCambio;
		System.out.println(valor);
		if (conta == null || conta.getId() == null)
			throw new ObjetoNaoEncontradoException("Conta nao encontrada");
		
		if (conta.getSaldo() < valor)
			throw new ObjetoNaoEncontradoException("Saldo insuficiente. Seu saldo e: " + conta.getSaldo());
		
		conta.setSaldo(conta.getSaldo() - valor);
		contaRepository.save(conta);
		return conta;
	}


	@Override
	public Cambio listarTaxaCambio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cambio ConsultarCambio(Double taxaCambio, Double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
