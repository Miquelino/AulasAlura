package Exercicio2.Principal;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Pedido;
import Exercicio2.repository.CategoriaRepository;
import Exercicio2.repository.PedidoRepository;
import Exercicio2.repository.ProdutoRepository;
import Exercicio2.Model.Produto;
import Exercicio2.repository.ProdutoRepository;
import Exercicio2.repository.ProdutoRepository;


import java.time.LocalDate;
import java.util.Scanner;

public class Principal {
    LocalDate dataAtual = LocalDate.now();

    Scanner leitura = new Scanner(System.in);
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private PedidoRepository pedidoRepository;


    public Principal(ProdutoRepository produto, CategoriaRepository categoria, PedidoRepository pedido){
        this.produtoRepository = produto;
        this.categoriaRepository = categoria;
        this.pedidoRepository = pedido;
    }

    public void exibirMenu(){
        Produto produto = new Produto("Notebook", 3500.0);
        Categoria categoria = new Categoria(1L, "Eletrônicos");
        Pedido pedido = new Pedido(1L, dataAtual);

        produtoRepository.save(produto);
        categoriaRepository.save(categoria);
        pedidoRepository.save(pedido);


    }


}