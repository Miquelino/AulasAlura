package Primeiro_Projeto_Banco.First.Entidades.Cliente;

import Primeiro_Projeto_Banco.First.Entidades.Conta.*;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ClienteRepository;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public Cliente cadastrarClienteComConta(ClienteDTO dadosCliente, ContaDTO dadosConta) {
        Cliente cliente = new Cliente();

        // Gera número sequencial da conta
        Integer maxNumero = contaRepository.findMaxNumero();
        int novoNumero = (maxNumero != null ? maxNumero + 1 : 1001);

        // Cria cliente + conta
        cliente.abrirConta(dadosCliente, novoNumero);

        // Salva tudo (cascade = ALL vai salvar a conta junto)
        clienteRepository.save(cliente);

        return cliente;
    }


}

