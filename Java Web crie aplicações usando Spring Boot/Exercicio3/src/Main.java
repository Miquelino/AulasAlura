import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1. Dada a lista de números inteiros abaixo, filtre apenas os números pares e imprima-os.");
            System.out.println("2. Dada a lista de strings abaixo, converta todas para letras maiúsculas e imprima-as.");
            System.out.println("3. Dada a lista de números inteiros abaixo, filtre os números ímpares, " +
                    "multiplique cada um por 2 e colete os resultados em uma nova lista.");
            System.out.println("4. Dada a lista de strings abaixo, remova as duplicatas (palavras que aparecem mais de uma vez) e imprima o resultado.");
            System.out.println("5. Dada a lista de sublistas de números inteiros abaixo, " +
                    "extraia todos os números primos em uma única lista e os ordene em ordem crescente.");
            System.out.println("6. Dado um objeto Pessoa com os campos nome e idade, f" +
                    "iltre as pessoas com mais de 18 anos, extraia os nomes e imprima-os em ordem alfabética. A classe Pessoa está definida abaixo.");
            System.out.println("7. Você tem uma lista de objetos do tipo Produto, onde cada produto possui os atributos nome (String), " +
                    "preco (double) e categoria (String). Filtre todos os produtos da categoria \"Eletrônicos\" com preço menor que R$ 1000, " +
                    "ordene-os pelo preço em ordem crescente e colete o resultado em uma nova lista.");
            System.out.println("0. Sair do programa.");
            var opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    List<Integer> numeros1 = Arrays.asList(1, 2, 3, 4, 5, 6);
                    // código para filtragem.
                    numeros1.stream()
                            .filter(numero -> numero % 2 == 0)
                            .forEach(numeroPar -> System.out.println(numeroPar + " "));
                    break;
                case 2:
                    List<String> palavras1 = Arrays.asList("java", "stream", "lambda");
                    // código para conversão
                    palavras1.stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                    System.out.println();
                    break;

                case 3:
                    List<Integer> numero2 = Arrays.asList(1, 2, 3, 4, 5, 6);
                    // código para filtragem e coleta
                    numero2.stream()
                            .filter(numero -> numero % 2 != 0)
                            .forEach(numeroImpar -> System.out.println(numeroImpar + " "));
                    break;
                case 4:
                    List<String> palavras2 = Arrays.asList("apple", "banana", "apple", "orange", "banana");
                    // código da filtragem
                    palavras2.stream()
                            .distinct()
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                    break;

                case 5:
                    List<List<Integer>> listaDeNumeros = Arrays.asList(
                            Arrays.asList(1, 2, 3, 4),
                            Arrays.asList(5, 6, 7, 8),
                            Arrays.asList(9, 10, 11, 12)
                    );

                    MetodosExercicios exercicio5 = new MetodosExercicios();

                    //código para filtrar e ordenar números primos
                    List<Integer> numerosPrimos = listaDeNumeros.stream()
                            .flatMap(List::stream)
                            .filter(MetodosExercicios::ehPrimo)
                            .sorted()
                            .collect(Collectors.toList());

                    System.out.println("Números primos encontrados: " + numerosPrimos);
                    break;

                case 6:
                    List<MetodosExercicios> pessoas = Arrays.asList(
                            new MetodosExercicios("Alice", 22),
                            new MetodosExercicios("Bob", 17),
                            new MetodosExercicios("Charlie", 19)
                    );

                    // código para filtrar pessoas
                    pessoas.stream()
                            .filter(p -> p.getIdade() > 18)
                            .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome())) // Ordena por nome
                            .forEach(pessoa -> System.out.println("Nome: " + pessoa.getNome() + ", Idade: " + pessoa.getIdade()));
                    break;

                case 7:
                    List<MetodosExercicios> produtos = Arrays.asList(
                            new MetodosExercicios("Smartphone", 800.0, "Eletrônicos"),
                            new MetodosExercicios("Notebook", 1500.0, "Eletrônicos"),
                            new MetodosExercicios("Teclado", 200.0, "Eletrônicos"),
                            new MetodosExercicios("Cadeira", 300.0, "Móveis"),
                            new MetodosExercicios("Monitor", 900.0, "Eletrônicos"),
                            new MetodosExercicios("Mesa", 700.0, "Móveis")
                    );

                    produtos.stream()
                            .filter(p -> p.getCategoria().equals("Eletrônicos"))
                            .filter(p -> p.getPreco() < 1000)
                            .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
                            .limit(3)
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Encerrando programa...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção invalida.");
                    break;
            }
        }
    }
}
