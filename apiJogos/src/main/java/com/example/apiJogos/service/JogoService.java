package com.example.apiJogos.service;

import com.example.apiJogos.dao.JogoRepository;
import com.example.apiJogos.model.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public ResponseEntity<List<Jogo>> getAllJogos() {
        try {
            return new ResponseEntity<>(jogoRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Jogo> getJogo(Integer id) {
        try{
            Jogo jogoBuscado = jogoRepository.findById(id).get();
            return new ResponseEntity<>(jogoBuscado, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    public ResponseEntity<Jogo> addJogo(Jogo jogo) {
        try{
            jogoRepository.save(jogo);
            return new ResponseEntity<>(jogo, HttpStatus.CREATED);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Jogo> atualizarJogo(Integer id, Jogo jogo) {
        try {

            if(jogo.getNome() ==  null ||  jogo.getTipo() ==  null || jogo.getNota() ==  null || jogo.getReview() ==  null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            Jogo jogoAtualizar = jogoRepository.findById(id).get();
            jogoAtualizar.setNome(jogo.getNome());
            jogoAtualizar.setTipo(jogo.getTipo());
            jogoAtualizar.setNota(jogo.getNota());
            jogoAtualizar.setReview(jogo.getReview());

            jogoRepository.save(jogoAtualizar);

            return new ResponseEntity<>(jogoAtualizar, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> deletarJogo(Integer id) {
        try {
            Jogo jogoDeletar =  jogoRepository.findById(id).get();
            jogoRepository.delete(jogoDeletar);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
