package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.ParticipanteRequestDTO;
import EventFlow.eventflow.entity.Participante;
import EventFlow.eventflow.mapper.ParticipanteMapper;
import EventFlow.eventflow.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;

    @Autowired
    private ParticipanteMapper mapper;

    public Participante criar(ParticipanteRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um participante com este e-mail.");
        }
        if (repository.existsByDocumento(dto.getDocumento())) {
            throw new IllegalArgumentException("Já existe um participante com este documento/CPF.");
        }

        Participante participante = mapper.toEntity(dto);
        return repository.save(participante);
    }

    public List<Participante> findAll() {
        return repository.findAll();
    }

    public Participante findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participante não encontrado! ID: " + id));
    }

    public Participante update(Integer id, ParticipanteRequestDTO dto) {
        Participante participanteAntigo = findById(id);

        if (!participanteAntigo.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Este e-mail já está em uso por outro participante.");
        }

        if (!participanteAntigo.getDocumento().equals(dto.getDocumento()) && repository.existsByDocumento(dto.getDocumento())) {
            throw new IllegalArgumentException("Este documento já está em uso por outro participante.");
        }

        Participante participanteNovo = mapper.toEntity(dto);
        participanteNovo.setId(id);

        return repository.save(participanteNovo);
    }

    public void delete(Integer id) {
        Participante participante = findById(id);
        repository.delete(participante);
    }
}