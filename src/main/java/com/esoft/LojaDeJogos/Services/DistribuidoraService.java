package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.DistribuidoraDTO;
import com.esoft.LojaDeJogos.Expections.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Distribuidora;
import com.esoft.LojaDeJogos.repositories.DistribuidoraRepository;

import jakarta.transaction.Transactional;

@Service
public class DistribuidoraService {

    public final DistribuidoraRepository distribuidoraRepository;

    public DistribuidoraService(DistribuidoraRepository distribuidoraRepository){
        this.distribuidoraRepository = distribuidoraRepository;
    }

    @Transactional
    public DistribuidoraDTO salvar(DistribuidoraDTO distribuidoraDTO){
        Distribuidora distribuidora = new Distribuidora(distribuidoraDTO);
        return new DistribuidoraDTO(distribuidoraRepository.save(distribuidora));
    }

    @Transactional
    public DistribuidoraDTO atualizar(Long id, DistribuidoraDTO distribuidoraDTO){
        Distribuidora existente = distribuidoraRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Distribuidora com id " + id +"não encotrada."));
        existente.setNome(distribuidoraDTO.nome());

        return new DistribuidoraDTO(distribuidoraRepository.save(existente));
    }

    public List<DistribuidoraDTO> listar(){
        return distribuidoraRepository.findAll().stream().map(DistribuidoraDTO::new).toList();
    }

    public DistribuidoraDTO buscarPorId(Long id){
        return new DistribuidoraDTO(distribuidoraRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Distribuidora com id" +id+ " não encontrada.")));
    }

    @Transactional
    public void remover(Long id){
        Distribuidora distribuidora = distribuidoraRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Distribuidora com id" +id+" não encontrada."));
        distribuidoraRepository.deleteById(id);
    }
}