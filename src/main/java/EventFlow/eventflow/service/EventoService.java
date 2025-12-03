package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.EventoRequestDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.entity.Organizador;
import EventFlow.eventflow.enums.StatusEvento;
import EventFlow.eventflow.mapper.EventoMapper;
import EventFlow.eventflow.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private EventoMapper mapper;

    @Autowired
    private OrganizadorService organizadorService;

    @Autowired
    private LocalService localService;

    public Evento criar(EventoRequestDTO dto) {

        Organizador organizador = organizadorService.findById(dto.getOrganizadorId());
        Local local = localService.findById(dto.getLocalId());

        if (dto.getStatus() == null) {
            dto.setStatus(StatusEvento.ABERTO);
        }

        Evento evento = mapper.toEntity(dto, organizador, local);

        return repository.save(evento);
    }

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public Evento findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado! ID: " + id));
    }

    public Evento update(Integer id, EventoRequestDTO dto) {
        Evento eventoAntigo = findById(id);

        Organizador organizador = organizadorService.findById(dto.getOrganizadorId());
        Local local = localService.findById(dto.getLocalId());

        Evento eventoNovo = mapper.toEntity(dto, organizador, local);

        eventoNovo.setId(id);


        return repository.save(eventoNovo);
    }

    public void delete(Integer id) {
        Evento evento = findById(id);
        repository.delete(evento);
    }
}