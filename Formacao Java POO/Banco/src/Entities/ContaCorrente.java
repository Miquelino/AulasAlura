package Entities;

public class ContaCorrente extends ContaBancaria{

    private double tarifaMensal;


    public void cobrarTarifaMensal(double tarifaMensal) {
        saldo -= tarifaMensal;
        System.out.println("Tarifa mensal de " + tarifaMensal + " cobrada. Saldo atual: " + saldo);
    }
}
