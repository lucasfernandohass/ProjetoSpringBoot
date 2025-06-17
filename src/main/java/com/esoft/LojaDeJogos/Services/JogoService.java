package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.JogoDTO;
import com.esoft.LojaDeJogos.Expections.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Jogo;
import com.esoft.LojaDeJogos.repositories.JogoRepository;

import jakarta.transaction.Transactional;

@Service
public class JogoService {

    public final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository){
        this.jogoRepository = jogoRepository;
    }

    @Transactional
    public JogoDTO salvar(JogoDTO jogoDTO){
        Jogo jogo = new Jogo(jogoDTO);
        return new JogoDTO(jogoRepository.save(jogo));
    }

    @Transactional
    public JogoDTO atualizar(Long id, JogoDTO jogoDTO){
        Jogo existente = jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo com id " + id +"não encotrada."));
        existente.setNome(jogoDTO.nome());
        existente.setDesenvolvedor(jogoDTO.desenvolvedor());
        existente.setDistribuidora(jogoDTO.distribuidora());

        return new JogoDTO(jogoRepository.save(existente));
    }

    public List<JogoDTO> listar(){
        return jogoRepository.findAll().stream().map(JogoDTO::new).toList();
    }

    public JogoDTO buscarPorId(Long id){
        return new JogoDTO(jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo com id" +id+ " não encontrada.")));
    }

    @Transactional
    public void remover(Long id){
        Jogo jogo = jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo com id" +id+" não encontrada."));
        jogoRepository.deleteById(id);
    }
}
