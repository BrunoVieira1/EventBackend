package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.OrganizadorRequestDTO;
import EventFlow.eventflow.entity.Organizador;
import EventFlow.eventflow.mapper.OrganizadorMapper;
import EventFlow.eventflow.repository.OrganizadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository repository;

    @Autowired
    private OrganizadorMapper mapper;

    public Organizador criar(OrganizadorRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um organizador com este e-mail.");
        }

        Organizador organizador = mapper.toEntity(dto);
        return repository.save(organizador);
    }

    public List<Organizador> findAll() {
        return repository.findAll();
    }

    public Organizador findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organizador não encontrado! ID: " + id));
    }

    public Organizador update(Integer id, OrganizadorRequestDTO dto) {
        Organizador organizadorAntigo = findById(id);

        if (!organizadorAntigo.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }

        Organizador organizadorNovo = mapper.toEntity(dto);
        organizadorNovo.setId(id);

        return repository.save(organizadorNovo);
    }

    public void delete(Integer id) {
        Organizador organizador = findById(id);
        repository.delete(organizador);
    }
}