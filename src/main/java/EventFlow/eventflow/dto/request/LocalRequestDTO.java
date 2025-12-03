package EventFlow.eventflow.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocalRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Min(1)
    private Integer capacidadeMaxima;
}