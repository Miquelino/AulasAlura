// No seu serieRepository.java
package AulaSpringWeb.screenmatch.repository;

import AulaSpringWeb.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface serieRepository extends JpaRepository<Serie, Integer> { // Seu ID primário é int
    // Se você estiver usando @ElementCollection
    @Query("SELECT s FROM Serie s JOIN FETCH s.generos")
    List<Serie> findAllWithGeneros();

    // Se você estiver usando @ManyToMany com uma entidade Genero
    // @Query("SELECT s FROM Serie s JOIN FETCH s.generos")
    // List<Serie> findAllWithGeneros(); // A query é a mesma, mas 'generos' seria List<Genero>
}