package com.ts.mpdecoracoes.exceptions;

public class CredenciaisWrongException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CredenciaisWrongException(String msg) {
		super(msg);
	}
    
}
