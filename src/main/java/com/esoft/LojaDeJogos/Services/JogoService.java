package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.JogoDTO;
import com.esoft.LojaDeJogos.Exceptions.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Categoria;
import com.esoft.LojaDeJogos.models.Desenvolvedor;
import com.esoft.LojaDeJogos.models.Distribuidora;
import com.esoft.LojaDeJogos.models.Jogo;
import com.esoft.LojaDeJogos.repositories.CategoriaRepository;
import com.esoft.LojaDeJogos.repositories.DesenvolvedorRepository;
import com.esoft.LojaDeJogos.repositories.DistribuidoraRepository;
import com.esoft.LojaDeJogos.repositories.JogoRepository;

import jakarta.transaction.Transactional;

@Service
public class JogoService {

    public final JogoRepository jogoRepository;
    public final DesenvolvedorRepository desenvolvedorRepository;
    public final DistribuidoraRepository distribuidoraRepository;
    public final CategoriaRepository categoriaRepository;

    public JogoService(JogoRepository jogoRepository, DesenvolvedorRepository desenvolvedorRepository, DistribuidoraRepository distribuidoraRepository, CategoriaRepository categoriaRepository){
        this.jogoRepository = jogoRepository;
        this.desenvolvedorRepository = desenvolvedorRepository;
        this.distribuidoraRepository = distribuidoraRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public JogoDTO salvar(JogoDTO jogoDTO) {
        Jogo jogo = new Jogo();
        jogo.setNome(jogoDTO.nome());
        
        // Tratar relacionamentos
        tratarRelacionamentos(jogo, jogoDTO);
        
        Jogo jogoSalvo = jogoRepository.save(jogo);
        return new JogoDTO(jogoSalvo);
    }
    
    private void tratarRelacionamentos(Jogo jogo, JogoDTO jogoDTO) {
        if (jogoDTO.desenvolvedorId() != null) {
            Desenvolvedor desenvolvedor = desenvolvedorRepository.findById(jogoDTO.desenvolvedorId())
                .orElseThrow(() -> new NaoEncontradoException("Desenvolvedor não encontrado"));
            jogo.setDesenvolvedor(desenvolvedor);
        }
        
        if (jogoDTO.distribuidoraId() != null) {
            Distribuidora distribuidora = distribuidoraRepository.findById(jogoDTO.distribuidoraId())
                .orElseThrow(() -> new NaoEncontradoException("Distribuidora não encontrada"));
            jogo.setDistribuidora(distribuidora);
        }
        
        if (jogoDTO.categoriasIds() != null && !jogoDTO.categoriasIds().isEmpty()) {
            List<Categoria> categorias = categoriaRepository.findAllById(jogoDTO.categoriasIds());
            if (categorias.size() != jogoDTO.categoriasIds().size()) {
                throw new NaoEncontradoException("Uma ou mais categorias não encontradas");
            }
            jogo.setCategorias(categorias);
        }
    }

    @Transactional
    public JogoDTO atualizar(Long id, JogoDTO jogoDTO) {
        Jogo jogoExistente = jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo com id " + id + " não encontrado."));

        // Atualiza apenas os campos permitidos
        if (jogoDTO.nome() != null) {
            jogoExistente.setNome(jogoDTO.nome());
        }

        // Atualiza desenvolvedor (se fornecido no DTO)
        if (jogoDTO.desenvolvedorId() != null) {
            Desenvolvedor desenvolvedor = desenvolvedorRepository.findById(jogoDTO.desenvolvedorId())
                .orElseThrow(() -> new NaoEncontradoException("Desenvolvedor não encontrado"));
            jogoExistente.setDesenvolvedor(desenvolvedor);
        } else {
            jogoExistente.setDesenvolvedor(null); // Remove o relacionamento se ID for null
        }

        // Atualiza distribuidora (se fornecido no DTO)
        if (jogoDTO.distribuidoraId() != null) {
            Distribuidora distribuidora = distribuidoraRepository.findById(jogoDTO.distribuidoraId())
                .orElseThrow(() -> new NaoEncontradoException("Distribuidora não encontrada"));
            jogoExistente.setDistribuidora(distribuidora);
        } else {
            jogoExistente.setDistribuidora(null); // Remove o relacionamento se ID for null
        }

        // Atualiza categorias (se fornecido no DTO)
        if (jogoDTO.categoriasIds() != null) {
            if (jogoDTO.categoriasIds().isEmpty()) {
                jogoExistente.getCategorias().clear(); // Remove todas as categorias
            } else {
                List<Categoria> categorias = categoriaRepository.findAllById(jogoDTO.categoriasIds());
                
                if (categorias.size() != jogoDTO.categoriasIds().size()) {
                    throw new NaoEncontradoException("Uma ou mais categorias não foram encontradas");
                }
                
                jogoExistente.getCategorias().clear();
                jogoExistente.getCategorias().addAll(categorias);
            }
        }

        Jogo jogoAtualizado = jogoRepository.save(jogoExistente);
        return new JogoDTO(jogoAtualizado);
    }

    

    public List<JogoDTO> listar(){
        return jogoRepository.findAll().stream().map(JogoDTO::new).toList();
    }

    public JogoDTO buscarPorId(Long id) {
        Jogo jogo = jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo não encontrado"));
        return new JogoDTO(jogo);
    }

    @Transactional
    public void remover(Long id){
        @SuppressWarnings("unused")
        Jogo jogo = jogoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Jogo com id" +id+" não encontrada."));
        jogoRepository.deleteById(id);
    }
}
