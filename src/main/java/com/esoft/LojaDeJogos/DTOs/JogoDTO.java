package com.esoft.LojaDeJogos.DTOs;

import java.util.List;
import java.util.stream.Collectors;

import com.esoft.LojaDeJogos.models.Categoria;
import com.esoft.LojaDeJogos.models.Jogo;

public record JogoDTO(
    Long id,  // Nullable para criação (POST)
    String nome,
    Long desenvolvedorId,
    Long distribuidoraId,
    List<Long> categoriasIds,
    
    // Campos opcionais para leitura (GET)
    String desenvolvedorNome,
    String distribuidoraNome,
    List<CategoriaDTO> categorias
) {
    // Construtor para criação (POST)
    public JogoDTO(String nome, Long desenvolvedorId, Long distribuidoraId, List<Long> categoriasIds) {
        this(null, nome, desenvolvedorId, distribuidoraId, categoriasIds, null, null, null);
    }
    
    // Construtor para leitura (GET) a partir da entidade
    public JogoDTO(Jogo jogo) {
        this(
            jogo.getId(),
            jogo.getNome(),
            jogo.getDesenvolvedor() != null ? jogo.getDesenvolvedor().getId() : null,
            jogo.getDistribuidora() != null ? jogo.getDistribuidora().getId() : null,
            jogo.getCategorias() != null ? 
                jogo.getCategorias().stream().map(Categoria::getId).collect(Collectors.toList()) : null,
            jogo.getDesenvolvedor() != null ? jogo.getDesenvolvedor().getNome() : null,
            jogo.getDistribuidora() != null ? jogo.getDistribuidora().getNome() : null,
            jogo.getCategorias() != null ? 
                jogo.getCategorias().stream().map(CategoriaDTO::new).collect(Collectors.toList()) : null
        );
    }
    
    public record CategoriaDTO(Long id, String nome) {
        public CategoriaDTO(Categoria categoria) {
            this(categoria.getId(), categoria.getNome());
        }
    }
}