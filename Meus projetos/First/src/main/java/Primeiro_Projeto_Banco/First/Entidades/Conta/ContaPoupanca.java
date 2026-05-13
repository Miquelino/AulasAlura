package Primeiro_Projeto_Banco.First.Entidades.Conta;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ContaPoupanca extends Conta {

    private double taxaRendimento;

    public ContaPoupanca(Cliente cliente, double saldo, double taxaRendimento) {
        super(cliente, saldo);
        this.taxaRendimento = taxaRendimento;
    }

    public void renderJuros() {
        // Exemplo de cálculo simples de juros (composto mensal)
        double juros = getSaldo() * taxaRendimento;
        depositar(juros);
    }
}
