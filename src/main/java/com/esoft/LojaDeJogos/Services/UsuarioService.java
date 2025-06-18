package com.esoft.LojaDeJogos.Services;

import java.util.List;
import java.util.stream.Collectors;

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

        // Tratamento dos jogos
        if (usuarioDTO.jogos() != null && !usuarioDTO.jogos().isEmpty()) {
            List<Long> jogoIds = usuarioDTO.jogos().stream()
                .map(UsuarioDTO.JogoDTO::id)
                .collect(Collectors.toList());
            
            List<Jogo> jogos = jogoRepository.findAllById(jogoIds);
            
            // Verifica se todos os jogos foram encontrados
            if (jogos.size() != jogoIds.size()) {
                throw new NaoEncontradoException("Um ou mais jogos não foram encontrados");
            }
            
            usuario.setJogos(jogos);
        }

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuário com id " + id + " não encontrado."));

        existente.setNome(usuarioDTO.nome());

        // Tratamento dos jogos
        if (usuarioDTO.jogos() != null) {
            if (usuarioDTO.jogos().isEmpty()) {
                existente.getJogos().clear();
            } else {
                List<Long> jogoIds = usuarioDTO.jogos().stream()
                    .map(UsuarioDTO.JogoDTO::id)
                    .collect(Collectors.toList());
                
                List<Jogo> jogos = jogoRepository.findAllById(jogoIds);
                
                // Verifica se todos os jogos foram encontrados
                if (jogos.size() != jogoIds.size()) {
                    throw new NaoEncontradoException("Um ou mais jogos não foram encontrados");
                }
                
                existente.getJogos().clear();
                existente.getJogos().addAll(jogos);
            }
        }

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
