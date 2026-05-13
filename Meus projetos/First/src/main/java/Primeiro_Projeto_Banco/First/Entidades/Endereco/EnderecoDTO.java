package Primeiro_Projeto_Banco.First.Entidades.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotBlank(message = "Nome da rua obrigatório")
        String rua,

        @NotBlank(message = "Nome do bairro obrigatório")
        String bairro,

        @NotNull(message = "Numero da casa obrigatório")
        int numero,

        @NotNull(message = "CEP obrigatório")
        int cep,

        @NotBlank(message = "Nome da cidade obrigatório")
        String cidade,

        @NotBlank(message = "Nome do estado obrigatório")
        String estado
) {

}
