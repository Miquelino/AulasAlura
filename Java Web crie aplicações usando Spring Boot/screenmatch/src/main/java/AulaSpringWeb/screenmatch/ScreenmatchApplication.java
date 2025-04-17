package AulaSpringWeb.screenmatch;

import AulaSpringWeb.screenmatch.model.DadosEpisodio;
import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.DadosTemporadas;
import AulaSpringWeb.screenmatch.model.TodosEpisodios;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import AulaSpringWeb.screenmatch.service.DadosResultado;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		ConverterDados conversor = new ConverterDados();

		String idSerie = "1396"; // ID de Breaking Bad no TMDb
		int temporada = 1;

		var json = consumoApi.obterDados("https://api.themoviedb.org/3/search/tv?api_key=adf7087c139b48d3eef7202f56ae8279&query=Breaking+Bad&language=pt-BR");
		var resultado = conversor.obterDados(json, DadosResultado.class);

		if (resultado.getResults().isEmpty()) {
			System.out.println("Nenhum resultado encontrado!");
		} else {
			var dados = resultado.getResults().get(0);
			var detalhesJson = consumoApi.obterDados(
					"https://api.themoviedb.org/3/tv/" + dados.id() + "?api_key=adf7087c139b48d3eef7202f56ae8279&language=pt-BR");
			var detalhesSerie = conversor.obterDados(detalhesJson, DadosTemporadas.class);

			//Exibir
			System.out.println("\n🎬 Dados da série:");
			System.out.println("Título: " + dados.titulo());
			System.out.println("Avaliação: " + dados.avaliacao());
			System.out.println("Data de Lançamento: " + dados.dataLancamento());
			System.out.println("Total de temporadas: " + detalhesSerie.totalTemporadas());
		}

		var jsonEpisodio = consumoApi.obterDados("https://api.themoviedb.org/3/tv/" + idSerie + "/season/" + temporada +
				"?api_key=adf7087c139b48d3eef7202f56ae8279&language=pt-BR");

		TodosEpisodios todosEpisodios = conversor.obterDados(jsonEpisodio, TodosEpisodios.class);
		//var episodio = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);

		if (resultado.getResults().isEmpty()) {
			System.out.println("Nenhum resultado encontrado!");
		} else {
			//var dados = resultado.getResults().get(0);

			System.out.println("📅 Episódios da Temporada " + temporada + ":");
			//Exibir
			for (DadosEpisodio ep : todosEpisodios.episodes()) {
				System.out.println("🎬 Episódio " + ep.numero() + ": " + ep.titulo());
				System.out.println("⭐ Avaliação: " + ep.avaliacao());
				System.out.println("📅 Lançamento: " + ep.dataLancamento());
				System.out.println("-------------------------------");
			}
		}
	}
}
