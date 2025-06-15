package com.esoft.LojaDeJogos.DTOs;

import com.esoft.LojaDeJogos.models.Categoria;

public record CategoriaDTO(
    Long id,
    String nome,
    String descricao
    ){
    
    public CategoriaDTO(Categoria categoria){
        this(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao()
        );
    }
}
