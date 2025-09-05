package Exercicio2.repository;

import Exercicio2.Model.Categoria;
import Exercicio2.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataIsNull();
    List<Pedido> findByDataIsNotNull();
    List<Pedido> findByDataAfter(LocalDate data);
    List<Pedido> findByDataBefore(LocalDate data);
    List<Pedido> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    // Exercicio 2
    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :dataParaBuscar AND :dataParaBuscarFinal")
    List<Pedido> cosultarPedidosEntreDatas(@Param("dataParaBuscar") LocalDate dataInicio,
                                           @Param("dataParaBuscarFinal") LocalDate dataFim);
}
