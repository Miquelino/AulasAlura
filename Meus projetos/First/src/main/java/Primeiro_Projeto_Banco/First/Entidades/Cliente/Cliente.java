package Primeiro_Projeto_Banco.First.Entidades.Cliente;

import Primeiro_Projeto_Banco.First.Entidades.Conta.*;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ContaRepository;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas = new ArrayList<>();

    @Transient
    private Double deposito;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipoConta;

    public void abrirConta(ClienteDTO dadosCliente, int numeroContaGerado) {
        this.nome = dadosCliente.nome();
        this.cpf = dadosCliente.cpf();
        this.email = dadosCliente.email();
        this.telefone = dadosCliente.telefone();
        this.deposito = dadosCliente.deposito();

        try {
            this.tipoConta = TipoConta.fromCodigo(dadosCliente.tipoConta());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de conta inválido. Use 1 para Corrente ou 2 para Poupança.");
        }

        if (dadosCliente.deposito() <= 0) {
            throw new IllegalArgumentException("Depósito inicial deve ser maior que zero para abrir conta.");
        }

        // Cria a conta conforme o tipo
        Conta novaConta;
        if (this.tipoConta == TipoConta.CORRENTE) {
            novaConta = new ContaCorrente();
        } else {
            novaConta = new ContaPoupanca();
        }

        novaConta.setNumero(numeroContaGerado);
        novaConta.setSaldo(dadosCliente.deposito());
        novaConta.setTipoConta(this.tipoConta);
        novaConta.setCliente(this);

        if (this.contas == null) {
            this.contas = new ArrayList<>();
        }
        this.contas.add(novaConta);
    }

}


