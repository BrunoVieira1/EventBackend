package EventFlow.eventflow.mapper;

import EventFlow.eventflow.dto.request.ParticipanteRequestDTO;
import EventFlow.eventflow.dto.response.ParticipanteResponseDTO;
import EventFlow.eventflow.entity.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ParticipanteMapper {

    @Mapping(target = "id", ignore = true)
    Participante toEntity(ParticipanteRequestDTO dto);

    default ParticipanteResponseDTO toResponseDTO(Participante entity) {
        if (entity == null) return null;
        ParticipanteResponseDTO dto = new ParticipanteResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    default List<ParticipanteResponseDTO> toResponseDTOList(List<Participante> lista) {
        return lista.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}