package Primeiro_Projeto_Banco.First.Entidades.Conta;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaDTO(

        @NotNull(message = "Necessário realizar um depósito para abertura de conta")
        Double saldo,

        @NotNull(message = "Necessário selecionar qual conta quer abrir")
        Integer tipoConta, // 1 para CORRENTE, 2 para POUPANCA

        @NotNull(message = "ID do cliente é obrigatório para vincular a conta")
        Long idCliente

) {}
