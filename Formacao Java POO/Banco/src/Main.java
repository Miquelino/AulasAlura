import Entities.ContaCorrente;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ContaCorrente conta = new ContaCorrente();
        conta.depositar(100);
        conta.cobrarTarifaMensal(5);
        conta.consultarSaldo();
        conta.sacar(50);
        conta.consultarSaldo();


    }
}