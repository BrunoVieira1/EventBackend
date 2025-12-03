package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.LocalRequestDTO;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.mapper.LocalMapper;
import EventFlow.eventflow.repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {

    @Autowired
    private LocalRepository repository;

    @Autowired
    private LocalMapper mapper;

    public Local criar(LocalRequestDTO dto) {
        Local local = mapper.toEntity(dto);
        return repository.save(local);
    }

    public List<Local> findAll() {
        return repository.findAll();
    }

    public Local findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado! ID: " + id));
    }

    public Local update(Integer id, LocalRequestDTO dto) {
        Local localAntigo = findById(id);

        Local localNovo = mapper.toEntity(dto);
        localNovo.setId(id);

        return repository.save(localNovo);
    }

    public void delete(Integer id) {
        Local local = findById(id);
        repository.delete(local);
    }
}