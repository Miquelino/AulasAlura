package Primeiro_Projeto_Banco.First.Entidades.Cliente;

import Primeiro_Projeto_Banco.First.Entidades.Conta.TipoConta;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.Query;

public record ClienteDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        //https://www.4devs.com.br/gerador_de_cpf
        @NotBlank(message = "CPF obrigatório")
        @CPF(message = "Formato CPF obrigatório")
        String cpf,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotNull(message = "Necessário selecionar qual conta quer abrir")
        Integer tipoConta, // <-- Agora é Integer

        @NotBlank(message = "Telefone para contato obrigatório")
        String telefone,

        @NotNull(message = "Deposito é obrigatório para abertura de conta")
        Double deposito

) {}
