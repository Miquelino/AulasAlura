package TabelaFipe.Principal;

import Model.CarroMarca;
import Model.DadosCarro;
import Service.ConsumoAPI;
import Service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1";

    public void exibeMenu(){
        boolean continuar = true;

        while (continuar){
            System.out.println("Bem vindo ao menu de opções!");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println("3. Caminhão");
            var opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    String jsonCarroMarcas = consumo.obterDados(ENDERECO + "/carros/marcas");
                    List<CarroMarca> marcasCarro = conversor.obterListDados(jsonCarroMarcas, CarroMarca.class);


                    if (marcasCarro != null) {
                        System.out.println("\n--- Marcas de Carros ---");
                        marcasCarro.forEach(marca -> System.out.println("Código: " + marca.getCodigo() + " - Nome: " + marca.getNome()));
                        // ... (seu código para buscar modelos, anos, etc.) ...
                    }
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 0:

                    break;

                default:

                    break;
            }
        }

    }
}
