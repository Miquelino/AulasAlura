package Entities;

public class TabuadaMultiplicacao implements  Tabuadas{


    @Override
    public void mostrarTabuada(int numero) {
        for (int i = 0; i <= 10; i++){
            double resultado = numero * i;
            System.out.println(numero + " * " + i + " = " + resultado);
        }
    }
}
