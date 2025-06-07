package AulaSpringWeb.screenmatch.repository;

import AulaSpringWeb.screenmatch.model.Categoria;
import AulaSpringWeb.screenmatch.model.Episodio;
import AulaSpringWeb.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface serieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    // Corrigido: busca por ator com JOIN
    @Query("SELECT s FROM Serie s JOIN s.atores a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAtor, '%'))")
    List<Serie> buscarPorNomeDoAtor(@Param("nomeAtor") String nomeAtor);

    // Corrigido: traz as séries com seus gêneros para evitar LazyInitializationException
    @Query("SELECT s FROM Serie s JOIN FETCH s.generos")
    List<Serie> findAllWithGeneros();


    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByCategoria(Categoria categoria); // <-- Aqui está o método correto

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer totalTemporadas, double avaliacao);

    @Query("Select s from Serie s where s.totalTemporadas <= :totalTemporadas and s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(Integer totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE LOWER(e.titulo) LIKE LOWER(CONCAT('%', :trechoEpisodio, '%'))")
    List<Episodio> episodiosPorTrechos(@Param("trechoEpisodio") String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieAno(Serie serie, int anoLancamento);
}
