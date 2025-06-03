package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.*;
import AulaSpringWeb.screenmatch.repository.serieRepository;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;
import AulaSpringWeb.screenmatch.service.MyMemoryTranslate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrincipalAula1 {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private final String ENDERECO = "https://api.themoviedb.org/3/search/tv?";
    private final String ENDERECO2 = "https://api.themoviedb.org/3/tv/";
    private final String ENDERECO_GENEROS = "https://api.themoviedb.org/3/genre/tv/list?";
    private final String API_KEY = "api_key=adf7087c139b48d3eef7202f56ae8279";

    private Map<Integer, String> nomeGeneros = new HashMap<>();

    private serieRepository repositorio;

    private List<Serie> seriesDoBanco = new ArrayList<>();

    public PrincipalAula1(serieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar serio por titulo
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

                case 4:
                    buscarSeriePorTitulo();
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
        Serie serieBuscada = getSerieCompleta();
        if (serieBuscada != null) {
            System.out.println("Série buscada e salva: " + serieBuscada.getTitulo());
        }
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome para buscar episódios: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporadas> temporadas = new ArrayList<>();

            System.out.println("Buscando episódios para: " + serieEncontrada.getTitulo());

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO2 + serieEncontrada.getId() + "/season/" + i + "?" + API_KEY + "&language=pt-BR");
                DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
                if (dadosTemporada != null) {
                    temporadas.add(dadosTemporada);
                }
            }

            List<Episodio> episodios = temporadas.stream()
                    // Adicione a verificação de nulo aqui
                    .flatMap(d -> {
                        if (d.episodios() == null) {
                            return Stream.empty(); // Retorna um stream vazio se a lista de episódios for nula
                        }
                        return d.episodios().stream();
                    })
                    .map(e -> new Episodio(e.numero(), e, serieEncontrada))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
            System.out.println("Episódios buscados e associados à série: " + serieEncontrada.getTitulo());

            episodios.forEach(System.out::println);

        } else {
            System.out.println("Série não encontrada.");
        }
    }

    public void buscarSeriePorTitulo(){
        System.out.println("Escolha uma série pelo nome para buscar episódios: ");
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBuscada.isPresent()){
            System.out.println("Dados da serie: " + serieBuscada.get());
        } else {
            System.out.println("Serie não encontrada!");
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

        var detalhesJson = consumo.obterDados(ENDERECO2 + dados.id() + "?" + API_KEY + "&language=pt-BR");
        var detalhesSerieCompleta = conversor.obterDados(detalhesJson, DadosSerie.class);

        if (nomeGeneros.isEmpty()) {
            fetchGenreNames();
        }

        String primeiroGeneroNomePortugues = "Desconhecido";
        if (dados.getGenreIds() != null && !dados.getGenreIds().isEmpty()) {
            Integer primeiroGeneroId = dados.getGenreIds().get(0);
            primeiroGeneroNomePortugues = nomeGeneros.getOrDefault(primeiroGeneroId, "Desconhecido");
        }
        Categoria categoriaDaSerie = Categoria.fromPortugues(primeiroGeneroNomePortugues);

        Serie serie = new Serie(dados, categoriaDaSerie);

        if (dados.totalTemporadas() != null) {
            serie.setTotalTemporadas(dados.totalTemporadas());
        } else if (detalhesSerieCompleta != null && detalhesSerieCompleta.totalTemporadas() != null) {
            serie.setTotalTemporadas(detalhesSerieCompleta.totalTemporadas());
        }

        List<Genero> generosDaApi = new ArrayList<>();
        if (dados.getGenreIds() != null) {
            for (Integer generoId : dados.getGenreIds()) {
                generosDaApi.add(new Genero(generoId, nomeGeneros.getOrDefault(generoId, "Desconhecido")));
            }
        }
        serie.setGeneros(generosDaApi); // CORRIGIDO: de setGenero para setGeneros

        try {
            String overview = serie.getSinopse();
            if (overview != null && !overview.isBlank()) {
                String traducao = MyMemoryTranslate.obterTraducao(overview);
                serie.setSinopse(traducao);
            }
            repositorio.save(serie);
            return serie;
        } catch (Exception e) {
            System.err.println("Erro ao processar série e/ou traduzir sinopse: " + e.getMessage());
            return null;
        }
    }

    private void listarSeriesBuscadas() {
        // Garanta que esta linha chame o método com a @Query configurada no repositório
        seriesDoBanco = repositorio.findAllWithGeneros();

        if (seriesDoBanco.isEmpty()) {
            System.out.println("Nenhuma série encontrada no banco de dados.");
            return;
        }

        seriesDoBanco.forEach(serie -> {
            String generosFormatados;
            // CORRIGIDO: de serie.getGenero() para serie.getGeneros()
            if (!serie.getGeneros().isEmpty()) {
                // g.getNome() está correto aqui, pois Genero é uma classe agora
                generosFormatados = serie.getGeneros().stream()
                        .map(g -> g.getNome())
                        .collect(Collectors.joining(", "));
            } else {
                generosFormatados = "Nenhum gênero";
            }

            System.out.println(" Série: " + serie.getTitulo() +
                    "\n Avaliação: " + serie.getAvaliacao() +
                    "\n Gêneros: " + generosFormatados +
                    "\n Sinopse: " + serie.getSinopse() +
                    "\n Categoria: " + serie.getCategoria().getNomePortugues() +
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

        // Lembre-se que getResultsGenero() deve retornar uma lista de objetos que Jackson pode mapear para Genero
        // e que esses objetos tenham getId() e getNome() ou campos públicos 'id' e 'nome'.
        resultado.getResultsGenero().forEach(genero ->
                nomeGeneros.put(genero.getId(), genero.getNome())
        );
    }
}