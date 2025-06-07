package AulaSpringWeb.screenmatch.repository;

import AulaSpringWeb.screenmatch.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional, mas boa prática para indicar que é um componente de repositório
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    // JpaRepository já fornece findById, save, etc.
}