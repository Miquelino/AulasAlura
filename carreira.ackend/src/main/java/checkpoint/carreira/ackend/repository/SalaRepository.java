package checkpoint.carreira.ackend.repository;

import checkpoint.carreira.ackend.entities.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Page<Sala> findAll(Pageable pageable);
}
