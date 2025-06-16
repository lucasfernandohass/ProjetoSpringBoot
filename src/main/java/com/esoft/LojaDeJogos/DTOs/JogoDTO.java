package com.esoft.LojaDeJogos.DTOs;

import java.util.List;

import com.esoft.LojaDeJogos.models.Categoria;
import com.esoft.LojaDeJogos.models.Desenvolvedor;
import com.esoft.LojaDeJogos.models.Distribuidora;
import com.esoft.LojaDeJogos.models.Jogo;

public record JogoDTO (
    Long id,
    String nome,
    Distribuidora distribuidora,
    Desenvolvedor desenvolvedor,
    List<Long> categoriaIds
    ){
    
    public JogoDTO(Jogo jogo){
        this(
            jogo.getId(),
            jogo.getNome(),
            jogo.getDistribuidora(),
            jogo.getDesenvolvedor(),
            jogo.getCategorias() != null ? jogo.getCategorias().stream().map(Categoria::getId).toList() : null
            );
    }
}
