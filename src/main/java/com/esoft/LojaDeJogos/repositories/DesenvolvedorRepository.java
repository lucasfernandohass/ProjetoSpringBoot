package com.esoft.LojaDeJogos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esoft.LojaDeJogos.models.Desenvolvedor;

@Repository
public interface  DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long>{

}