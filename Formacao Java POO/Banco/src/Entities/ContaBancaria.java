package Entities;

public class ContaBancaria {

    protected double saldo;

    public void depositar(double depositar){
        this.saldo += depositar;
        System.out.println("Depósito de " + depositar + " realizado. Saldo atual: " + saldo);
    }

    public void sacar(double valor){
        if (valor <= this.saldo){
            this.saldo -= valor;
            System.out.println("Saque de " + valor + " realizado. Saldo atual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public void consultarSaldo(){
        System.out.println("Saldo: " + saldo);
    }

}