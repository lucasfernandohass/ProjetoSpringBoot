package com.esoft.LojaDeJogos.models;

import java.util.List;

import com.esoft.LojaDeJogos.DTOs.JogoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Desenvolvedor desenvolvedor;

    @Column(nullable = false)
    private Distribuidora distribuidora;

    private List<Categoria> categorias;

    public Jogo(JogoDTO jogo){
        this.id = jogo.id();
        this.nome = jogo.nome();
        this.desenvolvedor = jogo.desenvolvedor();
        this.distribuidora = jogo.distribuidora();
    }
}
