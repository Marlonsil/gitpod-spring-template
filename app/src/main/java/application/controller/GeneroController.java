package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    @GetMapping
    public List<Genero> findAll() {
        return generoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Genero> findById(@PathVariable Long id) {
        Optional<Genero> genero = generoService.findById(id);
        if (genero.isPresent()) {
            return ResponseEntity.ok(genero.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Genero> save(@RequestBody Genero genero) {
        Genero savedGenero = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenero);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @RequestBody Genero genero) {
        if (!generoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        genero.setId(id);
        Genero updatedGenero = generoService.save(genero);
        return ResponseEntity.ok(updatedGenero);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!generoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        generoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
