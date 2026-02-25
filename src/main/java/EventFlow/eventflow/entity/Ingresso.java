package EventFlow.eventflow.entity;

import EventFlow.eventflow.enums.TipoIngresso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_ingresso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataCompra = LocalDateTime.now();

    @NotNull
    private BigDecimal valorPago;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoIngresso tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Participante participante;
}