package AulaSpringWeb.screenmatch;

import AulaSpringWeb.screenmatch.model.DadosEpisodio;
import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.DadosTemporadas;
import AulaSpringWeb.screenmatch.model.TodosEpisodios;
import AulaSpringWeb.screenmatch.principal.Principal;
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
		Principal principal = new Principal();
		principal.exibeMenu();


	}
}
