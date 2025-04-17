package AulaSpringWeb.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("episode_number") int numero,
                            @JsonAlias("name") String titulo,
                            @JsonAlias("vote_average") double avaliacao,
                            @JsonAlias("air_date") String dataLancamento) {
}
