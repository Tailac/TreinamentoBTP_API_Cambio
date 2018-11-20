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

import com.ibm.TreinamentoBTP.exception.Resposta;
import com.ibm.TreinamentoBTP.model.Correntista;
import com.ibm.TreinamentoBTP.service.CorrentistaService;

@RestController
@RequestMapping("/correntista")
public class CorrentistaController {
	
	//TODO: VALIDAR CRUD
	private CorrentistaService correntistaService;
	
	@Autowired
	public CorrentistaController(CorrentistaService correntistaService) {
		this.correntistaService = correntistaService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarCorrentista(@PathVariable Long id) {
		Correntista correntista = correntistaService.buscarCorrentista(id);
		return ResponseEntity.ok(new Resposta(0, "", correntista));
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarTodosCorrentista(@RequestParam(value = "filtro", required = false) String filtro) {
		List<Correntista> correntistas = correntistaService.buscarTodosCorrentistas(filtro);
		return ResponseEntity.ok(new Resposta(0, "", correntistas));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> novoCorrentista(@RequestBody Correntista correntista) {
		try {
			return ResponseEntity.ok(correntistaService.salvarCorrentista(correntista));
		} catch (RuntimeException re) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateCorrentista(@RequestBody Correntista correntista) {
		try {
			return ResponseEntity.ok(correntistaService.atualizarCorrentista(correntista));
		} catch (RuntimeException re) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCorrentista(@PathVariable Long id) {
		try {
			correntistaService.deletarCorrentista(id);
			return ResponseEntity.ok("Correntista removido com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
