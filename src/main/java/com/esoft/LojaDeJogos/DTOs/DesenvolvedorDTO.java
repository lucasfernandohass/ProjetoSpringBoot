package com.esoft.LojaDeJogos.DTOs;

import java.util.List;
import java.util.stream.Collectors;

import com.esoft.LojaDeJogos.models.Desenvolvedor;
import com.esoft.LojaDeJogos.models.Jogo;

public record DesenvolvedorDTO(
    Long id,
    String nome,
    List<JogoDTO> jogos
) {
    public DesenvolvedorDTO(Desenvolvedor desenvolvedor) {
        this(
            desenvolvedor.getId(),
            desenvolvedor.getNome(),
            desenvolvedor.getJogos() != null ? 
                desenvolvedor.getJogos().stream()
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