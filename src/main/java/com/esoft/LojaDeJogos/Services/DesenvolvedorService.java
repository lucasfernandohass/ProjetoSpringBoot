package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.DesenvolvedorDTO;
import com.esoft.LojaDeJogos.Exceptions.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Desenvolvedor;
import com.esoft.LojaDeJogos.repositories.DesenvolvedorRepository;

import jakarta.transaction.Transactional;

@Service
public class DesenvolvedorService {

    public final DesenvolvedorRepository desenvolvedorRepository;

    public DesenvolvedorService(DesenvolvedorRepository desenvolvedorRepository){
        this.desenvolvedorRepository = desenvolvedorRepository;
    }

    @Transactional
    public DesenvolvedorDTO salvar(DesenvolvedorDTO desenvolvedorDTO) {
        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.setNome(desenvolvedorDTO.nome());

        return new DesenvolvedorDTO(desenvolvedorRepository.save(desenvolvedor));
    }

    @Transactional
    public DesenvolvedorDTO atualizar(Long id, DesenvolvedorDTO desenvolvedorDTO) {
        Desenvolvedor existente = desenvolvedorRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Desenvolvedor com id " + id + " não encontrado."));

        existente.setNome(desenvolvedorDTO.nome());
        return new DesenvolvedorDTO(desenvolvedorRepository.save(existente));
    }
    

    public List<DesenvolvedorDTO> listar(){
        return desenvolvedorRepository.findAll().stream().map(DesenvolvedorDTO::new).toList();
    }

    public DesenvolvedorDTO buscarPorId(Long id){
        return new DesenvolvedorDTO(desenvolvedorRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Desenvolvedor com id" +id+ " não encontrado.")));
    }

    @Transactional
    public void remover(Long id){
        @SuppressWarnings("unused")
        Desenvolvedor desenvolvedor = desenvolvedorRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Desenvolvedor com id" +id+" não encontrado."));
        desenvolvedorRepository.deleteById(id);
    }
}