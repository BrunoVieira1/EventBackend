package EventFlow.eventflow.resource;

import EventFlow.eventflow.dto.request.LocalRequestDTO;
import EventFlow.eventflow.dto.response.LocalResponseDTO;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.mapper.LocalMapper;
import EventFlow.eventflow.service.LocalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locais")
@Tag(name = "Local")
public class LocalResource {

    @Autowired
    private LocalService service;

    @Autowired
    private LocalMapper mapper;

    @PostMapping
    public ResponseEntity<LocalResponseDTO> criar(@Valid @RequestBody LocalRequestDTO dto) {
        Local local = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(local));
    }

    @GetMapping
    public ResponseEntity<List<LocalResponseDTO>> findAll() {
        List<Local> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toResponseDTOList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocalResponseDTO> findById(@PathVariable Integer id) {
        Local local = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDTO(local));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LocalResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody LocalRequestDTO dto) {
        Local local = service.update(id, dto);
        return ResponseEntity.ok().body(mapper.toResponseDTO(local));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}