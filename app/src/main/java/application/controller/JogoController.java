package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Jogo;
import com.example.demo.service.JogoService;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {
    
    @Autowired
    private JogoService jogoService;
    
    @GetMapping
    public List<Jogo> findAll() {
        return jogoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> findById(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoService.findById(id);
        if (jogo.isPresent()) {
            return ResponseEntity.ok(jogo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Jogo> save(@RequestBody Jogo jogo) {
        Jogo savedJogo = jogoService.save(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJogo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> update(@PathVariable Long id, @RequestBody Jogo jogo) {
        if (!jogoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jogo.setId(id);
        Jogo updatedJogo = jogoService.save(jogo);
        return ResponseEntity.ok(updatedJogo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!jogoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jogoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
