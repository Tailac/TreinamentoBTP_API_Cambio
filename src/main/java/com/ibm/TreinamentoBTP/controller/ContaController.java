package com.ibm.TreinamentoBTP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.TreinamentoBTP.model.Conta;
import com.ibm.TreinamentoBTP.model.Correntista;
import com.ibm.TreinamentoBTP.service.ContaService;
import com.ibm.TreinamentoBTP.exception.*;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	//TODO: VALIDAR INCLUIR UMA CONTA COM UM CORRENTISTA QUE NAO EXISTE (VALIDAR ALL O CRUSO)
	private ContaService contaService;

	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaContas(@PathVariable Long id) {
    	 try {
             Conta conta = contaService.buscarConta(id);
             return ResponseEntity.ok(new Resposta(0, "", conta));
         } catch (ObjetoNaoEncontradoException e) {
 	        return ResponseEntity.badRequest().body(new Resposta(e.getCode(), e.getMessage(), null));
         }
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> buscarTodosConta(@RequestParam(value = "filtro", required = false) Integer filtro) {
    	List<Conta> contas = contaService.buscarTodosConta(filtro);
    	return ResponseEntity.ok(new Resposta(0, "", contas));
    }
    
    @RequestMapping(value = "/novo",method = RequestMethod.POST)
    public ResponseEntity<Object> novoConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(contaService.criarConta(conta));
            
        } catch (ObjetoExistenteException oe) {
            return ResponseEntity.badRequest().body(new Resposta(oe.getCode(),oe.getMessage(), null));
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(contaService.atualizarConta(conta));
        } catch (ObjetoNaoEncontradoException re) {
            return ResponseEntity.badRequest().body(new Resposta(re.getCode(),re.getMessage(),null));
        }
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteConta(@PathVariable Long id) {
        try {
            if(contaService.deletarConta(id)) {
            	return ResponseEntity.ok("Contato removido com sucesso!");
            }else {
            	return ResponseEntity.badRequest().body(new Resposta(1,"Conta nao encontrada",null ));
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @RequestMapping(value = "/deposito", method = RequestMethod.PUT)
    public ResponseEntity<Object> depositar(@RequestParam(value="numConta")Integer numConta, @RequestParam(value = "valor") Double valor,  @RequestParam(value="taxaCambio")Double taxaCambio) {
        try {
            return ResponseEntity.ok(contaService.depositar(numConta, valor, taxaCambio));
        } catch (ObjetoNaoEncontradoException re) {
            return ResponseEntity.badRequest().body(new Resposta(re.getCode(), re.getMessage(),null));
        }
    }
    
    @RequestMapping(value = "/saque", method = RequestMethod.PUT)
    public ResponseEntity<Object> sacar(@RequestParam(value="numConta")Integer numConta, @RequestParam(value = "valor") Double valor,  @RequestParam(value="taxaCambio")Double taxaCambio) {
        try {
            return ResponseEntity.ok(contaService.sacar(numConta, valor, taxaCambio));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(new Resposta(1,"Saldo insuficiente", null));        }
    }
    

    
    @RequestMapping(value = "/simulacaoCambio", method = RequestMethod.PUT)
    public ResponseEntity<Object> simularCambio(@RequestParam(value="valor")Double valor, @RequestParam(value = "taxaCambio") Double taxaCambio) {
        try {
            return ResponseEntity.ok(contaService.ConsultarCambio(taxaCambio, valor));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(new Resposta(4, "Não foi possivel fazer a simulação",null));
        }
    }
    

}
