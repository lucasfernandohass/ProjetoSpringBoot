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

import com.esoft.LojaDeJogos.DTOs.DistribuidoraDTO;
import com.esoft.LojaDeJogos.Services.DistribuidoraService;

@RestController
@RequestMapping("/distribuidoras")
public class DistribuidoraController {

    @Autowired
    private DistribuidoraService distribuidoraService;

    @GetMapping
    public List<DistribuidoraDTO> listar() {
        return distribuidoraService.listar();
    }

    @PostMapping
    public DistribuidoraDTO criar(@RequestBody DistribuidoraDTO distribuidora) {
        return distribuidoraService.salvar(distribuidora);
    }

    @GetMapping("/{id}")
    public DistribuidoraDTO buscarPorId(@PathVariable Long id) {
        return distribuidoraService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DistribuidoraDTO atualizar(@PathVariable Long id, @RequestBody DistribuidoraDTO distribuidora) {
        return distribuidoraService.atualizar(id, distribuidora);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        distribuidoraService.remover(id);
    }
}
