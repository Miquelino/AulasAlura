package Entities;

public class Livro implements Calculavel{


    @Override
    public double calcularPrecoFinal(double preco) {
        double precoFinal;
        double desconto;
        desconto = preco * (10/100);
        return precoFinal = preco - desconto;
    }
}
