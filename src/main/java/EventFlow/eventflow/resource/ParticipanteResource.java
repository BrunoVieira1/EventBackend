package EventFlow.eventflow.resource;

import EventFlow.eventflow.dto.request.ParticipanteRequestDTO;
import EventFlow.eventflow.dto.response.ParticipanteResponseDTO;
import EventFlow.eventflow.entity.Participante;
import EventFlow.eventflow.mapper.ParticipanteMapper;
import EventFlow.eventflow.service.ParticipanteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/participantes")
@Tag(name = "Participante")
public class ParticipanteResource {

    @Autowired
    private ParticipanteService service;

    @Autowired
    private ParticipanteMapper mapper;

    @PostMapping
    public ResponseEntity<ParticipanteResponseDTO> criar(@Valid @RequestBody ParticipanteRequestDTO dto) {
        Participante participante = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(participante));
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteResponseDTO>> findAll() {
        List<Participante> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toResponseDTOList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipanteResponseDTO> findById(@PathVariable Integer id) {
        Participante participante = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDTO(participante));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ParticipanteResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ParticipanteRequestDTO dto) {
        Participante participante = service.update(id, dto);
        return ResponseEntity.ok().body(mapper.toResponseDTO(participante));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}