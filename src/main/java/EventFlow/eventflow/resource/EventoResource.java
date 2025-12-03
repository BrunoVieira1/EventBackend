package EventFlow.eventflow.resource;

import EventFlow.eventflow.dto.request.EventoRequestDTO;
import EventFlow.eventflow.dto.response.EventoResponseDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.mapper.EventoMapper;
import EventFlow.eventflow.service.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/eventos")
@Tag(name = "Evento")
public class EventoResource {

    @Autowired
    private EventoService service;

    @Autowired
    private EventoMapper mapper;

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(@Valid @RequestBody EventoRequestDTO dto) {
        Evento evento = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(evento));
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> findAll() {
        List<Evento> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toResponseDTOList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoResponseDTO> findById(@PathVariable Integer id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDTO(evento));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody EventoRequestDTO dto) {
        Evento evento = service.update(id, dto);
        return ResponseEntity.ok().body(mapper.toResponseDTO(evento));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}