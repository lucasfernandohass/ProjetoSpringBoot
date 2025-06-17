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

import com.esoft.LojaDeJogos.DTOs.JogoDTO;
import com.esoft.LojaDeJogos.Services.JogoService;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<JogoDTO> listar() {
        return jogoService.listar();
    }

    @PostMapping
    public JogoDTO criar(@RequestBody JogoDTO jogo) {
        return jogoService.salvar(jogo);
    }

    @GetMapping("/{id}")
    public JogoDTO buscarPorId(@PathVariable Long id) {
        return jogoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public JogoDTO atualizar(@PathVariable Long id, @RequestBody JogoDTO jogo) {
        return jogoService.atualizar(id, jogo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        jogoService.remover(id);
    }
}
