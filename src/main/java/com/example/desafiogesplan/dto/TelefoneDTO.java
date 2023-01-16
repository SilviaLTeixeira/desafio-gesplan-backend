package com.example.desafiogesplan.dto;

import org.jetbrains.annotations.NotNull;

public class TelefoneDTO {
  private Long id;

  @NotNull
  private Long contatoId;
  @NotNull
  private String telefone;

  public String getTelefone(){
    return telefone;
  }
  public void setTelefone(String telefone){
     this.telefone = telefone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getContatoId() {
    return contatoId;
  }

  public void setContatoId(Long contatoId) {
    this.contatoId = contatoId;
  }

}
