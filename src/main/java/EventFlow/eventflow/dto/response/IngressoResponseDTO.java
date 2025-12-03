package EventFlow.eventflow.dto.response;

import EventFlow.eventflow.enums.TipoIngresso;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IngressoResponseDTO {
    private Integer id;
    private LocalDateTime dataCompra;
    private BigDecimal valorPago;
    private TipoIngresso tipo;
    private String nomeEvento;
    private String nomeParticipante;
}