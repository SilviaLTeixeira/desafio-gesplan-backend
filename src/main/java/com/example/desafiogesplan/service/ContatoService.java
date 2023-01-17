package com.example.desafiogesplan.service;


import com.example.desafiogesplan.dto.ContatoDTO;
import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.repository.ContatoRepository;
import com.example.desafiogesplan.service.exception.RequiredFieldMissingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    @Transactional
    public List<Contato> listar(){

        return contatoRepository.findAll();
    }

    @Transactional
    public Contato listarPorId(Long id){
        return contatoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Não existe contato com o id " + id)
        );
    }

    @Transactional
    public Long editar(Long id,
                            ContatoDTO contatoDTO) {
        Contato contato = validateDto(id, contatoDTO);
        contato.setNome(contatoDTO.getNome());
        contato.setSobrenome(contatoDTO.getSobrenome());
        contato.setEmail(contatoDTO.getEmail());
        contato.setObservacao(contatoDTO.getObservacao());

        return id;
    }
    @Transactional
    public Long salvar(ContatoDTO contatoDTO){
        Contato contato = validateAndConvertDto(contatoDTO);
        contato = contatoRepository.save(contato);
        return contato.getId();
    }

    @Transactional
    public void deletar(List<Long> ids){
        List<Contato> contatos = new ArrayList<>();
        for(Long id: ids ){
            contatos.add(contatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe contato com o id: " + id)));
        }
        contatoRepository.deleteAllInBatch(contatos);
    }

    private Contato validateAndConvertDto(ContatoDTO contatoDTO){
        existsNome(contatoDTO);
        existsSobrenome(contatoDTO);
        existsEmail(contatoDTO);
        Contato contato = new Contato();
        contato.setNome(contatoDTO.getNome());
        contato.setSobrenome(contatoDTO.getSobrenome());
        contato.setEmail(contatoDTO.getEmail());
        contato.setObservacao(contatoDTO.getObservacao());
        return contato;
    }
    private Contato validateDto(Long id, ContatoDTO contatoDTO){
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe contato com o id: " + id));
        existsNome(contatoDTO);
        existsSobrenome(contatoDTO);
        existsEmail(contatoDTO);
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
