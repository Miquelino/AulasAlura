// Em AulaSpringWeb.screenmatch.model.DadosTemporadas
package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporadas(@JsonAlias("season_number") Integer numero, // <-- Este campo!
                              @JsonAlias("episodes") List<DadosEpisodio> episodios,
                              @JsonAlias("total_episodes") Integer totalEpisodios // ou outro nome se você o tiver
                              // Outros campos da temporada...
) {
}