package EventFlow.eventflow.resource;

import EventFlow.eventflow.dto.request.OrganizadorRequestDTO;
import EventFlow.eventflow.dto.response.OrganizadorResponseDTO;
import EventFlow.eventflow.entity.Organizador;
import EventFlow.eventflow.mapper.OrganizadorMapper;
import EventFlow.eventflow.service.OrganizadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/organizadores")
@Tag(name = "Organizador")
public class OrganizadorResource {

    @Autowired
    private OrganizadorService service;

    @Autowired
    private OrganizadorMapper mapper;

    @PostMapping
    public ResponseEntity<OrganizadorResponseDTO> criar(@Valid @RequestBody OrganizadorRequestDTO dto) {
        Organizador organizador = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(organizador));
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorResponseDTO>> findAll() {
        List<Organizador> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toResponseDTOList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganizadorResponseDTO> findById(@PathVariable Integer id) {
        Organizador organizador = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDTO(organizador));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrganizadorResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody OrganizadorRequestDTO dto) {
        Organizador organizador = service.update(id, dto);
        return ResponseEntity.ok().body(mapper.toResponseDTO(organizador));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}