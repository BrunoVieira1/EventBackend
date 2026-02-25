package EventFlow.eventflow.entity;

import EventFlow.eventflow.enums.StatusEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "tb_evento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEvento status = StatusEvento.ABERTO;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
}