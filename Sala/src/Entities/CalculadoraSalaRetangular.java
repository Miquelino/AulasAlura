package Entities;

public class CalculadoraSalaRetangular implements CalculoGeometrico{
    double PI = 3.1416;


    @Override
    public void calcularArea(double largura, double altura) {
        double area = altura * largura;
        System.out.println("A area é: " + area);
    }

    @Override
    public void calcularPerimetro(double largura, double altura) {
        double perimetro = 2  * (largura + altura);
        System.out.println("O perimetro é: " + perimetro);

    }
}
