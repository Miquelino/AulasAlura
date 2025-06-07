package AulaSpringWeb.screenmatch.repository;

import AulaSpringWeb.screenmatch.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

    // Buscar ator pelo nome completo (exato, ignorando maiúsculas/minúsculas)
    Optional<Ator> findByNomeIgnoreCase(String nome);

    // Buscar por nomes parecidos (ex: para autocomplete, etc)
    Optional<Ator> findByNomeContainingIgnoreCase(String nome);
}
