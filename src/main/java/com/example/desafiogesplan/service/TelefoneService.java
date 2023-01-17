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
    public List<Telefone> listar(Long idContato) {
        Contato contato = contatoRepository.findById(idContato).orElseThrow(() ->
                new EntityNotFoundException("Contato não encontrado!"));
        return telefoneRepository.findByContatoId(idContato);
    }

    @Transactional
    public Long editar(Long idTelefone,TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new EntityNotFoundException("Telefone não encontrado!"));
        Contato contato = contatoRepository.findById(telefoneDTO.getContatoId()).orElseThrow(() ->
                new EntityNotFoundException("Não existe contato com o id: " + telefoneDTO.getContatoId()));
        validateDto(telefoneDTO);
        telefone.setTelefone(telefoneDTO.getTelefone());
        telefone.setContato(contato);

        return idTelefone;
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
                () -> new EntityNotFoundException("Não existe contato com o id " + idContato)
        );

        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(
                () -> new EntityNotFoundException("Não existe telefone com o id " + idTelefone)
        );

        if (!idContato.equals(telefone.getContato().getId())) {
            throw new IllegalArgumentException("O id do Contato passado não é o id do Contato desse telefone");
        }
        telefoneRepository.delete(telefone);
    }

    private Telefone validateAndConvertDto(TelefoneDTO telefoneDTO){
        existsTelefone(telefoneDTO);
        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneDTO.getTelefone());
        return telefone;
    }
    private void validateDto( TelefoneDTO telefoneDTO){
        existsTelefone(telefoneDTO);
    }

    private void existsTelefone(TelefoneDTO telefoneDTO) {
        if (telefoneDTO.getTelefone() == null) {
            throw new RequiredFieldMissingException("O campo telefone é obrigatório.");
        }
    }
}
