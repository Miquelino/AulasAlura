package Entities;

public interface Vendavel {
    double precoTotal(int quantidade);
    void aplicarDesconto(double porcentualDesconto);
}
