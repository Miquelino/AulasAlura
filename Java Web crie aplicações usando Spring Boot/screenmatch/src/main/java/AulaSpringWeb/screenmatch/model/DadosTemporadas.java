package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporadas(
        @JsonAlias("number_of_seasons") int totalTemporadas,
        @JsonAlias("episodes") List<DadosEpisodio> episodios)
       {
}
