package checkpoint.carreira.ackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        Integer idade
) {
}
