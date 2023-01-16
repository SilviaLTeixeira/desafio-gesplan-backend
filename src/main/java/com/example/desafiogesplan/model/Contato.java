package com.example.desafiogesplan.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contatoger")
    @SequenceGenerator(name = "contatoger", sequenceName = "contatos_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String observacao;

    public Contato() {
    }

    public Contato(Long id, String nome, String sobrenome, String email, String observacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.observacao = observacao;
    }
}
