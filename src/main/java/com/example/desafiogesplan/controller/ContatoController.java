package com.example.desafiogesplan.controller;


import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> get(
            @RequestParam(required = false) String nome

    ) {
        List<Contato> contato = service.listar(nome);
        if (contato.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contato);
    }
}
