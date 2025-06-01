package Exercicio2;

import Exercicio2.Principal.Principal;
import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class main implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produto;
	@Autowired
	private CategoriaRepository categoria;
	@Autowired
	private PedidoRepository pedido;


	public static void main(String[] args) {
		SpringApplication.run(main.class, args);
	}

	@Override
	public void run (String... args) throws Exception{

		Principal principal = new Principal(produto, categoria, pedido);
		principal.exibirMenu();
	}

}
