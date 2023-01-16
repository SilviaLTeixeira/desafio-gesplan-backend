package com.example.desafiogesplan.service;


import com.example.desafiogesplan.dto.ContatoDTO;
import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.repository.ContatoRepository;
import com.example.desafiogesplan.repository.TelefoneRepository;
import com.example.desafiogesplan.service.exception.RequiredFieldMissingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    @Transactional
    public List<Contato> listar(String nome){

        return contatoRepository.findAll();
    }

    @Transactional
    public Contato listarPorId(Long id){
        return contatoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Não existe contato com o id " + id)
        );
    }

    @Transactional
    public Long salvar(ContatoDTO contatoDTO){
        Contato contato = validateAndConvertDto(contatoDTO);
        contato = contatoRepository.save(contato);
        return contato.getId();
    }

    @Transactional
    public void deletar(Long id){
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe contato com o id: " + id));
        List<Contato> contatos = contatoRepository.findAll();
        contatoRepository.delete(contato);
    }

    private Contato validateAndConvertDto(ContatoDTO contatoDTO){
        existsNome(contatoDTO);
        existsSobrenome(contatoDTO);
        existsEmail(contatoDTO);
        Contato contato = new Contato();
        contato.setNome(contatoDTO.getNome());
        contato.setSobrenome(contatoDTO.getSobrenome());
        contato.setEmail(contatoDTO.getEmail());
        return contato;
    }

    private void existsNome(ContatoDTO contatoDTO){
        if(contatoDTO.getNome() == null){
            throw new RequiredFieldMissingException("O campo nome é obrigatório.");
        }
    }

    private void existsSobrenome(ContatoDTO contatoDTO){
        if(contatoDTO.getSobrenome() == null){
            throw new RequiredFieldMissingException("O campo sobrenome é obrigatório.");
        }
    }

    private void existsEmail(ContatoDTO contatoDTO){
        if(contatoDTO.getEmail() == null){
            throw new RequiredFieldMissingException("O campo email é obrigatório.");
        }
    }

}
