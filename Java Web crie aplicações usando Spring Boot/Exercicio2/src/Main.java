// Importa as interfaces funcionais personalizadas criadas no projeto
import Interfaces.Converter;
import Interfaces.Operacao;
import Interfaces.Palindromo;
import Interfaces.Primo;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1 - Expressões lambda para operações matemáticas simples.
        // Interface 'Operacao' possui método: int calcular(int a, int b)
        Operacao soma = (a, b) -> a + b;            // Soma dois números
        Operacao subtrai = (a, b) -> a - b;         // Subtrai b de a
        Operacao multiplicacao = (a, b) -> a * b;   // Multiplica a por b
        Operacao divisao = (a, b) -> a / b;         // Divide a por b (sem verificação de divisão por zero aqui)

        // Impressão dos resultados das operações matemáticas
        System.out.println(soma.calcular(5, 3));         // 8
        System.out.println(subtrai.calcular(5, 3));      // 2
        System.out.println(multiplicacao.calcular(5, 3));// 15

        // 2 - Verifica se um número é primo usando uma lambda
        // Interface 'Primo' com método: boolean verificarPrimo(int n)
        Primo primo = n -> {
            if (n <= 1) return false; // 0 e 1 não são primos
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false; // Se for divisível por algum número menor, não é primo
            }
            return true; // Se passar por todos os testes, é primo
        };
        System.out.println(primo.verificarPrimo(11));  // true
        System.out.println(primo.verificarPrimo(12));  // false

        // 3 - Converte uma string para maiúsculas
        // Interface 'Converter' com método: String maiuscula(String s)
        Converter deixarMaiscula = nome -> nome.toUpperCase();
        System.out.println(deixarMaiscula.maiuscula("Java")); // JAVA

        // 4 - Verifica se uma string é um palíndromo
        // Interface 'Palindromo' com método: boolean verificarPalindromo(String str)
        Palindromo palindromo = str -> str.equals(new StringBuilder(str).reverse().toString());
        System.out.println(palindromo.verificarPalindromo("radar"));  // true
        System.out.println(palindromo.verificarPalindromo("java"));   // false

        // 5 - Multiplica cada número da lista por 3
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        numeros.replaceAll(n -> n * 3); // Multiplica cada elemento por 3
        System.out.println(numeros); // [3, 6, 9, 12, 15]

        // 6 - Ordena uma lista de strings em ordem alfabética
        List<String> letras = Arrays.asList("a", "c", "o", "b", "e");
        letras.sort((a, b) -> a.compareTo(b)); // Usando compareTo para ordenação alfabética
        System.out.println(letras); // [a, b, c, e, o]

        // 7 - Divisão com tratamento de exceção para divisão por zero
        Operacao divisor = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Divisão por zero");
            return a / b;
        };

        try {
            System.out.println(divisor.calcular(10, 2)); // 5
            System.out.println(divisor.calcular(10, 0)); // Exceção lançada aqui
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage()); // Exibe: "Divisão por zero"
        }
    }
}
