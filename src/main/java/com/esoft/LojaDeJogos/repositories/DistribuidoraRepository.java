package com.esoft.LojaDeJogos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esoft.LojaDeJogos.models.Distribuidora;

@Repository
public interface DistribuidoraRepository extends JpaRepository<Distribuidora, Long> {

}