package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.UsuarioDTO;
import com.esoft.LojaDeJogos.Exceptions.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Jogo;
import com.esoft.LojaDeJogos.models.Usuario;
import com.esoft.LojaDeJogos.repositories.JogoRepository;
import com.esoft.LojaDeJogos.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, JogoRepository jogoRepository){
        this.usuarioRepository = usuarioRepository;
        this.jogoRepository = jogoRepository;
    }

    
    @Transactional
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        
        if (usuarioDTO.jogosIds() != null && !usuarioDTO.jogosIds().isEmpty()) {
            List<Jogo> jogos = jogoRepository.findAllById(usuarioDTO.jogosIds());
            
            if (jogos.size() != usuarioDTO.jogosIds().size()) {
                throw new NaoEncontradoException("Um ou mais jogos não foram encontrados");
            }
            
            usuario.setJogos(jogos);
        }
        
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
        
        existente.setNome(usuarioDTO.nome());
        
        // Lógica simplificada para atualizar os jogos
        if (usuarioDTO.jogosIds() != null) {
            List<Jogo> jogos = usuarioDTO.jogosIds().isEmpty() ? 
                List.of() : // Lista vazia se não houver IDs
                jogoRepository.findAllById(usuarioDTO.jogosIds());
            
            // Verifica se todos os jogos foram encontrados (apenas se houver IDs)
            if (!usuarioDTO.jogosIds().isEmpty() && jogos.size() != usuarioDTO.jogosIds().size()) {
                throw new NaoEncontradoException("Um ou mais jogos não foram encontrados");
            }
            
            existente.setJogos(jogos);
        }
        // Se jogosIds for null, mantém a lista atual de jogos
        
        return new UsuarioDTO(usuarioRepository.save(existente));
    }


    public List<UsuarioDTO> listar(){
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO buscarPorId(Long id){
        return new UsuarioDTO(usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuário com id" +id+ " não encontrada.")));
    }

    @Transactional
    public void remover(Long id){
        @SuppressWarnings("unused")
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuario com id" +id+" não encontrada."));
        usuarioRepository.deleteById(id);
    }
}
