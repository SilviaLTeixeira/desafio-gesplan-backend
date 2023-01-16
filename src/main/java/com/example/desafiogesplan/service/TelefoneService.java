package com.example.desafiogesplan.service;

import com.example.desafiogesplan.dto.TelefoneDTO;
import com.example.desafiogesplan.model.Contato;
import com.example.desafiogesplan.model.Telefone;
import com.example.desafiogesplan.repository.ContatoRepository;
import com.example.desafiogesplan.repository.TelefoneRepository;
import com.example.desafiogesplan.service.exception.RequiredFieldMissingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    @Transactional
    public List<Telefone> listar(String nome, Long idContato) {
        Contato contato = contatoRepository.findById(idContato).orElseThrow(() ->
                new EntityNotFoundException("Contato não encontrado!"));
        return telefoneRepository.findAll();
    }

    @Transactional
    public Telefone listarPorId(Long idTelefone, Long idContato) {

        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new EntityNotFoundException("Telefone não encontrado!"));

        Contato contato = contatoRepository.findById(idContato).orElseThrow(() ->
                new EntityNotFoundException("Contato não encontrado!"));

        if (!idContato.equals(telefone.getContato().getId())){
            throw new IllegalArgumentException("O ID do contato não coincide com o ID especificado!");
        }

        return telefone;

    }

    @Transactional
    public Long salvar(TelefoneDTO telefoneDTO, Long idContato){
        Contato contato = contatoRepository.findById(idContato).orElseThrow(
                () -> new EntityNotFoundException("Não existe contato com o id " + idContato)
        );
        Telefone telefone = validateAndConvertDto(telefoneDTO);
        telefone.setContato(contato);
        telefone = telefoneRepository.save(telefone);
        return telefone.getId();
    }

    @Transactional
    public void deletar(Long idTelefone, Long idContato) {
        contatoRepository.findById(idContato).orElseThrow(
                () -> new EntityNotFoundException("Não existe estado com o id " + idContato)
        );

        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(
                () -> new EntityNotFoundException("Não existe cidade com o id " + idTelefone)
        );

        if (!idContato.equals(telefone.getContato().getId())) {
            throw new IllegalArgumentException("O id do Contato passado não é o id do Contato desse telefone");
        }
        telefoneRepository.delete(telefone);
    }

    private Telefone validateAndConvertDto(TelefoneDTO telefoneDTO){
        existsNome(telefoneDTO);
        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneDTO.getTelefone());
        return telefone;
    }
    private void existsNome(TelefoneDTO telefoneDTO) {
        if (telefoneDTO.getTelefone() == null) {
            throw new RequiredFieldMissingException("O campo nome é obrigatório.");
        }
    }
}
