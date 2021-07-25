package com.ts.mpdecoracoes.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFoundException(String msg) {
		super(msg);
	}
    
}
