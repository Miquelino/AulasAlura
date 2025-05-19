package Principal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha o exercício no menu abaixo.");

            var menu = """
                    1
                    2
                    3
                    4
                    5
                    6
                    7
                    8
                    0 - Sair
                    """;

            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("1 - Imagine que você tem uma lista de strings. Algumas das strings são números, mas outras não. " +
                            "Queremos converter a lista de strings para uma lista de números. Se a conversão falhar, você deve ignorar o valor. " +
                            "Por exemplo, na lista a seguir, a saída deve ser [10, 20]:");

                    List<String> input = Arrays.asList("10", "abc", "20", "30x");

                    input.stream()
                            .filter(str -> {
                                try {
                                    Integer.valueOf(str);
                                    return true;
                                } catch (NumberFormatException e){
                                    return false;
                                }
                            })
                            .forEach(numericos -> System.out.println(numericos + " "));

                    break;
                case 2:
                    System.out.println("2 - Implemente um método que recebe um número inteiro dentro de um Optional. " +
                            "Se o número estiver presente e for positivo, calcule seu quadrado. Caso contrário, retorne Optional.empty.");

                    System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
                    System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
                    System.out.println(processaNumero(Optional.empty())); // Saída: Optional.empty


                    break;
                case 3:
                    System.out.println("3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. " +
                            "O método deve retornar o primeiro e o último nome após remover os espaços desnecessários.");

                    break;
                case 4:
                    System.out.println("4 - Implemente um método que verifica se uma frase é um palíndromo. " +
                            "Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é igual à leitura normal. Um exemplo:");

                    break;
                case 5:
                    System.out.println("5 - Implemente um método que recebe uma lista de e-mails (String) " +
                            "e retorna uma nova lista onde cada e-mail está convertido para letras minúsculas.");

                    break;
                case 6:
                    System.out.println("6 - Crie um enum Mes que represente os meses do ano. " +
                            "Adicione um método que retorna o número de dias de cada mês, considerando anos não bissextos.");

                    break;
                case 7:
                    System.out.println("7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. " +
                            "Cada moeda deve ter uma taxa de conversão para reais. " +
                            "Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.");

                    break;
                case 8:
                    System.out.println("8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR. " +
                            "Cada valor deve ter um código numérico e uma descrição associados. " +
                            "Adicione métodos para acessar o código e a descrição. " +
                            "Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.");

                    break;
                case 0:
                    System.out.println("Encerrando o programa....");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção incorreta.");
                    break;

            }
        }

    }

    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        // Implementar aqui
        if (numero.isPresent()){
            if (numero < 0){
            var quadrado = numero * numero;
            System.out.println("Ao quadrado: " + quadrado);
            }
        } else {
            System.out.println("Nao é inteiro");
        }
        return numero;
    }
}
