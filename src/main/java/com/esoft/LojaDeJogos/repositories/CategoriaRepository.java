package com.esoft.LojaDeJogos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esoft.LojaDeJogos.models.Categoria;

@Repository
public interface  CategoriaRepository extends JpaRepository<Categoria, Long>{

}
