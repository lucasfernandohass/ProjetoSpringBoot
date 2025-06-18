package com.esoft.LojaDeJogos.models;

import java.util.List;

import com.esoft.LojaDeJogos.DTOs.DesenvolvedorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_desenvolvedor")
public class Desenvolvedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "desenvolvedor")
    private List<Jogo> jogos;

    public Desenvolvedor(DesenvolvedorDTO desenvolvedor){
        this.id = desenvolvedor.id();
        this.nome = desenvolvedor.nome();
    }
}

