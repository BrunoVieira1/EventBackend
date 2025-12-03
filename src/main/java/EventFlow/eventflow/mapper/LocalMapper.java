package EventFlow.eventflow.mapper;

import EventFlow.eventflow.dto.request.LocalRequestDTO;
import EventFlow.eventflow.dto.response.LocalResponseDTO;
import EventFlow.eventflow.entity.Local;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LocalMapper {

    @Mapping(target = "id", ignore = true)
    Local toEntity(LocalRequestDTO dto);

    default LocalResponseDTO toResponseDTO(Local entity) {
        if (entity == null) return null;
        LocalResponseDTO dto = new LocalResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());
        dto.setCapacidadeMaxima(entity.getCapacidadeMaxima());
        return dto;
    }

    default List<LocalResponseDTO> toResponseDTOList(List<Local> lista) {
        return lista.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}