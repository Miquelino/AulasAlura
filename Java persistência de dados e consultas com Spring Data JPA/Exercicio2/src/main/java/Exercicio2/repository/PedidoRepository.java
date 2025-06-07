package Exercicio2.repository;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataIsNull();
    List<Pedido> findByDataIsNotNull();
}
