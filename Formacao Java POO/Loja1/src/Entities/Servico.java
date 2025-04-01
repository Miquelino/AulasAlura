package Entities;

public class Servico implements Vendavel{

    private String descricao;
    private double precoHora;

    @Override
    public double precoTotal(int quantidade) {
        return precoHora * quantidade;
    }

    @Override
    public void aplicarDesconto(double porcentualDesconto) {
        precoHora -= precoHora * (porcentualDesconto / 100);
        System.out.println("preço da hora: " + precoHora);
    }
}
