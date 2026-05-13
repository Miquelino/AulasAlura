package Primeiro_Projeto_Banco.First.Entidades.Conta;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import Primeiro_Projeto_Banco.First.Entidades.Repositorios.ContaRepository;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Conta")
@Table(name = "contas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(unique = true)
    private Integer numero;

    private double saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipoConta;

    public Conta(Cliente cliente, double saldo) {
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    public boolean sacar(double valor){
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void abrirConta(ContaDTO dadosConta, Cliente cliente){
        this.cliente = cliente;
        this.saldo = dadosConta.saldo();
        this.tipoConta = TipoConta.fromCodigo(dadosConta.tipoConta());
    }
}

