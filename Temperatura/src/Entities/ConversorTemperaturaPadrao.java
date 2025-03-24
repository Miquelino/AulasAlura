package Entities;

public class ConversorTemperaturaPadrao implements ConversorTemperatura{

    @Override
    public void celsiusParaFahrenheit(double c) {
        double F = c * 1.8 + 32;
        System.out.println("A conversao de Celsius para Fahrenheit é: " + F);
    }

    @Override
    public void fahrenheitParaCelsius(double f) {
        double C = (f - 32) * 5/9 ;
        System.out.println("A conversao de Fahrenheit para Celsius é: " + C);
    }
}
