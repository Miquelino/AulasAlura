package Exercicios.Exercicio1;

import Exercicios.Exercicio1.model.Avaliacao;
import Exercicios.Exercicio1.model.Tarefa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Exercicios.Exercicio1.model.Avaliacao.calcularMediaNotas;

@SpringBootApplication
public class Exercicio1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Exercicio1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tarefa tarefa1 = new Tarefa("Lavar o chão.", "Rey");
		tarefa1.concluindoTarefa();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("tarefa.json"), tarefa1);
		System.out.println("Dados salvos no arquivo tarefa.json");

		System.out.println(" ");
		System.out.println("Lendo o arquivo tarefa.json");
		Tarefa leituraArquivo = objectMapper.readValue(Paths.get("tarefa.json").toFile(), Tarefa.class);

		System.out.println("Tarefa: " + leituraArquivo.getDescricao() + " (" + leituraArquivo.getPessoaResponsavel() + ")");

		Avaliacao<String> nota1 = new Avaliacao("Matematica", "Melhore!", 5);
		Avaliacao<String> nota2 = new Avaliacao("Matematica", "Otima nota!", 8);
		Avaliacao<String> nota3 = new Avaliacao("Matematica", "Pessima nota!", 0);

		ArrayList<Avaliacao<String>> notaFinal = new ArrayList<>();
		notaFinal.add(nota1);
		notaFinal.add(nota2);
		notaFinal.add(nota3);
		System.out.println(calcularMediaNotas(notaFinal));
	}

}
