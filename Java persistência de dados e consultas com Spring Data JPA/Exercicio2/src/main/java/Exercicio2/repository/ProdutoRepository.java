package Exercicio2.repository;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    //Exercicio 1
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    // Encontra o primeiro produto ordenado pelo preço em ordem decrescente
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThan(Double preco);
    List<Produto> findByNomeContaining(String termo);
    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);
    long countByPrecoGreaterThan(Double valor);
    List<Produto> findByPrecoLessThanOrNomeContaining(Double valor, String termo);
    // *** NOVO MÉTODO PARA ENCONTRAR OS 3 PRODUTOS COM MAIOR PREÇO USANDO @Query ***
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> findTop3ProdutosComMaiorPreco();
    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    //Exercicio 2
    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> buscarPorPrecoMaior(@Param("preco") Double preco);
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> buscarOrdenadoPorPrecoAsc();
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> buscarOrdenadoPorPrecoDesc();
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE CONCAT(:palavra, '%')")
    List<Produto> consultarProdutoporLetra(@Param("palavra") String palavra);
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC LIMIT 5")
    List<Produto> consultarCincoProdutoMaisCaros();
    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecoProdutos();
    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    Double buscarPrecoMaximoPorCategoria(@Param("categoria") String categoria);
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> contarProdutosPorCategoria();
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")
    List<Object[]> categoriasComMaisDe(@Param("quantidade") long quantidade);
    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria)")
    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);
    @Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> buscarTop5ProdutosMaisCaros();

}
