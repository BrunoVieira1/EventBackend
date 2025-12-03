package EventFlow.eventflow.dto.request;

import EventFlow.eventflow.enums.TipoIngresso;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngressoRequestDTO {
    @NotNull
    private Integer eventoId;

    @NotNull
    private Integer participanteId;

    @NotNull
    private TipoIngresso tipo;

    @NotNull
    @Positive
    private BigDecimal valorPago;
}