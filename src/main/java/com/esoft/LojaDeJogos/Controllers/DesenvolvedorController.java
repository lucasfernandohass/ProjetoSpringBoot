package com.esoft.LojaDeJogos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esoft.LojaDeJogos.DTOs.DesenvolvedorDTO;
import com.esoft.LojaDeJogos.Services.DesenvolvedorService;

@RestController
@RequestMapping("/desenvolvedores")
public class DesenvolvedorController {

    @Autowired
    private DesenvolvedorService desenvolvedorService;

    @GetMapping
    public List<DesenvolvedorDTO> listar(){
        return desenvolvedorService.listar();
    }

    @PostMapping
    public DesenvolvedorDTO criar(@RequestBody DesenvolvedorDTO desenvolvedor){
        return desenvolvedorService.salvar(desenvolvedor);
    }

    @GetMapping("/{id}")
    public DesenvolvedorDTO buscarPorId(@PathVariable Long id){
        return desenvolvedorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DesenvolvedorDTO atualizar(@PathVariable Long id, @RequestBody DesenvolvedorDTO desenvolvedor){
        return desenvolvedorService.atualizar(id, desenvolvedor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        desenvolvedorService.remover(id);
    }
}