package com.esoft.LojaDeJogos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.LojaDeJogos.DTOs.CategoriaDTO;
import com.esoft.LojaDeJogos.Exceptions.NaoEncontradoException;
import com.esoft.LojaDeJogos.models.Categoria;
import com.esoft.LojaDeJogos.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    public final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaDTO salvar(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria(categoriaDTO);
        return new CategoriaDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDTO){
        Categoria existente = categoriaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Categoria com id " + id +"não encotrada."));
        existente.setNome(categoriaDTO.nome());
        existente.setDescricao(categoriaDTO.descricao());

        return new CategoriaDTO(categoriaRepository.save(existente));
    }

    public List<CategoriaDTO> listar(){
        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).toList();
    }

    public CategoriaDTO buscarPorId(Long id){
        return new CategoriaDTO(categoriaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Categoria com id" +id+ " não encontrada.")));
    }

    @Transactional
    public void remover(Long id){
        @SuppressWarnings("unused")
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Categoria com id" +id+" não encontrada."));
        categoriaRepository.deleteById(id);
    }
}
