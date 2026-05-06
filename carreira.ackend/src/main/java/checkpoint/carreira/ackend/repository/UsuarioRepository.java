package checkpoint.carreira.ackend.repository;

import checkpoint.carreira.ackend.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);

}
