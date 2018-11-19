package com.ibm.TreinamentoBTP.controller;

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
import com.ibm.TreinamentoBTP.service.ContaService;
import com.ibm.TreinamentoBTP.exception.*;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	
	private ContaService contaService;

	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
    @RequestMapping(value = "/{numeroConta}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaTodasContas(@PathVariable Integer numConta) {
    	 try {
             Conta conta = contaService.buscarConta(numConta);
             return ResponseEntity.ok(new Resposta(0, "", conta));
         } catch (ObjetoNaoEncontradoException e) {
 	        return ResponseEntity.badRequest().body(new Resposta(e.getCode(), e.getMessage(), null));
         }
    }
    
    @RequestMapping(value = "/novo",method = RequestMethod.POST)
    public ResponseEntity<Object> novoConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(contaService.criarConta(conta));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(contaService.atualizarConta(conta));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @RequestMapping(value = "/delete/{numConta}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteConta(@PathVariable Long id) {
        try {
            if(contaService.deletarConta(id)) {
            	return ResponseEntity.ok("Contato removido com sucesso!");
            }else {
            	return ResponseEntity.ok("Contato nao encontrado!");
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(value = "/deposito", method = RequestMethod.PUT)
    public ResponseEntity<Object> depositar(@RequestBody Conta conta, Double valor, Double taxaCambio) {
        try {
            return ResponseEntity.ok(contaService.depositar(conta, valor, taxaCambio));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    

}
