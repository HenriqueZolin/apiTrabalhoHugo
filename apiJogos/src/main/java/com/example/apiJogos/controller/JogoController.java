package com.example.apiJogos.controller;

import com.example.apiJogos.model.Jogo;
import com.example.apiJogos.model.Usuario;
import com.example.apiJogos.service.JogoService;
import com.example.apiJogos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
        if (usuario.getEmail().equals("usuario@esoft.com") && usuario.getPassword().equals("Abc123")) {
            return new ResponseEntity<>(
                    Map.of("token", UUID.randomUUID().toString()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                Map.of("erro", "Credenciais inválidas"),
                HttpStatus.UNAUTHORIZED);
    }

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
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Integer id, @RequestBody Jogo jogo){
        return jogoService.atualizarJogo(id, jogo);
    }

    @DeleteMapping("/jogos/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Integer id){
        return jogoService.deletarJogo(id);
    }
}
