package com.esoft.LojaDeJogos.DTOs;

import java.util.List;

import com.esoft.LojaDeJogos.models.Jogo;
import com.esoft.LojaDeJogos.models.Usuario;

public record UsuarioDTO(
    Long id,
    String nome,
    List<Long> jogosIds
) {
    public UsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getJogos() != null ? 
                usuario.getJogos().stream().map(Jogo::getId).toList() : 
                null
        );
    }
}