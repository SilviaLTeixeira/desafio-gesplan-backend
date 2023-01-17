package com.example.desafiogesplan.controller;


import com.example.desafiogesplan.dto.TelefoneDTO;
import com.example.desafiogesplan.model.Telefone;
import com.example.desafiogesplan.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato/{idContato}/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService service;

    @GetMapping
    public ResponseEntity<List<Telefone>> get(
            @PathVariable(name = "idContato") Long idContato
    ) {

        List<Telefone> telefones = service.listar(idContato);
        if (telefones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(telefones);
    }

    @PutMapping(path = "/{idTelefone}")
    public ResponseEntity<Long> put(
            @PathVariable(name = "idContato") Long idContato,
            @PathVariable(name = "idTelefone") Long idTelefone,
            @RequestBody TelefoneDTO telefone
    ){
        Long idRetornado = service.editar(idTelefone,telefone);
        return ResponseEntity.ok(idRetornado);
    }
    @PostMapping
    public ResponseEntity<Long> post(
            @PathVariable(name = "idContato") Long idContato,
            @RequestBody TelefoneDTO telefone
    ){
        Long id = service.salvar(telefone,idContato);
        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }
    @DeleteMapping("/{idTelefone}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "idContato") Long idContato,
            @PathVariable(name = "idTelefone") Long idTelefone
    ){
        service.deletar(idTelefone,idContato);
        return ResponseEntity.noContent().build();
    }
}
