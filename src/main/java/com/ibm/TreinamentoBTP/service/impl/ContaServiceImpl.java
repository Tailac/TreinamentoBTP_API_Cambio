package com.ibm.TreinamentoBTP.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoBTP.exception.InternalException;
import com.ibm.TreinamentoBTP.exception.ObjetoExistenteException;
import com.ibm.TreinamentoBTP.model.Cambio;
import com.ibm.TreinamentoBTP.model.Conta;
import com.ibm.TreinamentoBTP.model.Correntista;
import com.ibm.TreinamentoBTP.repository.ContaRepository;
import com.ibm.TreinamentoBTP.repository.CorrentistaRepository;
import com.ibm.TreinamentoBTP.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService{
	
	private ContaRepository contaRepository;
	private CorrentistaRepository correnstistaRepositoy;
	
    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository, CorrentistaRepository correnstistaRepositoy) {
        this.contaRepository = contaRepository;
        this.correnstistaRepositoy = correnstistaRepositoy;
    }

	@Override 
	public Conta buscarConta(Long id) {
		Optional<Conta> optionalConta = contaRepository.findById(id);
        return optionalConta.orElseThrow(() ->
                new InternalException("Não foi possivel localizar a conta de id " + id));
	}
	
	@Override
	public List<Conta> buscarTodosConta(Integer filtro) {
		if (filtro != null)
			return contaRepository.findAllByNumContaContains(filtro);
		return contaRepository.findAll();
	}

	@Override
	public Conta criarConta(Conta conta) {
		if(conta.getCorrentista()== null)
			throw new ObjetoExistenteException("Correntista não vinculado a conta");
		Optional<Correntista> retCor = correnstistaRepositoy.findById(conta.getCorrentista().getId());
		if(retCor.isPresent()) {
			Optional<Conta> ret = contaRepository.findBynumConta(conta.getNumConta());
			 if(ret.isPresent()) {
				 throw new ObjetoExistenteException("Conta já existente");
			 }else {
				 return contaRepository.save(conta);
			 }
		}else {
			throw new ObjetoExistenteException("Correntista não existente");
		}

	}

	@Override
	public Conta atualizarConta(Conta conta) {
		if (conta == null || conta.getId()==null || conta.getNumConta() == null)
            throw new InternalException("Conta não encontrado, verifique se passou o ID");
		Optional<Conta> retNumConta = contaRepository.findBynumConta(conta.getNumConta());
		Optional<Conta> retID = contaRepository.findById(conta.getId());
		if(retNumConta.isPresent() & retID.isPresent()) {
			try {
				return contaRepository.save(conta);
			}catch (Exception e) {
				throw new InternalException("Erro ao atualziar conta, verifique informações da Conta");
			}
			
		}else {
			throw new InternalException("Conta não encontrada, verifique o numero da Conta e o ID");
		}
		
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
	public Conta depositar(Integer numConta, Double valor, Double taxaCambio) {
		if(numConta == null) {
			throw new InternalException("Conta não encontrado");
		}
		Optional<Conta> retNumConta = contaRepository.findBynumConta(numConta);
		if(retNumConta.isPresent()) {
			try {
				Optional<Conta> conta = contaRepository.findBynumConta(numConta);
				conta.get().setSaldo(conta.get().getSaldo() + (valor * taxaCambio));
				return contaRepository.save(conta.get());
			}catch (Exception e) {
				throw new InternalException("Erro ao realizar o Depósito, verifique informações da Conta");
			}
		}else {
			throw new InternalException("Conta não encontrada, verifique as informacoes da conta");
		}
		
	}

	@Override
	public Conta sacar(Integer numConta, Double valor, Double taxaCambio) {
		
		valor *= taxaCambio;
		if (numConta == null)
			throw new InternalException("Conta nao encontrada");
		Optional<Conta> retNumConta = contaRepository.findBynumConta(numConta);
		if(retNumConta.isPresent()) {
			try {
				Optional<Conta> conta = contaRepository.findBynumConta(numConta);
				if (conta.get().getSaldo() < valor)
					throw new InternalException("Saldo insuficiente. Seu saldo e: " + conta.get().getSaldo());
				conta.get().setSaldo(conta.get().getSaldo() - valor);
				return contaRepository.save(conta.get());
			}catch (Exception e) {
				throw new InternalException("Erro ao realizar o Depósito, verifique informações da Conta");
			}
		}else {
			throw new InternalException("Conta não encontrada, verifique as informacoes da conta");
		}
	}


	@Override
	public Cambio ConsultarCambio(Double taxaCambio, Double valor) {
		Cambio cambio =  new Cambio();
		cambio.setTaxaCabmio(taxaCambio);
		cambio.setValorTroca(valor);
		cambio.setValorConvertido(valor * taxaCambio);
		return cambio;
	}

}
