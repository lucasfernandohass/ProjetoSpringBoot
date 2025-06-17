package com.esoft.LojaDeJogos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esoft.LojaDeJogos.models.Jogo;

@Repository
public interface  JogoRepository extends JpaRepository<Jogo, Long>{

}