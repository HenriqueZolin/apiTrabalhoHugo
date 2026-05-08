package com.example.apiJogos.controller;

import com.example.apiJogos.model.Jogo;
import com.example.apiJogos.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping("/jogos")
    public ResponseEntity<List<Jogo>> listarJogos(){
        return jogoService.getAllJogos();
    }

    @GetMapping("/jogos/{id}")
    public ResponseEntity<Jogo> buscarJogo(@PathVariable Integer id){
        return jogoService.getJogo(id);
    }

    @PostMapping("/jogos")
    public ResponseEntity<Jogo> salvarJogo(@RequestBody Jogo jogo){
        return jogoService.addJogo(jogo);
    }

    @PutMapping("/jogos/{id}")
    public Jogo atualizarJogo(@PathVariable Integer id, @RequestBody Jogo jogo){
        return jogoService.atualizarJogo(id, jogo);
    }

    @DeleteMapping("/jogos/{id}")
    public void deletarJogo(@PathVariable Integer id){
        jogoService.deletarJogo(id);
    }
}
