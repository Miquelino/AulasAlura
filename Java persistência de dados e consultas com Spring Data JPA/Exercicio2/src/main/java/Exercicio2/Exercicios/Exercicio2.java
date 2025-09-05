package Exercicio2.Exercicios;

import Exercicio2.Model.Pedido;
import Exercicio2.Model.Produto;
import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.FornecedorRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class Exercicio2 {
    Scanner leitura = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataParaBuscar;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void exercicio() {
        boolean continuar = true;
        while (continuar) {
            var menu = """
                    1 - Crie uma consulta que retorne os produtos com preço maior que um valor
                    2 - Crie uma consulta que retorne os produtos ordenados pelo preço crescente.
                    3 - Crie uma consulta que retorne os produtos ordenados pelo preço decrescente.
                    4 - Crie uma consulta que retorne os produtos que comecem com uma letra específica.
                    5 - Crie uma consulta que retorne os pedidos feitos entre duas datas.
                    6 - Crie uma consulta que retorne a média de preços dos produtos.
                    7 - Crie uma consulta que retorne o preço máximo de um produto em uma categoria
                    8 - Crie uma consulta para contar o número de produtos por categoria.
                    9 - Crie uma consulta para filtrar categorias com mais de 10 produtos.
                    10 - Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
                    11 - Crie uma consulta nativa para buscar os cinco produtos mais caros
                    0 - Voltar ao menu anterior
                    """;

            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    consultaPrecoMaiorValor();
                    break;
                case 2:
                    consultaPrecoCrescente();
                    break;
                case 3:
                    consultaPrecoDecrecente();
                    break;
                case 4:
                    consultaRetornaProdutoLetraEspec();
                    break;
                case 5:
                    consultaPedidosEntreDatas();
                    break;
                case 6:
                    consultaMediaProdutos();
                    break;
                case 7:
                    consultaPrecoMaximoCategoria();
                    break;
                case 8:
                    consultaNumeroProdutosCategoria();
                    break;
                case 9:
                    consultaFiltroCategoriasMaisDe10();
                    break;
                case 10:
                    consultaFiltroNomeOuCategoria();
                    break;
                case 11:
                    consultaNativaCincoProdutosMaisCaros();
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }
    }

    // 1
    private void consultaPrecoMaiorValor() {
        System.out.println("Digite um preco: ");
        var preco = leitura.nextDouble();
        List<Produto> produtos = produtoRepository.buscarPorPrecoMaior(preco);
        produtos.forEach(System.out::println);
    }

    // 2
    private void consultaPrecoCrescente() {
        List<Produto> produtos = produtoRepository.buscarOrdenadoPorPrecoAsc();
        produtos.forEach(System.out::println);
    }

    // 3
    private void consultaPrecoDecrecente() {
        List<Produto> produtos = produtoRepository.buscarOrdenadoPorPrecoDesc();
        produtos.forEach(System.out::println);
    }

    // 4
    private void consultaRetornaProdutoLetraEspec() {
        System.out.println("Digite uma palavra ou letra para buscar produto: ");
        var palavra = leitura.nextLine();
        List<Produto> produtos = produtoRepository.consultarProdutoporLetra(palavra);
        produtos.forEach(System.out::println);
    }

    // 5
    private void consultaPedidosEntreDatas() {
        System.out.println("Digite a data inicial: (dd/mm/yyyy)");
        var dataInicial = leitura.nextLine();
        dataParaBuscar = LocalDate.parse(dataInicial, formatter);
        System.out.println("Digite a data final: (dd/mm/yyyy)");
        var dataFinal = leitura.nextLine();
        LocalDate dataParaBuscarFinal;
        dataParaBuscarFinal = LocalDate.parse(dataFinal, formatter);
        List<Pedido> pedidos = pedidoRepository.cosultarPedidosEntreDatas(dataParaBuscar, dataParaBuscarFinal);
        pedidos.forEach(System.out::println);
    }

    // 6
    private void consultaMediaProdutos() {

        Double produtos = produtoRepository.calcularMediaPrecoProdutos();
        System.out.println("Media dos produtos: " + produtos);
    }

    // 7
    private void consultaPrecoMaximoCategoria() {
        System.out.println("Digite e categoria: ");
        var categoria = leitura.nextLine();
        Double produtos = produtoRepository.buscarPrecoMaximoPorCategoria(categoria);
        System.out.println("Media dos produtos: " + produtos);
    }

    // 8
    private void consultaNumeroProdutosCategoria() {
        List<Object[]> produtos = produtoRepository.contarProdutosPorCategoria();
        produtos.forEach(System.out::println);
    }

    // 9
    private void consultaFiltroCategoriasMaisDe10() {
        System.out.println("Digite a quantidade de produtos que deseja filtrar: ");
        var quantidade = leitura.nextInt();
        List<Object[]> produtos = produtoRepository.categoriasComMaisDe(quantidade);
        produtos.forEach(System.out::println);
    }

    // 10
    private void consultaFiltroNomeOuCategoria() {
        System.out.println("Digite uma categoria ou nome e categoria do produto: ");
        var nome = leitura.nextLine();
        var categoria = leitura.nextLine();
        List<Produto> produtos = produtoRepository.buscarProdutosFiltrados(nome, categoria);
        produtos.forEach(System.out::println);
    }

    // 11
    private void consultaNativaCincoProdutosMaisCaros() {
        List<Produto> produto = produtoRepository.buscarTop5ProdutosMaisCaros();
        produto.forEach(System.out::println);
    }

}
