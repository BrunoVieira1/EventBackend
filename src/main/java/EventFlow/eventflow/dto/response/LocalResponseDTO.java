package EventFlow.eventflow.dto.response;

import lombok.Data;

@Data
public class LocalResponseDTO {
    private Integer id;
    private String nome;
    private String endereco;
    private Integer capacidadeMaxima;
}