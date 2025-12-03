package EventFlow.eventflow.mapper;

import EventFlow.eventflow.dto.request.IngressoRequestDTO;
import EventFlow.eventflow.dto.response.IngressoResponseDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Ingresso;
import EventFlow.eventflow.entity.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IngressoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", source = "eventoEntidade")
    @Mapping(target = "participante", source = "participanteEntidade")
    @Mapping(target = "dataCompra", expression = "java(java.time.LocalDateTime.now())")
    Ingresso toEntity(IngressoRequestDTO dto, Evento eventoEntidade, Participante participanteEntidade);

    default IngressoResponseDTO toResponseDTO(Ingresso entity) {
        if (entity == null) return null;
        IngressoResponseDTO dto = new IngressoResponseDTO();
        dto.setId(entity.getId());
        dto.setDataCompra(entity.getDataCompra());
        dto.setValorPago(entity.getValorPago());
        dto.setTipo(entity.getTipo());

        if (entity.getEvento() != null) {
            dto.setNomeEvento(entity.getEvento().getTitulo());
        }
        if (entity.getParticipante() != null) {
            dto.setNomeParticipante(entity.getParticipante().getNome());
        }
        return dto;
    }

    default List<IngressoResponseDTO> toResponseDTOList(List<Ingresso> lista) {
        return lista.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}