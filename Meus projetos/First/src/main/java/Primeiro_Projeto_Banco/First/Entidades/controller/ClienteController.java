package Primeiro_Projeto_Banco.First.Entidades.controller;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import Primeiro_Projeto_Banco.First.Entidades.Cliente.ClienteDTO;
import Primeiro_Projeto_Banco.First.Entidades.Cliente.ClienteDetalhamentoDTO;
import Primeiro_Projeto_Banco.First.Entidades.Cliente.ClienteService;
import Primeiro_Projeto_Banco.First.Entidades.Conta.Conta;
import Primeiro_Projeto_Banco.First.Entidades.Conta.ContaDTO;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ClienteRepository;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ContaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("Banco_RM")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

/*
@ResponseBody
📍 Origem: org.springframework.web.bind.annotation.ResponseBody
✅ O que faz: Indica que o retorno do metodo do controller vai diretamente no corpo (body) da resposta HTTP —
                                        e não em uma página HTML (como seria num MVC tradicional).
Ou seja: O retorno do metodo é serializado em JSON (ou XML) e enviado ao cliente.

@Valid
📍 Origem: jakarta.validation.Valid
(ou javax.validation.Valid em versões mais antigas)
✅ O que faz: Ativa a validação automática dos campos anotados com constraints como:
@NotNull, @NotBlank, @Size, @Email e etc.
O Spring verifica esses campos antes de executar o metodo, e se algo estiver inválido, retorna automaticamente HTTP 400 (Bad Request) com a mensagem de erro.
 */

    @PostMapping("/cadastro")
    public ResponseEntity<ClienteDetalhamentoDTO> cadastrar(
            @RequestBody
            @Valid 
            ClienteDTO dadosCliente,
            ContaDTO dadosConta,
            UriComponentsBuilder uriBuilder) {

        var cliente = clienteService.cadastrarClienteComConta(dadosCliente, dadosConta);
        var uri = uriBuilder.path("/Banco_RM/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ClienteDetalhamentoDTO(cliente));
    }
}


//    @GetMapping("/{id}")
//    public ResponseEntity detalhar(@PathVariable Long id){
//        var cliente = repositoryCliente.getReferenceById(id);
//        return ResponseEntity.ok(new ClienteDetalhamentoDTO(cliente));
//    }


