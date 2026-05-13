package Primeiro_Projeto_Banco.First.Entidades.Conta;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente cliente, double saldo, double limite) {
        super(cliente, saldo);
        this.limite = limite;
    }

    public Double usarChequeEspecial(double valor) {
        // TODO: implementar lógica de uso do cheque especial
        return 750.0;

    }
}

