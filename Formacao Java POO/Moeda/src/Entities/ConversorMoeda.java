package Entities;

public class ConversorMoeda implements ConversaoFinanceira{
    private double real;
    private double cotacao = 5.50;

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    @Override
    public void converterDolarParaReal(double dolar) {
        this.real = cotacao * dolar;
        System.out.println("O valor em reais é: " + real);
    }
}
