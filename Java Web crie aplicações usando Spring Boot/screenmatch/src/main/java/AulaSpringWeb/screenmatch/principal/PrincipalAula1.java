package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.*;
import AulaSpringWeb.screenmatch.repository.serieRepository;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;
import AulaSpringWeb.screenmatch.service.MyMemoryTranslate;

import java.util.*;
import java.util.stream.Collectors;

public class PrincipalAula1 {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private final String ENDERECO = "https://api.themoviedb.org/3/search/tv?";
    private final String ENDERECO2 = "https://api.themoviedb.org/3/tv/";
    private final String ENDERECO_GENEROS = "https://api.themoviedb.org/3/genre/tv/list?"; // Endpoint for genres
    private final String API_KEY = "api_key=adf7087c139b48d3eef7202f56ae8279";

    private List<Serie> dadosSeries = new ArrayList<Serie>();
    private Map<Integer, String> nomeGeneros = new HashMap<>(); // Store genre names

    private serieRepository repositorio;

    public PrincipalAula1(serieRepository repositorio){
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    0 - Sair
                    """;

            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;

                case 2:
                    buscarEpisodioPorSerie();
                    break;

                case 3:
                    listarSeriesBuscadas();
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

    private void buscarSerieWeb() {
        Serie dados = getSerieCompleta();
        if (dados != null) {
            dadosSeries.add(dados);
            System.out.println(dados);

        }
    }

    private void buscarEpisodioPorSerie() {
        Serie dadosSerie = getSerieCompleta();
        if (dadosSerie != null) {
            List<DadosTemporadas> temporadas = new ArrayList<>();
            for (int i = 1; i <= dadosSerie.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO2 + dadosSerie.getTotalTemporadas() + "/season/" + i + "?" + API_KEY + "&language=pt-BR");
                DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
                if (dadosTemporada != null) {
                    temporadas.add(dadosTemporada);
                }
            }
            temporadas.forEach(System.out::println);
        }
    }

    public Serie getSerieCompleta() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + API_KEY + "&query=" + nomeSerie.replace(" ", "+"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        if (resultado.getResults().isEmpty()) {
            System.out.println("Nenhum resultado encontrado!");
            return null;
        }

        DadosSerie dados = resultado.getResults().get(0);

        // Recupera o número de temporadas
        var detalhesJson = consumo.obterDados(ENDERECO2 + dados.id() + "?" + API_KEY + "&language=pt-BR");
        var detalhesTemporada = conversor.obterDados(detalhesJson, DadosTemporadas.class);

        Serie serie = new Serie(dados);  // Aqui você está passando o objeto 'dados' do tipo DadosSerie, o que é correto.


        if (detalhesTemporada != null) {
            serie.setTotalTemporadas(detalhesTemporada.totalTemporadas());
        }

        try {
            String overview = serie.getSinopse();
            if (overview != null && !overview.isBlank()) {
                String traducao = MyMemoryTranslate.obterTraducao(overview);
                serie.setSinopse(traducao);
            }
            repositorio.save(serie);
            return serie;
        } catch (Exception e) {
            System.err.println("Erro ao processar série: " + e.getMessage());
            return null;
        }
        //return serie;

    }

    private void listarSeriesBuscadas() {
        List<Serie> seriesDoBanco = repositorio.findAllWithGeneros();

//                dadosSeries.stream()
//                .map(serie -> {
//                    try {
//                        String overview = serie.getSinopse();
//                        if (overview != null && !overview.isBlank()) {
//                            String traducao = MyMemoryTranslate.obterTraducao(overview);
//                            serie.setSinopse(traducao);
//                        }
//                        return serie;
//                    } catch (Exception e) {
//                        System.err.println("Erro ao processar série: " + e.getMessage());
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull)
//                .toList();

        if (nomeGeneros.isEmpty()) {
            fetchGenreNames();
        }

        seriesDoBanco.forEach(serie -> {
            String generosFormatados = serie.getGeneros().stream()
                    .map(id -> nomeGeneros.getOrDefault(id, "Desconhecido"))
                    .collect(Collectors.joining(", "));
            System.out.println(" Série: " + serie.getTitulo() +
                    "\n Avaliação: " + serie.getAvaliacao() +
                    "\n Gêneros: " + generosFormatados +
                    "\n Sinopse: " + serie.getSinopse() +
                    "\n");

        });
    }

    private void fetchGenreNames() {
        String json = consumo.obterDados(ENDERECO_GENEROS + API_KEY + "&language=pt-BR");
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        if (resultado == null || resultado.getResultsGenero() == null || resultado.getResultsGenero().isEmpty()) {
            System.err.println("Erro: nenhum gênero encontrado ou resposta inválida.");
            return;
        }

        // Preenche o mapa com ID e nome dos gêneros
        resultado.getResultsGenero().forEach(genero ->
                nomeGeneros.put(genero.id(), genero.nome())
        );
    }
}
