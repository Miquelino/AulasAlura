package Exercicio2.Principal;


import Exercicio2.Model.Categoria;
import Exercicio2.Model.Fornecedor;
import Exercicio2.Model.Pedido;
import Exercicio2.Model.Produto;
import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.FornecedorRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Principal {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void exibirMenu() {
        // Criando categorias
        Categoria categoriaEletronicos = new Categoria("Eletrônicos");
        Categoria categoriaLivros = new Categoria("Livros");
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando fornecedores
        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        // Criando produtos
        Produto produto1 = new Produto("Notebook", 3500.0, fornecedorTech);
        Produto produto2 = new Produto("Smartphone", 2500.0, fornecedorTech);
        Produto produto3 = new Produto("Livro de Java", 100.0, fornecedorLivros);
        produto1.setFornecedor(fornecedorTech);
        produto2.setFornecedor(fornecedorTech);
        produto3.setFornecedor(fornecedorLivros);
        produtoRepository.saveAll(List.of(produto1, produto2, produto3));

        // Criando pedidos e associando produtos
        Pedido pedido1 = new Pedido(1L, LocalDate.now());
        pedido1.setProdutos(List.of(produto1, produto3));
        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
        pedido2.setProdutos(List.of(produto2));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));

        // Testando consultas e verificando os relacionamentos
        System.out.println("Produtos na categoria Eletrônicos: ");
        categoriaRepository.findById(1L).ifPresent(categoria ->
                categoria.getProdutos().forEach(produto ->
                        System.out.println(" - " + produto.getNome())
                )
        );

        System.out.println("\nPedidos e seus produtos:");
        pedidoRepository.findAll().forEach(pedido -> {
            System.out.println("Pedido " + pedido.getId() + ":");
            pedido.getProdutos().forEach(produto ->
                    System.out.println(" - " + produto.getNome())
            );
        });

        System.out.println("\nProdutos e seus fornecedores:");
        produtoRepository.findAll().forEach(produto ->
                System.out.println("Produto: " + produto.getNome() +
                        ", Fornecedor: " + produto.getFornecedor().getNome())
        );
    }
}