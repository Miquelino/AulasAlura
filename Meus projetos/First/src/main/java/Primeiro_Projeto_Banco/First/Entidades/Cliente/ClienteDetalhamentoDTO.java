package Primeiro_Projeto_Banco.First.Entidades.Cliente;

import Primeiro_Projeto_Banco.First.Entidades.Conta.TipoConta;

public record ClienteDetalhamentoDTO(Long id,
                                     String nome,
                                     String cpf,
                                     String email,
                                     String telefone,
                                     TipoConta conta) {

    public ClienteDetalhamentoDTO(Cliente cliente){
        this(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getTipoConta());
    }
}
