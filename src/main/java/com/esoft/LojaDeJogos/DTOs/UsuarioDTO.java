package com.esoft.LojaDeJogos.DTOs;

import java.util.List;
import java.util.stream.Collectors;

import com.esoft.LojaDeJogos.models.Jogo;
import com.esoft.LojaDeJogos.models.Usuario;

public record UsuarioDTO(
    Long id,
    String nome,
    List<JogoDTO> jogos
) {
    public UsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getJogos() != null ? 
                usuario.getJogos().stream()
                    .map(JogoDTO::new)
                    .collect(Collectors.toList()) 
                : null
        );
    }
    
    /*
        MESMA LOGICA DO JOGO PARA PODER FORNECER O NOME DO JOGO E O ID DELE NA REQUISIÇÃO GET
    */

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