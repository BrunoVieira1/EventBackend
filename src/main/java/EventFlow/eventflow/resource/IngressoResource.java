package EventFlow.eventflow.resource;

import EventFlow.eventflow.dto.request.IngressoRequestDTO;
import EventFlow.eventflow.dto.response.IngressoResponseDTO;
import EventFlow.eventflow.entity.Ingresso;
import EventFlow.eventflow.mapper.IngressoMapper;
import EventFlow.eventflow.service.IngressoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ingressos")
@Tag(name = "Ingresso")
public class IngressoResource {

    @Autowired
    private IngressoService service;

    @Autowired
    private IngressoMapper mapper;

    @PostMapping
    public ResponseEntity<IngressoResponseDTO> criar(@Valid @RequestBody IngressoRequestDTO dto) {
        Ingresso ingresso = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(ingresso));
    }

    @GetMapping
    public ResponseEntity<List<IngressoResponseDTO>> findAll() {
        List<Ingresso> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toResponseDTOList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IngressoResponseDTO> findById(@PathVariable Integer id) {
        Ingresso ingresso = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDTO(ingresso));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IngressoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody IngressoRequestDTO dto) {
        Ingresso ingresso = service.update(id, dto);
        return ResponseEntity.ok().body(mapper.toResponseDTO(ingresso));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}