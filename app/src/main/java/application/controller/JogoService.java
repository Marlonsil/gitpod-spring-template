package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Jogo;
import com.example.demo.repository.JogoRepository;

@Service
public class JogoService {
    
    @Autowired
    private JogoRepository jogoRepository;
    
    public List<Jogo> findAll() {
        return jogoRepository.findAll();
    }
    
    public Optional<Jogo> findById(Long id) {
        return jogoRepository.findById(id);
    }
    
    public Jogo save(Jogo jogo) {
        return jogoRepository.save(jogo);
    }
    
    public void deleteById(Long id) {
        jogoRepository.deleteById(id);
    }
}

