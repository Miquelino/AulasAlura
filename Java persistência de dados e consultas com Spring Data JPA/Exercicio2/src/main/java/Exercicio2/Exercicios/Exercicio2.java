package Exercicio2.Exercicios;

import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.FornecedorRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class Exercicio2 {
    Scanner leitura = new Scanner(System.in);

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void exercicio() {
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
                break;
            default:
                System.out.println("Opção invalida!");
                break;
        }

    }

    private void consultaNativaCincoProdutosMaisCaros() {
    }

    private void consultaFiltroNomeOuCategoria() {
    }

    private void consultaFiltroCategoriasMaisDe10() {
    }

    private void consultaNumeroProdutosCategoria() {
    }

    private void consultaPrecoMaximoCategoria() {
    }

    private void consultaMediaProdutos() {
    }

    private void consultaPedidosEntreDatas() {
    }

    private void consultaRetornaProdutoLetraEspec() {
    }

    private void consultaPrecoDecrecente() {
    }

    private void consultaPrecoCrescente() {
    }

    private void consultaPrecoMaiorValor() {
    }

}
