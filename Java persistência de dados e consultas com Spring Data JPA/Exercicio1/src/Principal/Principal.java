package Principal;

import Model.CodigoErro;
import Model.Exercicios;
import Model.Mes;
import Model.Moeda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);

    public void exibirMenu() {
        boolean continuar = true;

        Exercicios exercicio = new Exercicios();

        while (continuar) {
            System.out.println("Escolha o exercício no menu abaixo.");

            var menu = """
                    1, 2, 3, 4, 5, 6, 7, 8
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

                    System.out.println(Exercicios.processaNumero(Optional.of(5))); // Saída: Optional[25]
                    System.out.println(Exercicios.processaNumero(Optional.of(-3))); // Saída: Optional.empty
                    System.out.println(Exercicios.processaNumero(Optional.empty())); // Saída: Optional.empty


                    break;
                case 3:
                    System.out.println("3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. " +
                            "O método deve retornar o primeiro e o último nome após remover os espaços desnecessários.");

                    System.out.println(Exercicios.obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
                    System.out.println(Exercicios.obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"

                    break;
                case 4:
                    System.out.println("4 - Implemente um método que verifica se uma frase é um palíndromo. " +
                            "Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é igual à leitura normal. Um exemplo:");

                    System.out.println(Exercicios.ehPalindromo("subi no onibus em marrocos")); // Saída: true
                    System.out.println(Exercicios.ehPalindromo("Java")); // Saída: false
                    System.out.println(Exercicios.ehPalindromo("Ovo")); // Saída: false

                    break;
                case 5:
                    System.out.println("5 - Implemente um método que recebe uma lista de e-mails (String) " +
                            "e retorna uma nova lista onde cada e-mail está convertido para letras minúsculas.");

                    List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
                    System.out.println(exercicio.converterEmails(emails));
                    // Saída: ["teste@exemplo.com", "exemplo@java.com", "usuario@teste.com"]

                    break;
                case 6:
                    System.out.println("6 - Crie um enum Mes que represente os meses do ano. " +
                            "Adicione um método que retorna o número de dias de cada mês, considerando anos não bissextos.");

                    System.out.println("Digite o ano:");
                    int ano = leitura.nextInt();
                    System.out.println("Digite o número do mês (1 a 12):");
                    int numeroMes = leitura.nextInt();

                    if (numeroMes >= 1 && numeroMes <= 12) {
                        Mes mesEscolhido = Mes.values()[numeroMes - 1];
                        int dias = mesEscolhido.getNumeroDeDias(ano);
                        System.out.println("O mês de " + mesEscolhido + " no ano " + ano + " tem " + dias + " dias.");
                    } else {
                        System.out.println("Número de mês inválido!");
                    }

                    break;
                case 7:
                    System.out.println("7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. " +
                            "Cada moeda deve ter uma taxa de conversão para reais. " +
                            "Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.");

                    System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
                    System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)

                    break;
                case 8:
                    System.out.println("8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR. " +
                            "Cada valor deve ter um código numérico e uma descrição associados. " +
                            "Adicione métodos para acessar o código e a descrição. " +
                            "Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.");

                    System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
                    System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida

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

}
