package com.example.desafiogesplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class ContatoDTO {
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nome")
    private String nome;
    @NotNull
    @JsonProperty("sobrenome")
    private String sobrenome;
    @NotNull
    @JsonProperty("email")
    private String email;

    @JsonProperty("observacao")
    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
