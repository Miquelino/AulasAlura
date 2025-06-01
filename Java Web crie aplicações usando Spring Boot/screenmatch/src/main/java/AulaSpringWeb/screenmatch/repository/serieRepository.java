package AulaSpringWeb.screenmatch.repository;

import AulaSpringWeb.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // <-- Garanta esta importação
import java.util.List;

public interface serieRepository extends JpaRepository<Serie, Long> {

    // Esta anotação @Query é crucial para que o Spring Data JPA saiba como buscar os gêneros
    @Query("SELECT s FROM Serie s JOIN FETCH s.generos") // <-- Esta linha é a correção!
    List<Serie> findAllWithGeneros();

    // Mantenha quaisquer outros métodos que você possa ter aqui
}