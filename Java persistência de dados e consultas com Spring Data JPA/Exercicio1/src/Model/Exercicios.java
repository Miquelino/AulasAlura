package Model;

import javax.security.auth.Destroyable;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercicios {

    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        // Implementar aqui
        return numero.filter(n -> n > 0).map(n -> n * n);
    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        // Implementar aqui
        // O método split() em Java é usado para dividir uma String em partes, com base em um delimitador
        //  (ou seja, um caractere ou expressão regular). Ele retorna um array de strings contendo os pedaços resultantes da divisão.
        String[] nomes = nomeCompleto.trim().split("\\s+");

        if (nomes.length == 1){
            return nomes[0];
        }
        return nomes[0] + " " + nomes[nomes.length - 1];
    }

    public static boolean ehPalindromo(String palavra) {
        // Implementar aqui
        String semEspacos = palavra.replace(" ", "").toLowerCase();
        var palindromo = new StringBuilder(semEspacos).reverse().toString();
        if (semEspacos.equalsIgnoreCase(palindromo)){
            System.out.println("É um palíndromo!");
            return true;
        } else {
            System.out.println("Não é um palíndromo.");
            return false;
        }
    }

    public List<String> converterEmails(List<String> emails) {
        // Implementar aqui
        // map() é usado para converter, modificar ou extrair informações de cada elemento.
        return emails.stream()
                .map(email -> email.trim().toLowerCase())
                .toList();

    }



}
