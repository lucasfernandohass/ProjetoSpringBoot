package com.esoft.LojaDeJogos.Exceptions;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
