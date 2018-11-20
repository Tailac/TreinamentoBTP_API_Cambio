package com.ibm.TreinamentoBTP.exception;

public class ObjetoExistenteException extends RuntimeException {
	
    private Integer code = 2;

    public ObjetoExistenteException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

}
