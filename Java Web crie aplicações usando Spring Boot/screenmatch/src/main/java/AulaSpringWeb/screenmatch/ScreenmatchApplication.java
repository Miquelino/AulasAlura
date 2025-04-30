package AulaSpringWeb.screenmatch;

import AulaSpringWeb.screenmatch.principal.Principal;
import AulaSpringWeb.screenmatch.principal.PrincipalAula;
import AulaSpringWeb.screenmatch.service.ConsumoAPI;
import AulaSpringWeb.screenmatch.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var consumoApi = new ConsumoAPI();
//		ConverterDados conversor = new ConverterDados();
//		Principal principal = new Principal();
//		principal.exibeMenu();

		PrincipalAula principalAula = new PrincipalAula();
		principalAula.exibeMenu();



	}
}
