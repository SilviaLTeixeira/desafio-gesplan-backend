package com.example.desafiogesplan.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefoneger")
    @SequenceGenerator(name = "telefoneger", sequenceName = "telefones_id_seq", allocationSize = 1)
    private Long id;

    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_contato", referencedColumnName = "id")
    private Contato contato;

    public Telefone() {
    }


}
