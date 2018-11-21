package com.ibm.TreinamentoBTP.enumerator;

public enum TipoMoeda {
	DOLAR(3.75),
	REAL(3.75),
	YENE(3.75),
	EURO(3.75);
	
	public Double valorMoeda;
	
	TipoMoeda(Double valor) {
		valorMoeda = valor;
	}
	
	public Double getValor() {
		return valorMoeda;
	}
}
