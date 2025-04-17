package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias({"name", "original_name"}) String titulo,
                         @JsonAlias("vote_average") String avaliacao,
                         @JsonAlias("first_air_date") String dataLancamento,
                         @JsonAlias("id") int id) {

}
