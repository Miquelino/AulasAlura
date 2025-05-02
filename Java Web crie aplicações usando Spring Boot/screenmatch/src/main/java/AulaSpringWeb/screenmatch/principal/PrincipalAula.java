package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.DadosEpisodio;
import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.DadosTemporadas;
import AulaSpringWeb.screenmatch.model.Episodio;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summarizingDouble;

public class PrincipalAula {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private String idSerie = "1396"; // ID de Breaking Bad no TMDb
    private int totalTemporadas = 0;

    private final String ENDERECO = "https://api.themoviedb.org/3/search/tv?";
    private final String ENDERECO2 = "https://api.themoviedb.org/3/tv/";
    private final String API_KEY = "api_key=adf7087c139b48d3eef7202f56ae8279";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + API_KEY + "&query=" + nomeSerie.replace(" ", "+"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        // VERIFIQUE ANTES DE ACESSAR O GET(0)
        if (resultado.getResults().isEmpty()) {
            System.out.println("Nenhum resultado encontrado!");
            return; // Sai do método se não encontrou nada
        }

        DadosSerie dados = resultado.getResults().get(0);

        var detalhesJson = consumo.obterDados(
                ENDERECO2 + dados.id() + "?" + API_KEY + "&language=pt-BR");
        var detalhesSerie = conversor.obterDados(detalhesJson, DadosTemporadas.class);
        totalTemporadas = detalhesSerie.totalTemporadas();
        boolean continuar = true;

        while (continuar) {
            // Apenas declare a lista vazia no começo:
            List<DadosTemporadas> temporadas = new ArrayList<>();

            System.out.println("\nBem vindo ao Menu de opções. Digite a opção desejada: ");
            System.out.println("1. Temporada desejada.");
            System.out.println("2. Top 5 episódios.");
            System.out.println("3. Temporada por ano."); // Corrigi aqui: era "1" escrito duas vezes
            System.out.println("0. Sair do programa.");
            var opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a temporada de " + nomeSerie + " para pesquisar os episódios: ");
                    var idTemporada = leitura.nextInt();

                    var jsonTemp = consumo.obterDados(ENDERECO2 + dados.id() + "/season/" + idTemporada + "?" + API_KEY + "&language=pt-BR");
                    DadosTemporadas dadosTemporada = conversor.obterDados(jsonTemp, DadosTemporadas.class);
                    temporadas.add(dadosTemporada);

                    // Agora que temos temporadas carregadas, podemos mostrar os episódios:
                    temporadas.forEach(t -> {
                        System.out.println("\n📅 Temporada:");
                        t.episodios().forEach(e -> System.out.println("🎬 " + e.titulo()));
                    });
                    break;

                case 2:
                    // Aqui você precisa buscar todas as temporadas antes!
                    for (int i = 1; i <= totalTemporadas; i++) {
                        var temporadaJson = consumo.obterDados(ENDERECO2 + dados.id() + "/season/" + i + "?" + API_KEY + "&language=pt-BR");
                        DadosTemporadas temp = conversor.obterDados(temporadaJson, DadosTemporadas.class);
                        temporadas.add(temp);
                    }

                    List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                            .flatMap(t -> t.episodios().stream())
                            .collect(Collectors.toList());

                    System.out.println("Top 5 episódios:");
                    dadosEpisodios.stream()
                            .filter(e -> !e.titulo().equalsIgnoreCase("N/A"))
                            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                            .limit(5)
                            .forEach(e -> System.out.println("🎬 " + e.titulo() + "\nAvaliação: " + e.avaliacao() + "\n"));

                    System.out.println("digite nome");
                    var trechoTitulo = leitura.nextLine();
                    Stream<DadosEpisodio> episodioBuscado = dadosEpisodios.stream()
                            .filter(e -> e.titulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                            .findFirst().stream();

//                    if(episodioBuscado.isPresent()){
//                        System.out.println("Episodio encontrato!");
//                        System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//                    } else {
//                        System.out.println("Episodio não encotrado!");
//                    }

                    Map<Integer, Double> avaliacoesPorTemporada = IntStream.range(0, temporadas.size())
                            .boxed()
                            .flatMap(i -> {
                                DadosTemporadas temporada = temporadas.get(i);
                                int numeroTemporada = i + 1; // O número da temporada é o índice + 1
                                return temporada.episodios().stream()
                                        .filter(episodio -> episodio.avaliacao() > 0.0)
                                        .map(episodio -> new AbstractMap.SimpleEntry<>(numeroTemporada, episodio.avaliacao()));
                            })
                            .collect(Collectors.groupingBy(Map.Entry::getKey,
                                    Collectors.averagingDouble(Map.Entry::getValue)));

                    System.out.println("Avaliações médias por temporada: " + avaliacoesPorTemporada);

                    DoubleSummaryStatistics est = dadosEpisodios.stream()
                            .filter(e -> e.avaliacao() > 0.0)
                            .collect(Collectors.summarizingDouble(DadosEpisodio::avaliacao));
                    System.out.println("\nMedia: " + est.getAverage());
                    System.out.println("Melhor episodio: "  + est.getMax());
                    System.out.println("Pior episodio: "  + est.getMin());
                    System.out.println("Quantidade: "  + est.getCount());


                    break;

                case 3:
                    // Mesma coisa: carregar as temporadas primeiro
                    for (int i = 1; i <= totalTemporadas; i++) {
                        var temporadaJson = consumo.obterDados(ENDERECO2 + dados.id() + "/season/" + i + "?" + API_KEY + "&language=pt-BR");
                        DadosTemporadas temp = conversor.obterDados(temporadaJson, DadosTemporadas.class);
                        temporadas.add(temp);
                    }

                    List<Episodio> episodios = temporadas.stream()
                            .flatMap(t -> t.episodios().stream()
                                    .map(d -> new Episodio(t.totalTemporadas(), d)))
                            .collect(Collectors.toList());

                    System.out.println("A partir de que ano você deseja ver os episódios?");
                    var ano = leitura.nextInt();
                    leitura.nextLine();

                    LocalDate dataBusca = LocalDate.of(ano, 1, 1);
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    episodios.stream()
                            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                            .forEach(e -> System.out.println(
                                    "Temporada: " + e.getNumero() +
                                            " Episódio: " + e.getTitulo() +
                                            " Data de lançamento: " + e.getDataLancamento().format(formatador)));

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
