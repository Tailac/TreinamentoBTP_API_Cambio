package com.ibm.TreinamentoBTP.model;

import javax.persistence.Entity;

import com.ibm.TreinamentoBTP.enumerator.TipoMoeda;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cambio {
	private Double taxaCabmio;
	private Double valorTroca;
	private Double valorConvertido;

}
