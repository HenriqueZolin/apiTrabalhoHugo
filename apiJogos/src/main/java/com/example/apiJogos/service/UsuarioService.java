package com.example.apiJogos.service;

import com.example.apiJogos.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UsuarioService {

    public ResponseEntity<Map<String, String>> logar(Usuario usuario) {
        if (usuario.getEmail().equals("usuario@esoft.com") && usuario.getPassword().equals("Abc123")) {
            return new ResponseEntity<>(
                    Map.of("token", UUID.randomUUID().toString()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                Map.of("erro", "Credenciais inválidas"),
                HttpStatus.UNAUTHORIZED);
    }
}
