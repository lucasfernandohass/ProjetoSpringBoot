package com.esoft.LojaDeJogos.DTOs;

import java.util.List;
import java.util.stream.Collectors;

import com.esoft.LojaDeJogos.models.Distribuidora;
import com.esoft.LojaDeJogos.models.Jogo;

public record DistribuidoraDTO(
    Long id,
    String nome,
    List<JogoDTO> jogos
) {
    public DistribuidoraDTO(Distribuidora distribuidora) {
        this(
            distribuidora.getId(),
            distribuidora.getNome(),
            distribuidora.getJogos() != null ? 
                distribuidora.getJogos().stream()
                    .map(JogoDTO::new)
                    .collect(Collectors.toList()) 
                : null
        );
    }
    
    public record JogoDTO(
        Long id,
        String nome
    ) {
        public JogoDTO(Jogo jogo) {
            this(
                jogo.getId(),
                jogo.getNome()
            );
        }
    }
}