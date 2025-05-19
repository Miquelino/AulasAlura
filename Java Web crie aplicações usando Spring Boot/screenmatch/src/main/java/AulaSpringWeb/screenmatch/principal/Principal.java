package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.DadosEpisodio;
import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.DadosTemporadas;
import AulaSpringWeb.screenmatch.model.Episodio;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;

import java.util.*;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private String idSerie = "1396"; // ID de Breaking Bad no TMDb
    private int totalTemporadas = 0;

    private final String ENDERECO = "https://api.themoviedb.org/3/search/tv?";
    private final String ENDERECO2 = "https://api.themoviedb.org/3/tv/";
    private final String API_KEY = "api_key=adf7087c139b48d3eef7202f56ae8279";

    private List<DadosSerie> dadosSeries = new ArrayList<>();


    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            // Apenas declare a lista vazia no começo:
            List<DadosTemporadas> temporadas = new ArrayList<>();

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
                    var menuOpcoesBusca = """
                            1 - Buscar pelo Nome
                            2 - Buscar por Genere
                            3 - Buscar por Actors
                            4 - Buscar por Pôster
                            5 - Buscar por Sinopse
                            
                            0 - Sair                                 
                            """;

                    System.out.println(menuOpcoesBusca);
                    var opcao1 = leitura.nextInt();
                    leitura.nextLine();

                    if (opcao1 == 1) {

                    } else if (opcao1 == 2) {
                        buscarSerieWeb();
                    } else if (opcao1 == 3) {

                    } else if (opcao1 == 4) {

                    } else if (opcao1 == 5) {

                    } else if (opcao == 0) {

                    } else {
                        System.out.println("Opção inexistente");
                    }

                    buscarSerieWeb();
                    //buscarSerieDesejada(nomeSerie, dados, temporadas);
                    break;

                case 2:
                    buscarEpisodioPorSerie();
                    //melhoresEpisodio(dados, temporadas);
                    break;

                case 3:
                    listarSeriesBuscadas();
                    //temporadaAno(dados, temporadas);
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
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporadas> temporadas = new ArrayList<>();


        for (int i = 1; i <= totalTemporadas; i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + API_KEY + "&query=" + nomeSerie.replace(" ", "+"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        // VERIFIQUE ANTES DE ACESSAR O GET(0)
//        if (resultado.getGenero().isEmpty()) {
//            System.out.println("Nenhum resultado encontrado!");
//        }

        DadosSerie dados = resultado.getResults().get(0);

        var detalhesJson = consumo.obterDados(
                ENDERECO2 + dados.id() + "?" + API_KEY + "&language=pt-BR");
        var detalhesSerie = conversor.obterDados(detalhesJson, DadosTemporadas.class);
        totalTemporadas = detalhesSerie.totalTemporadas();

        return dados;
    }

    private void listarSeriesBuscadas() {
        dadosSeries.forEach(System.out::println);
    }

}
