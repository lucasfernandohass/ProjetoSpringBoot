package com.esoft.LojaDeJogos.Expections;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
