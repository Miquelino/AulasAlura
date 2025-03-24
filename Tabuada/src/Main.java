import Entities.TabuadaMultiplicacao;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        TabuadaMultiplicacao dados = new TabuadaMultiplicacao();

        System.out.println("Digite um número para saber a tabuada: ");
        int n = leitura.nextInt();
        dados.mostrarTabuada(n);
    }
}