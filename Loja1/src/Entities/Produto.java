package Entities;

public class Produto implements Vendavel{

    private String nome;
    private double precoUnitario;


    @Override
    public double precoTotal(int quantidade) {
        return precoUnitario * quantidade;
    }

    @Override
    public void aplicarDesconto(double porcentualDesconto) {
        precoUnitario -= precoUnitario * (porcentualDesconto / 100);
        System.out.println("preço unitario: " + precoUnitario);
    }
}
