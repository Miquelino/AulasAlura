package Entities;

public class Carro {

    private String modelo;
    private int ano;
    private String cor;

    public Carro(String modelo, int ano, String cor) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public int Ano(){
        return 2025 - this.ano ;
    }

    public String InfoCarro(){
        return "Modelo: " + this.modelo + " ano: " + this.ano + " cor: " + this.cor + " idade do carro: " + this.Ano() + " anos.";
    }

}
