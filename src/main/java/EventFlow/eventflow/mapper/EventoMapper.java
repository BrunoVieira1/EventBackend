package EventFlow.eventflow.mapper;

import EventFlow.eventflow.dto.request.EventoRequestDTO;
import EventFlow.eventflow.dto.response.EventoResponseDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.entity.Organizador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {OrganizadorMapper.class, LocalMapper.class})
public interface EventoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizador", source = "organizadorEntidade")
    @Mapping(target = "local", source = "localEntidade")
    Evento toEntity(EventoRequestDTO dto, Organizador organizadorEntidade, Local localEntidade);


    EventoResponseDTO toResponseDTO(Evento entity);

    default List<EventoResponseDTO> toResponseDTOList(List<Evento> lista) {
        return lista.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}