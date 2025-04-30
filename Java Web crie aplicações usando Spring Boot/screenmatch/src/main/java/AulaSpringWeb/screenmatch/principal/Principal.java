package AulaSpringWeb.screenmatch.principal;

import AulaSpringWeb.screenmatch.model.DadosEpisodio;
import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.DadosTemporadas;
import AulaSpringWeb.screenmatch.model.TodosEpisodios;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private String idSerie = "1396"; // ID de Breaking Bad no TMDb
    private int totalTemporadas = 0;

    private final String ENDERECO = "https://api.themoviedb.org/3/search/tv?";
    private final String ENDERECO2 = "https://api.themoviedb.org/3/tv/";
    private final String API_KEY = "api_key=adf7087c139b48d3eef7202f56ae8279";

    public void exibeMenu(){
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();

        var json = consumo.obterDados(ENDERECO + API_KEY  + "&query=" + nomeSerie.replace(" ", "+"));
        var resultado = conversor.obterDados(json, DadosResultado.class);
        System.out.println(resultado);

        if (resultado.getResults().isEmpty()) {
            System.out.println("Nenhum resultado encontrado!");
        } else {
            var dados = resultado.getResults().get(0);
            var detalhesJson = consumo.obterDados(
                    ENDERECO2 + dados.id() + "?" + API_KEY + "&language=pt-BR");
            var detalhesSerie = conversor.obterDados(detalhesJson, DadosTemporadas.class);

            totalTemporadas = detalhesSerie.totalTemporadas();

            //Exibir
            System.out.println("\n🎬 Dados da série:");
            System.out.println("Título: " + dados.titulo());
            System.out.println("Avaliação: " + dados.avaliacao());
            System.out.println("Data de Lançamento: " + dados.dataLancamento());
            System.out.println("Total de temporadas: " + detalhesSerie.totalTemporadas());
            System.out.println(" ");
        }

        System.out.println("Digite a temporada de " + nomeSerie + " para pesquisar os episodios: ");
        var idTemporada = leitura.nextInt();

        try {
            var jsonEpisodio = consumo.obterDados(ENDERECO2 + idSerie + "/season/" + idTemporada +
                    "?" + API_KEY + "&language=pt-BR");

            TodosEpisodios todosEpisodios = conversor.obterDados(jsonEpisodio, TodosEpisodios.class);

            if (todosEpisodios != null && todosEpisodios.episodes() != null) {
                System.out.println("📅 Episódios da Temporada " + idTemporada + ":");
                for (DadosEpisodio ep : todosEpisodios.episodes()) {
                    System.out.println("🎬 Episódio " + ep.numero() + ": " + ep.titulo());
                    System.out.println("⭐ Avaliação: " + ep.avaliacao());
                    System.out.println("📅 Lançamento: " + ep.dataLancamento());
                    System.out.println("-------------------------------");
                }
            } else {
                System.out.println("🚫 Temporada não encontrada. A serie tem no total " + totalTemporadas + " temporadas");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar episódios da temporada: " + e.getMessage());
        }

    }

}