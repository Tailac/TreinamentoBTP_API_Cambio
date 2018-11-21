package com.ibm.TreinamentoBTP.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.ibm.TreinamentoBTP.enumerator.TipoMoeda;

import lombok.Data;

@Data
//@Entity
public class TaxaCambio {
	@Id
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoMoeda tipoMoeda;
	
	private Double taxaConversao;
}
//API DE CAMBIO
//https://fixer.io/documentation