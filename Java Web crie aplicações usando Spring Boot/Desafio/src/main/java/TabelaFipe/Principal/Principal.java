package TabelaFipe.Principal;

import Model.*;
import Service.ConsumoAPI;
import Service.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private String ENDERECO = "https://parallelum.com.br/fipe/api/v1";

    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bem vindo ao menu de opções!");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println("3. Caminhão");
            System.out.println("0. Sair");
            var opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    ENDERECO = ENDERECO + "/carros/marcas";
                    String jsonCarroMarcas = consumo.obterDados(ENDERECO);
                    List<CarroMarca> marcasCarro = conversor.obterListDados(jsonCarroMarcas, CarroMarca.class);


                    if (marcasCarro != null) {
                        System.out.println("\n--- Marcas de Carros ---");
                        marcasCarro.forEach(marca -> System.out.println("Código: " + marca.getCodigo() + " - Nome: " + marca.getNome()));
                        // ... (seu código para buscar modelos, anos, etc.) ...
                        System.out.println("Informe o cógido da marca para consulta: ");
                        var codigoMarca = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoMarca + "/modelos";
                        var jsonCarroModelos = consumo.obterDados(ENDERECO);
                        var modeloLista = conversor.obterDados(jsonCarroModelos, CarroModelos.class);

                        System.out.println("\n--- Modelos de Carros ---");

                        modeloLista.modelos().stream()
                                .sorted(Comparator.comparing(Dados::codigo))
                                //.forEach(System.out::println);
                                .forEach(modelo -> System.out.println("Código: " + modelo.codigo() + " - Nome: " + modelo.nome()));

                        System.out.println("Informe o nome do modelo do carro: ");
                        var nomeVeiculo = leitura.next();

                        List<Dados> modelosFiltrados = modeloLista.modelos()
                                .stream().filter(modelo -> modelo.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                                .collect(Collectors.toList());

                        System.out.println("Modelos filtrados");
                        modelosFiltrados.forEach(System.out::println);

                        System.out.println("Informe o cógido do modelo para consulta dos anos: ");
                        var codigoModelo = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoModelo + "/anos";
                        var jsonCarroAno = consumo.obterDados(ENDERECO);
                        List<Dados> anos = conversor.obterListDados(jsonCarroAno, Dados.class);
                        List<Veiculo> veiculos = new ArrayList<>();

                        System.out.println("\n--- Anos do Modelo do Carro ---");
                        for (int i = 0; i < anos.size(); i++) {
                            var enderecoAnos = ENDERECO + "/" + anos.get(i).codigo();
                            jsonCarroAno = consumo.obterDados(enderecoAnos);
                            Veiculo veiculo = conversor.obterDados(jsonCarroAno, Veiculo.class);
                            veiculos.add(veiculo);
                        }

                        System.out.println("\nTodos os veiculos filtrados com avaliação por ano: ");
                        veiculos.forEach(carro -> System.out.println("Valor: " + carro.valor() +
                                "\n Marca: " + carro.marca() +
                                "\n Modelo: " + carro.modelo() +
                                "\n Ano: " + carro.ano() +
                                "\n"));

                    }

                    break;

                case 2:
                    ENDERECO = ENDERECO + "/motos/marcas";
                    String jsonMotoMarca = consumo.obterDados(ENDERECO);
                    List<CarroMarca> marcasMoto = conversor.obterListDados(jsonMotoMarca, CarroMarca.class);


                    if (marcasMoto != null) {
                        System.out.println("\n--- Marcas de Motos ---");
                        marcasMoto.forEach(marca -> System.out.println("Código: " + marca.getCodigo() + " - Nome: " + marca.getNome()));
                        // ... (seu código para buscar modelos, anos, etc.) ...
                        System.out.println("Informe o cógido da marca para consulta: ");
                        var codigoMarca = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoMarca + "/modelos";
                        var json = consumo.obterDados(ENDERECO);
                        var modeloLista = conversor.obterDados(json, CarroModelos.class);

                        System.out.println("\n--- Modelos de Motos ---");
//                        modeloLista.modelos().stream()
//                                .sorted(Comparator.comparing(Dados::codigo))
//                                .forEach(modelo -> System.out.println("Código do Modelo: " + modelo.codigo() + " - Nome do Modelo: " + modelo.nome()));

                        modeloLista.modelos().stream()
                                .sorted(Comparator.comparing(Dados::codigo))
                                //.forEach(System.out::println);
                                .forEach(modelo -> System.out.println("Código: " + modelo.codigo() + " - Nome: " + modelo.nome()));

                        System.out.println("Informe o nome do modelo da Moto : ");
                        var nomeVeiculo = leitura.next();

                        List<Dados> modelosFiltrados = modeloLista.modelos()
                                .stream().filter(modelo -> modelo.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                                .collect(Collectors.toList());

                        System.out.println("Modelos filtrados");
                        modelosFiltrados.forEach(System.out::println);

                        System.out.println("Informe o cógido do modelo para consulta dos anos: ");
                        var codigoModelo = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoModelo + "/anos";
                        json = consumo.obterDados(ENDERECO);
                        List<Dados> anos = conversor.obterListDados(json, Dados.class);
                        List<Veiculo> veiculos = new ArrayList<>();

                        System.out.println("\n--- Anos do Modelo de Moto ---");
                        for (int i = 0; i < anos.size(); i++) {
                            var enderecoAnos = ENDERECO + "/" + anos.get(i).codigo();
                            json = consumo.obterDados(enderecoAnos);
                            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
                            veiculos.add(veiculo);
                        }

                        System.out.println("\nTodos os veiculos filtrados com avaliação por ano: ");
                        veiculos.forEach(moto -> System.out.println("Valor: " + moto.valor() +
                                "\n Marca: " + moto.marca() +
                                "\n Modelo: " + moto.modelo() +
                                "\n Ano: " + moto.ano() +
                                "\n"));

                    }

                    break;

                case 3:
                    ENDERECO = ENDERECO + "/motos/marcas";
                    String jsonCaminhaoMarca = consumo.obterDados(ENDERECO);
                    List<CarroMarca> marcasCaminhao = conversor.obterListDados(jsonCaminhaoMarca, CarroMarca.class);


                    if (marcasCaminhao != null) {
                        System.out.println("\n--- Marcas de Caminhões ---");
                        marcasCaminhao.forEach(marca -> System.out.println("Código: " + marca.getCodigo() + " - Nome: " + marca.getNome()));
                        // ... (seu código para buscar modelos, anos, etc.) ...
                        System.out.println("Informe o cógido da marca para consulta: ");
                        var codigoMarca = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoMarca + "/modelos";
                        var jsonCaminhaoModelos = consumo.obterDados(ENDERECO);
                        var modeloLista = conversor.obterDados(jsonCaminhaoModelos, CarroModelos.class);

                        System.out.println("\n--- Modelos de Caminhões ---");
//                        modeloLista.modelos().stream()
//                                .sorted(Comparator.comparing(Dados::codigo))
//                                .forEach(modelo -> System.out.println("Código do Modelo: " + modelo.codigo() + " - Nome do Modelo: " + modelo.nome()));

                        modeloLista.modelos().stream()
                                .sorted(Comparator.comparing(Dados::codigo))
                                //.forEach(System.out::println);
                                .forEach(modelo -> System.out.println("Código: " + modelo.codigo() + " - Nome: " + modelo.nome()));
                        ;

                        System.out.println("Informe o nome do modelo de Caminhiões: ");
                        var nomeVeiculo = leitura.next();

                        List<Dados> modelosFiltrados = modeloLista.modelos()
                                .stream().filter(modelo -> modelo.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                                .collect(Collectors.toList());

                        System.out.println("Modelos filtrados");
                        modelosFiltrados.forEach(System.out::println);

                        System.out.println("Informe o cógido do modelo para consulta dos anos: ");
                        var codigoModelo = leitura.next();
                        ENDERECO = ENDERECO + "/" + codigoModelo + "/anos";
                        var jsonCarroAno = consumo.obterDados(ENDERECO);
                        List<Dados> anos = conversor.obterListDados(jsonCarroAno, Dados.class);
                        List<Veiculo> veiculos = new ArrayList<>();

                        System.out.println("\n--- Anos do Modelo de Caminhões ---");
                        for (int i = 0; i < anos.size(); i++) {
                            var enderecoAnos = ENDERECO + "/" + anos.get(i).codigo();
                            jsonCarroAno = consumo.obterDados(enderecoAnos);
                            Veiculo veiculo = conversor.obterDados(jsonCarroAno, Veiculo.class);
                            veiculos.add(veiculo);
                        }

                        System.out.println("\nTodos os veiculos filtrados com avaliação por ano: ");
                        veiculos.forEach(caminhao -> System.out.println("Valor: " + caminhao.valor() +
                                "\n Marca: " + caminhao.marca() +
                                "\n Modelo: " + caminhao.modelo() +
                                "\n Ano: " + caminhao.ano() +
                                "\n"));


                    }

                    break;

                case 0:

                    System.out.println("Encerrando o programa...");
                    continuar = false;

                    break;

                default:

                    System.out.println("Opção invalida!");

                    break;
            }
        }

    }
}
