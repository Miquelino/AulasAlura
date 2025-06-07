package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.*;
import AulaSpringWeb.screenmatch.repository.AtorRepository; // <-- Importe AtorRepository
import AulaSpringWeb.screenmatch.repository.GeneroRepository;
import AulaSpringWeb.screenmatch.repository.serieRepository;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;
import AulaSpringWeb.screenmatch.service.MyMemoryTranslate;
import org.springframework.transaction.annotation.Transactional; // Importe para o @Transactional
import AulaSpringWeb.screenmatch.model.DadosCreditos; // <-- MANTENHA/ADICIONE ESTA LINHA!

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
    private final String ENDERECO_PESSOA = "https://api.themoviedb.org/3/search/person?";
    private final String ENDERECO_CREDITOS_PESSOA = "https://api.themoviedb.org/3/person/";

    private Map<Integer, String> nomeGeneros = new HashMap<>();

    private serieRepository repositorioSerie;
    private GeneroRepository repositorioGenero;
    private AtorRepository repositorioAtor; // <-- NOVO: Injeção do repositório de Ator

    private List<Serie> seriesDoBanco = new ArrayList<>();

    private Optional<Serie> serieBusca;

    // --- CONSTRUTOR ATUALIZADO PARA INJETAR AtorRepository ---
    public PrincipalAula1(serieRepository repositorioSerie, GeneroRepository repositorioGenero, AtorRepository repositorioAtor) {
        this.repositorioSerie = repositorioSerie;
        this.repositorioGenero = repositorioGenero;
        this.repositorioAtor = repositorioAtor; // <-- NOVO
    }

    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            var menu = """
                    1 - Buscar séries (na web e salvar)
                    2 - Buscar episódios de uma série (já salva)
                    3 - Listar séries buscadas (do banco)
                    4 - Buscar série por título (no banco)
                    5 - Buscar séries por ator (no banco ou na web)
                    6 - Buscar top 5 series
                    7 - Buscar series por categoria
                    8 - Filtrar series
                    9 - Buscar episodio por trecho
                    10 - Top 5 episodios por serie
                    11 - Buscar episodios a partir de uma data
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
                    buscarSeriePorTituloNoBanco();
                    break;
                case 5:
                    buscarSeriesPorAtor(); // <-- Lógica alterada aqui
                    break;

                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    filtrarSeriesPorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTreacho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodioDepoisData();
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

        Optional<Serie> serie = repositorioSerie.findByTituloContainingIgnoreCase(nomeSerie);

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
                    .flatMap(d -> {
                        if (d.episodios() == null) {
                            return Stream.empty();
                        }
                        int temporada = d.numero(); // número da temporada
                        return d.episodios().stream()
                                .map(e -> new Episodio(temporada, e, serieEncontrada));
                    })
                    .collect(Collectors.toList());


            serieEncontrada.setEpisodios(episodios);

            System.out.println("Episódios buscados e associados à série: " + serieEncontrada.getTitulo());
            repositorioSerie.save(serieEncontrada);

            episodios.forEach(System.out::println);

        } else {
            System.out.println("Série não encontrada.");
        }
    }

    public void buscarSeriePorTituloNoBanco() {
        System.out.println("Digite o título da série para buscar no banco de dados: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorioSerie.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da série encontrada no banco: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada no banco de dados!");
        }
    }

    // --- LÓGICA ALTERADA PARA BUSCAR ATORES DO BANCO PRIMEIRO ---
    private void buscarSeriesPorAtor() {
        System.out.println("Digite o nome do ator:");
        var nomeAtor = leitura.nextLine();

        // 1. Tenta buscar o ator no banco de dados
        Optional<Ator> atorDoBanco = repositorioAtor.findByNomeContainingIgnoreCase(nomeAtor);

        if (atorDoBanco.isPresent()) {
            System.out.println("\n--- Ator encontrado no banco de dados: " + atorDoBanco.get().getNome() + " ---");
            Set<Serie> seriesDoAtor = atorDoBanco.get().getSeries();

            if (seriesDoAtor.isEmpty()) {
                System.out.println("Nenhuma série associada a este ator encontrada no banco de dados.");
            } else {
                System.out.println("Séries em que " + atorDoBanco.get().getNome() + " atuou (do banco):");
                seriesDoAtor.stream()
                        .map(Serie::getTitulo)
                        .distinct()
                        .sorted()
                        .forEach(System.out::println);
            }
        } else {
            System.out.println("\n--- Ator não encontrado no banco de dados local. Buscando na API... ---");
            // Lógica existente para buscar na API (mantida como fallback)

            // 1. Buscar o ID do ator na API
            var jsonPessoa = consumo.obterDados(ENDERECO_PESSOA + API_KEY + "&query=" + nomeAtor.replace(" ", "%20"));
            DadosResultadoPessoa resultadoPessoa = conversor.obterDados(jsonPessoa, DadosResultadoPessoa.class);

            if (resultadoPessoa == null || resultadoPessoa.pessoas().isEmpty()) {
                System.out.println("Ator não encontrado na API.");
                return;
            }

            DadosPessoa atorEncontradoApi = resultadoPessoa.pessoas().get(0);
            System.out.println("Ator encontrado na API: " + atorEncontradoApi.name() + " (ID TMDB: " + atorEncontradoApi.id() + ")");

            // 2. Buscar os créditos (séries/filmes) do ator na API
            var jsonCreditos = consumo.obterDados(ENDERECO_CREDITOS_PESSOA + atorEncontradoApi.id() + "/combined_credits?" + API_KEY);
            DadosCombinedCredits creditos = conversor.obterDados(jsonCreditos, DadosCombinedCredits.class);

            if (creditos == null || creditos.creditos().isEmpty()) {
                System.out.println("Nenhuma série encontrada para este ator nos créditos combinados da API.");
                return;
            }

            System.out.println("\nSéries em que " + atorEncontradoApi.name() + " atuou (da API):");
            List<String> seriesDoAtorApi = creditos.creditos().stream()
                    .filter(c -> "tv".equals(c.mediaType()))
                    .map(DadosCredito::titulo)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            if (seriesDoAtorApi.isEmpty()) {
                System.out.println("Nenhuma série de TV encontrada para este ator na API (apenas filmes ou outros tipos de mídia).");
            } else {
                seriesDoAtorApi.forEach(System.out::println);
            }
        }
    }

    @Transactional // <--- MANTENHA ESTA ANOTAÇÃO AQUI! É importante para a transação.
    public Serie getSerieCompleta() {
        System.out.println("Digite o nome da série para busca na web:");
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

        // LINHA CORRIGIDA:
        Set<Genero> generosDaApi = new HashSet<>(); // <-- Mude de List para Set e de ArrayList para HashSet
        if (dados.getGenreIds() != null) {
            for (Integer generoId : dados.getGenreIds()) {
                System.out.println("--- Processando Gênero ID: " + generoId + " ---");
                Optional<Genero> generoExistente = repositorioGenero.findById(generoId);

                Genero genero;
                if (generoExistente.isPresent()) {
                    genero = generoExistente.get();
                    System.out.println("Gênero ID " + generoId + " JÁ EXISTE no banco. Usando a instância existente.");
                } else {
                    genero = new Genero(generoId, nomeGeneros.getOrDefault(generoId, "Desconhecido"));
                    System.out.println("Gênero ID " + generoId + " NÃO EXISTE no banco. Tentando SALVAR novo gênero...");
                    repositorioGenero.save(genero);
                    System.out.println("Gênero ID " + generoId + " SALVO com sucesso.");
                }
                generosDaApi.add(genero);
            }
        }
        serie.setGeneros(generosDaApi);

        try {
            String overview = serie.getSinopse();
            if (overview != null && !overview.isBlank()) {
                String traducao = MyMemoryTranslate.obterTraducao(overview);
                serie.setSinopse(traducao);
            }

            // --- NOVO: Buscar elenco (atores principais) da API e salvá-los no banco ---
            Set<Ator> atoresPrincipais = new HashSet<>();
            String jsonCreditosSerie = consumo.obterDados(ENDERECO2 + dados.id() + "/credits?" + API_KEY);
            DadosCreditos creditosDaSerie = conversor.obterDados(jsonCreditosSerie, DadosCreditos.class);

            if (creditosDaSerie != null && creditosDaSerie.elenco() != null && !creditosDaSerie.elenco().isEmpty()) {
                // Pega os 3 primeiros atores
                for (int i = 0; i < Math.min(creditosDaSerie.elenco().size(), 3); i++) {
                    DadosAtorCreditos atorApi = creditosDaSerie.elenco().get(i);
                    // Tenta encontrar o ator no banco pelo ID do TMDB
                    Optional<Ator> atorExistente = repositorioAtor.findById(atorApi.id().longValue());

                    Ator ator;
                    if (atorExistente.isPresent()) {
                        ator = atorExistente.get(); // Usa o ator existente do banco
                    } else {
                        // Se não existe, cria um novo Ator e salva no repositório de Ator
                        ator = new Ator(atorApi.id(), atorApi.nome());
                        repositorioAtor.save(ator);
                    }
                    atoresPrincipais.add(ator); // Adiciona o ator (existente ou novo) ao set
                }
                serie.setAtores(atoresPrincipais); // Associa o set de atores à série
            }

            repositorioSerie.save(serie); // Salva a série (que agora tem um set de atores associados)
            return serie;
        } catch (Exception e) {
            System.err.println("Erro ao processar série e/ou traduzir sinopse ou buscar atores: " + e.getMessage());
            return null;
        }
    }

    private void listarSeriesBuscadas() {
        // Buscar todas as séries, incluindo os gêneros e atores (graças ao FetchType.EAGER)
        seriesDoBanco = repositorioSerie.findAllWithGeneros();

        if (seriesDoBanco.isEmpty()) {
            System.out.println("Nenhuma série encontrada no banco de dados.");
            return;
        }

        seriesDoBanco.forEach(serie -> {
            String generosFormatados;
            if (serie.getGeneros() != null && !serie.getGeneros().isEmpty()) {
                generosFormatados = serie.getGeneros().stream()
                        .map(g -> g.getNome())
                        .collect(Collectors.joining(", "));
            } else {
                generosFormatados = "Nenhum gênero";
            }

            String nomesAtores = "";
            if (serie.getAtores() != null && !serie.getAtores().isEmpty()) {
                nomesAtores = serie.getAtores().stream()
                        .map(Ator::getNome)
                        .collect(Collectors.joining(", "));
            } else {
                nomesAtores = "N/A";
            }


            System.out.println(" Série: " + serie.getTitulo() +
                    "\n Avaliação: " + serie.getAvaliacao() +
                    "\n Gêneros: " + generosFormatados +
                    "\n Sinopse: " + serie.getSinopse() +
                    "\n Categoria: " + serie.getCategoria().getNomePortugues() +
                    "\n Atores Principais: " + nomesAtores + // Exibir atores do Set
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

        resultado.getResultsGenero().forEach(genero ->
                nomeGeneros.put(genero.getId(), genero.getNome())
        );
    }

    private void buscarTop5Series() {
        List<Serie> seriesTop = repositorioSerie.findTop5ByOrderByAvaliacaoDesc();
        seriesTop.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria(){
        System.out.println("Deseja buscar séries de qual categoria/gênero?");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);

        List<Serie> seriesPorCategoria = repositorioSerie.findByCategoria(categoria);
        if (seriesPorCategoria.isEmpty()) {
            System.out.println("Nenhuma série encontrada para a categoria: " + nomeGenero);
        } else {
            System.out.println("Séries da categoria " + categoria.getNomePortugues() + ":");
            seriesPorCategoria.forEach(System.out::println);
        }
    }

    private void filtrarSeriesPorTemporadaEAvaliacao(){
        System.out.println("Filtrar series até quantas temporadas?");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Com avaliação a partir de que valor?");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSerie = repositorioSerie.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
        //List<Serie> filtroSerie = repositorioSerie.findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(totalTemporadas, avaliacao);
        System.out.println("*** Series filtradas ***");
        filtroSerie.forEach(s -> System.out.println(s.getTitulo() + " - avaliação: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTreacho(){
        System.out.println("Digite o nome do trecho:");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repositorioSerie.episodiosPorTrechos(trechoEpisodio);
        episodiosEncontrados.forEach(e -> System.out.printf("Serie: %s Temporada %s - Episodio %s - %s\n",
                e.getSerie().getTitulo(), e.getTemporada(), e.getNumero(), e.getTitulo()));
    }

    private void topEpisodiosPorSerie(){
        buscarSeriePorTituloNoBanco();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repositorioSerie.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e -> System.out.printf("Serie: %s Temporada %s - Episodio %s - %s Avaliação %s\n",
                    e.getSerie().getTitulo(), e.getTemporada(), e.getNumero(), e.getTitulo(), e.getAvaliacao()));
        }
    }

    private void buscarEpisodioDepoisData(){
        buscarSeriePorTituloNoBanco();
        if (serieBusca.isPresent()){
            Serie series = serieBusca.get();
            System.out.println("Digite a data desejada: ");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> episodiosAno = repositorioSerie.episodiosPorSerieAno(series, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }

}