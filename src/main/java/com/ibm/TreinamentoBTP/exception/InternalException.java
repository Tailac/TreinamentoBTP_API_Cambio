package com.ibm.TreinamentoBTP.exception;

public class InternalException extends RuntimeException{
	
    private Integer code = 1;

    public InternalException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

}
