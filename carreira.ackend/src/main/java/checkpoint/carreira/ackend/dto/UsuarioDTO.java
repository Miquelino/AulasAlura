package checkpoint.carreira.ackend.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioDTO(

        @NotBlank
        @Size(min = 3, max = 100)
        String nome,

        @NotBlank
        @Email
        @Size(max = 100)
        String email,

        @NotNull
        @Min(0)
        @Max(120)
        Integer idade,

        @NotBlank
        //@CPF
        String cpf

) {}
