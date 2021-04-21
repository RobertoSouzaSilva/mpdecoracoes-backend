package com.ts.mpdecoracoes.exceptions;

public class ConteudoNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ConteudoNotFoundException(String msg){
        super(msg);
    }
}
