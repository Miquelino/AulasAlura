package Exercicio2.Exercicios;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Pedido;
import Exercicio2.Model.Produto;
import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.FornecedorRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class Exercicio1 {
    Scanner leitura = new Scanner(System.in);

    // Define o formato esperado da data
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataParaBusca;

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
                    1 - Retorne todos os produtos com o nome exato fornecido.
                    2 - Retorne todos os produtos associados a uma categoria específica.
                    3 - Retorne produtos com preço maior que o valor fornecido.
                    4 - Retorne produtos com preço menor que o valor fornecido.
                    5 - Retorne produtos cujo nome contenha o termo especificado.
                    6 - Retorne pedidos que ainda não possuem uma data de entrega.
                    7 - Retorne pedidos com data de entrega preenchida.
                    8 - Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
                    9 - Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
                    10 - Retorne a contagem de produtos em uma categoria específica.
                    11 - Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
                    12 - Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
                    13 - Retorne pedidos feitos após uma data específica.
                    14 - Retorne pedidos feitos antes de uma data específica.
                    15 - Retorne pedidos feitos em um intervalo de datas.
                    16 - Retorne os três produtos mais caros.
                    17 - Retorne os cinco produtos mais baratos de uma categoria.
                    0 - Sair
                    """;

            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarNomeProduto();
                    break;
                case 2:
                    produtoCategoriaEspecifica();
                    break;
                case 3:
                    precoProdutoMaiorFornecido();
                    break;
                case 4:
                    precoProdutoMenorFornecido();
                    break;
                case 5:
                    produtoNomeTermoExpecifico();
                    break;
                case 6:
                    pedidosSemDataEntrega();
                    break;
                case 7:
                    pedidosDataEntrega();
                    break;
                case 8:
                    produtoPrecoOrdemCrescente();
                    break;
                case 9:
                    produtoPrecoOrdemDecrescente();
                    break;
                case 10:
                    produtoNumeroCategoria();
                    break;
                case 11:
                    produtoNumeroValorMaior();
                    break;
                case 12:
                    produtoNumeroValorMenor();
                    break;
                case 13:
                    pedidosFeitoDataX();
                    break;
                case 14:
                    pedidosFeitosAntesDataX();
                    break;
                case 15:
                    pedidosFeitoEntreDataX();
                    break;
                case 16:
                    retorne3ProdutosMaisCaros();
                    break;
                case 17:
                    retorne5ProdutosBaratosCategoria();
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public void buscarNomeProduto() {
        System.out.println("Qual o produto desejado? ");
        var nomeProduto = leitura.nextLine();
        List<Produto> produtoDesejado = produtoRepository.findByNomeContainingIgnoreCase(nomeProduto);
        produtoDesejado.forEach(System.out::println);
    }

    public void produtoCategoriaEspecifica() {
        System.out.println("Qual a categoria desejada? ");
        var nomeCategoria = leitura.nextLine();
        List<Categoria> categoriaDesejada = categoriaRepository.findByNomeContainingIgnoreCase(nomeCategoria);
        categoriaDesejada.forEach(System.out::println);
    }

    public void precoProdutoMaiorFornecido() {
        System.out.println("Produtos maior que esse preco: ");
        var maiorPreco = leitura.nextDouble();
        List<Produto> maiorPrecoProduto = produtoRepository.findByPrecoGreaterThan(maiorPreco);
        maiorPrecoProduto.forEach(System.out::println);
    }

    public void precoProdutoMenorFornecido() {
        System.out.println("Produtos menor que esse preco: ");
        var menorPreco = leitura.nextDouble();
        List<Produto> menorPrecoProduto = produtoRepository.findByPrecoLessThan(menorPreco);
        menorPrecoProduto.forEach(System.out::println);
    }

    public void produtoNomeTermoExpecifico() {
        System.out.println("Pesquisar por nome contendo: ");
        var contendoNome = leitura.nextLine();
        List<Produto> nomeContendo = produtoRepository.findByNomeContaining(contendoNome);
        nomeContendo.forEach(System.out::println);
    }

    public void pedidosSemDataEntrega() {
        System.out.println("Pedidos sem data de entrega: ");
        List<Pedido> pedidosSemData = pedidoRepository.findByDataIsNull();
        pedidosSemData.forEach(System.out::println);
    }

    public void pedidosDataEntrega() {
        System.out.println("Pesquisar pedidos da data: ");
        List<Pedido> listaDataEntrega = pedidoRepository.findByDataIsNotNull();
        listaDataEntrega.forEach(System.out::println);
    }

    public void produtoPrecoOrdemCrescente() {
        System.out.println("Pesquise a categoria: ");
        var categoriaPesquisada = leitura.nextLine();
        List<Produto> produtosCrescente = produtoRepository.findByCategoriaNomeOrderByPrecoAsc(categoriaPesquisada);
        produtosCrescente.forEach(System.out::println);
    }

    public void produtoPrecoOrdemDecrescente() {
        System.out.println("Pesquise a categoria: ");
        var categoriaPesquisada = leitura.nextLine();
        List<Produto> produtosCrescente = produtoRepository.findByCategoriaNomeOrderByPrecoDesc(categoriaPesquisada);
        produtosCrescente.forEach(System.out::println);
    }

    public void produtoNumeroCategoria() {
        System.out.println("Pesquite o categoria que deseja realizar a contagem de produtos: ");
        var produtoContagem = leitura.nextLine();
        List<Categoria> contagemProduto = categoriaRepository.countByNome(produtoContagem);
        contagemProduto.forEach(System.out::println);
    }

    public void produtoNumeroValorMaior() {
        System.out.println("Passe um valor: ");
        var valor = leitura.nextDouble();
        long quantidade = produtoRepository.countByPrecoGreaterThan(valor);
        System.out.println("Número de produtos com preço maior que " + valor + ": " + quantidade);
    }

    public void produtoNumeroValorMenor() {
        //Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
        System.out.println("Digite um valor: ");
        var valor = leitura.nextDouble();
        System.out.println("Digite um termo: ");
        var termo = leitura.nextLine();
        List<Produto> produtos = produtoRepository.findByPrecoLessThanOrNomeContaining(valor, termo);
        produtos.forEach(System.out::println);
    }

    public void pedidosFeitoDataX() {
        System.out.println("Digite a data do pedido: (dd/mm/yyyy)");
        var data = leitura.nextLine();
        dataParaBusca = LocalDate.parse(data, formatter);
        List<Pedido> pedidos = pedidoRepository.findByDataAfter(dataParaBusca);
        pedidos.forEach(System.out::println);
    }

    public void pedidosFeitosAntesDataX() {
        System.out.println("Digite a data do pedido: (dd/mm/yyyy)");
        var data = leitura.nextLine();
        dataParaBusca = LocalDate.parse(data, formatter);
        List<Pedido> pedidos = pedidoRepository.findByDataBefore(dataParaBusca);
        pedidos.forEach(System.out::println);
    }

    public void pedidosFeitoEntreDataX() {
        System.out.println("Digite duas datas e voltara os pedidos entre elas: (dd/mm/yyyy)");
        var dataInicio = leitura.nextLine();
        var dataFim = leitura.nextLine();
        dataParaBusca = LocalDate.parse(dataInicio, formatter);
        LocalDate dataParaBusca1 = LocalDate.parse(dataFim, formatter);
        List<Pedido> pedidos = pedidoRepository.findByDataBetween(dataParaBusca, dataParaBusca1);
        pedidos.forEach(System.out::println);
    }

    public void retorne3ProdutosMaisCaros() {
        List<Produto> produtos = produtoRepository.findTop3ProdutosComMaiorPreco();
        produtos.forEach(System.out::println);
    }

    public void retorne5ProdutosBaratosCategoria() {
        System.out.println("Digite uma categoria: ");
        var categoria = leitura.nextLine();
        List<Produto> produtos = produtoRepository.findTop5ByCategoriaNomeOrderByPrecoAsc(categoria);
        produtos.forEach(System.out::println);

    }
}
