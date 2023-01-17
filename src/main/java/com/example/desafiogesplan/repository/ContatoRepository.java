package com.example.desafiogesplan.repository;

import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

}
