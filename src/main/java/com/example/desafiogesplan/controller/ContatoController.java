package com.example.desafiogesplan.controller;


import com.example.desafiogesplan.dto.ContatoDTO;
import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> get() {
        List<Contato> contatos = service.listar();
        if (contatos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contatos);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Long> put(
            @PathVariable(name = "id") Long id,
            @RequestBody ContatoDTO contato
    ){
        Long idRetornado = service.editar(id,contato);
        return ResponseEntity.ok(idRetornado);
    }
    @PostMapping
    public ResponseEntity<Long> post(
            @RequestBody ContatoDTO contato
    ){
       Long id = service.salvar(contato);
       return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestBody List<Long> ids
    ){
        service.deletar(ids);
        return ResponseEntity.noContent().build();
    }
}
