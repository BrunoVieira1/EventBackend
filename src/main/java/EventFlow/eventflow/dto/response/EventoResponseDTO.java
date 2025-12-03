package EventFlow.eventflow.dto.response;

import EventFlow.eventflow.enums.StatusEvento;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoResponseDTO {
    private Integer id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private StatusEvento status;
    private OrganizadorResponseDTO organizador;
    private LocalResponseDTO local;
}