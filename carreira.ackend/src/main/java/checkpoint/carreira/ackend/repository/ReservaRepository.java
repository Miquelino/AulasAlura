package checkpoint.carreira.ackend.repository;

import checkpoint.carreira.ackend.entities.Reserva;
import checkpoint.carreira.ackend.entities.StatusReserva;
import checkpoint.carreira.ackend.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findAll(Pageable pageable);

    List<Reserva> findBySalaIdAndStatus(Long id, StatusReserva statusReserva);

    @Query("""
    SELECT r FROM Reserva r
    WHERE r.sala.id = :salaId
    AND r.status = 'ATIVA'
    AND r.inicio < :fim
    AND r.fim > :inicio
    """)
    List<Reserva> buscarConflitos(Long salaId, LocalDateTime inicio, LocalDateTime fim);

    boolean existsBySalaIdAndStatusAndInicioLessThanAndFimGreaterThan(
            Long salaId,
            StatusReserva status,
            LocalDateTime fim,
            LocalDateTime inicio
    );
}
