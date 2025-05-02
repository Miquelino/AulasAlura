import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        boolean continuar = true;

        while (continuar) {

            System.out.println("\nBem vindo ao Menu de opções. Digite a opção desejada: ");
            System.out.println("1. Exercicio");
            System.out.println("2. Exercicio");
            System.out.println("3. Exercicio"); // Corrigi aqui: era "1" escrito duas vezes
            System.out.println("4. Exercicio");
            System.out.println("5. Exercicio");
            System.out.println("6. Exercicio");
            System.out.println("7. Exercicio");
            System.out.println("8. Exercicio");
            System.out.println("9. Exercicio");
            System.out.println("0. Sair do programa.");
            var opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Dada a lista de números inteiros a seguir, encontre o maior número dela.");
                    List<Integer> numeros1 = Arrays.asList(10, 20, 30, 40, 50);
                    Optional<Integer> max = numeros1.stream()
                            .max(Integer::compare);
                    max.ifPresent(System.out::println);

                    break;

                case 2:
                    System.out.println("Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho. No código a seguir, " +
                            "há um exemplo prático do resultado esperado.");

                    List<String> palavras1 = Arrays.asList("java", "stream", "lambda", "code");
                    Map<Integer, List<String>> agrupamento = palavras1.stream()
                            .collect(Collectors.groupingBy(String::length));
                    System.out.println(agrupamento);

                    break;

                case 3:
                    System.out.println("Dada a lista de nomes abaixo, concatene-os separados por vírgula. " +
                            "No código a seguir, há um exemplo prático do resultado esperado.");

                    List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
                    String resultado = nomes.stream()
                            .collect(Collectors.joining(", "));
                    System.out.println(resultado);
                    break;

                case 4:
                    System.out.println("Dada a lista de números inteiros abaixo, calcule a soma dos quadrados dos números pares.");
                    List<Integer> numeros3 = Arrays.asList(1, 2, 3, 4, 5, 6);
                    int sum = numeros3.stream()
                            .filter(e -> e % 2 == 0)
                            .map(e -> e * e)
                            .reduce(0, Integer::sum);
                    System.out.println(sum);

                    break;

                case 5:
                    System.out.println("Dada uma lista de números inteiros, separe os números pares dos ímpares.");
                    List<Integer> numero5 = Arrays.asList(1, 2, 3, 4, 5, 6);
                    System.out.println("MINHA RESOLUÇÃO");
                    numero5.stream()
                            .filter(e -> e % 2 == 0)
                            .forEach(numerosPar -> System.out.println("Numeros par: " + numerosPar));
                    numero5.stream()
                            .filter(e -> e % 2 != 0)
                            .forEach(numeroImpar -> System.out.println("\nNumeros impar: " + numeroImpar));

                    System.out.println("RESOLUÇÃO DO PROFESSOR");
                    Map<Boolean, List<Integer>> particionado = numero5.stream()
                            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
                    System.out.println("Pares: " + particionado.get(true));  // Esperado: [2, 4, 6]
                    System.out.println("Ímpares: " + particionado.get(false));
                    break;

                case 6:
                    System.out.println("Dada a lista de produtos acima, agrupe-os por categoria em um Map<String, List<Produto>>.");
                    Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                            .collect(Collectors.groupingBy(Produto::getCategoria));

                    System.out.println(produtosPorCategoria);
                    break;

                case 7:
                    System.out.println("Dada a lista de produtos acima, conte quantos produtos há em cada categoria e armazene em um Map<String, Long>.");
                    Map<String, Long> contagemPorCategoria = produtos.stream()
                            .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

                    System.out.println(contagemPorCategoria);
                    break;

                case 8:
                    System.out.println("Dada a lista de produtos acima, " +
                            "encontre o produto mais caro de cada categoria e armazene o resultado em um Map<String, Optional<Produto>>.");

                    Map<String, Optional<Produto>> maisCaroPorCategoria = produtos.stream()
                            .collect(Collectors.groupingBy(Produto::getCategoria,
                                    Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));

                    System.out.println(maisCaroPorCategoria);
                    break;

                case 9:
                    System.out.println("Dada a lista de produtos acima, " +
                            "calcule o total dos preços dos produtos em cada categoria e armazene o resultado em um Map<String, Double>.");

                    Map<String, Double> somaPrecoPorCategoria = produtos.stream()
                            .collect(Collectors.groupingBy(Produto::getCategoria,
                                    Collectors.summingDouble(Produto::getPreco)));

                    System.out.println(somaPrecoPorCategoria);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        }

    }
}