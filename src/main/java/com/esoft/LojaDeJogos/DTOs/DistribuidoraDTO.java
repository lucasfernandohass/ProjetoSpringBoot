package com.esoft.LojaDeJogos.DTOs;

import com.esoft.LojaDeJogos.models.Distribuidora;

public record DistribuidoraDTO(
        Long id,
        String nome// ,
// List<Jogo> jogos
) {

    public DistribuidoraDTO(Distribuidora distribuidora) {
        this(
                distribuidora.getId(),
                distribuidora.getNome());
    }
}