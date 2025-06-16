package com.esoft.LojaDeJogos.DTOs;

import com.esoft.LojaDeJogos.models.Desenvolvedor;

public record DesenvolvedorDTO(
    Long id,
    String nome//,
    //List<Jogo> jogos
    ) {

    public DesenvolvedorDTO(Desenvolvedor desenvolvedor){
        this(
            desenvolvedor.getId(),
            desenvolvedor.getNome()
        );
    }
}