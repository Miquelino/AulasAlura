package AulaSpringWeb.screenmatch;

import AulaSpringWeb.screenmatch.principal.Principal;
import AulaSpringWeb.screenmatch.principal.PrincipalAula1;
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
//		var consumoApi = new ConsumoAPI();
//		ConverterDados conversor = new ConverterDados();
//		Principal principal = new Principal();
//		principal.exibeMenu();

//		PrincipalAula principalAula = new PrincipalAula();
//		principalAula.exibeMenu();

        PrincipalAula1 principalAula1 = new PrincipalAula1();
        principalAula1.exibeMenu();

//        Principal principal = new Principal();
//        principal.exibeMenu();


    }
}
