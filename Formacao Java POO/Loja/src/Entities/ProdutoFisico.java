package Entities;

public class ProdutoFisico implements Calculavel{


    @Override
    public double calcularPrecoFinal(double preco) {
        double taxa;
        return taxa = (preco*10) + preco;
    }
}
