import Entities.Carro;
import Entities.ModeloCarro;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Carro carro = new ModeloCarro();
        carro.definirModelo("Onix");
        carro.definirPrecos(48000, 49000, 48500);
        carro.exibirInfo();

    }
}