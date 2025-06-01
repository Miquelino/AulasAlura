package Exercicio2.repository;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
