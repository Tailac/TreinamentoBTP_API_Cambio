package com.ibm.TreinamentoBTP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Object> buscaTodasContas(@PathVariable Long id) {
    	 try {
             Conta conta = contaService.buscarConta(id);
             return ResponseEntity.ok(new Resposta(0, "", conta));
         } catch (ObjetoNaoEcontratoException e) {
 	        return ResponseEntity.badRequest().body(new Resposta(e.getCode(), e.getMessage(), null));
         }
    }
	
	
	
	
	

}
