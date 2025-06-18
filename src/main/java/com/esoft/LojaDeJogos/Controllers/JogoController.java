package com.esoft.LojaDeJogos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<JogoDTO> criar(@RequestBody JogoDTO jogoDTO) {
        JogoDTO jogoSalvo = jogoService.salvar(jogoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
    }

    @GetMapping
    public List<JogoDTO> listar() {
        return jogoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> buscarPorId(@PathVariable Long id) {
        JogoDTO jogo = jogoService.buscarPorId(id);
        return ResponseEntity.ok(jogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoDTO> atualizar(
        @PathVariable Long id,
        @RequestBody JogoDTO jogoDTO
    ) {
        JogoDTO jogoAtualizado = jogoService.atualizar(id, jogoDTO);
        return ResponseEntity.ok(jogoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        jogoService.remover(id);
    }
}
