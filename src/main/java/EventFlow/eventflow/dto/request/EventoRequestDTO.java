package EventFlow.eventflow.dto.request;

import EventFlow.eventflow.enums.StatusEvento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequestDTO {
    @NotBlank
    private String titulo;

    private String descricao;

    @NotNull
    @Future
    private LocalDateTime dataHora;

    @NotNull
    private Integer organizadorId;

    @NotNull
    private Integer localId;

    private StatusEvento status;
}