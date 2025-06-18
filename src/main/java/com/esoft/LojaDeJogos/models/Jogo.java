package com.esoft.LojaDeJogos.models;

import java.util.List;

import com.esoft.LojaDeJogos.DTOs.JogoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "desenvolvedor_id")
    private Desenvolvedor desenvolvedor;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "jogo_categorias",
        joinColumns = @JoinColumn(name = "jogo_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;


    public Jogo(JogoDTO jogo){
        this.id = jogo.id();
        this.nome = jogo.nome();
    }

}
