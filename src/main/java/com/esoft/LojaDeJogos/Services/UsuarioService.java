package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.UsuarioDTO;
import com.esoft.LojaDeJogos.Expections.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Usuario;
import com.esoft.LojaDeJogos.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO){
        Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuário com id " + id +"não encotrada."));
        existente.setNome(usuarioDTO.nome());
        //Lista de jogos

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
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuario com id" +id+" não encontrada."));
        usuarioRepository.deleteById(id);
    }
}
