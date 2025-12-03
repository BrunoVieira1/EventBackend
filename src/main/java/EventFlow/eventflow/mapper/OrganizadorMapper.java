package EventFlow.eventflow.mapper;

import EventFlow.eventflow.dto.request.OrganizadorRequestDTO;
import EventFlow.eventflow.dto.response.OrganizadorResponseDTO;
import EventFlow.eventflow.entity.Organizador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrganizadorMapper {

    @Mapping(target = "id", ignore = true)
    Organizador toEntity(OrganizadorRequestDTO dto);

    default OrganizadorResponseDTO toResponseDTO(Organizador entity) {
        if (entity == null) return null;
        OrganizadorResponseDTO dto = new OrganizadorResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }

    default List<OrganizadorResponseDTO> toResponseDTOList(List<Organizador> lista) {
        return lista.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}