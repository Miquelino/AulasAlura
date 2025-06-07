package Exercicio2.repository;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Encontra o primeiro produto ordenado pelo preço em ordem decrescente
    List<Produto> findByPrecoGreaterThan(Double preco);

    List<Produto> findByPrecoLessThan(Double preco);

    List<Produto> findByNomeContaining(String termo);

    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);
}
