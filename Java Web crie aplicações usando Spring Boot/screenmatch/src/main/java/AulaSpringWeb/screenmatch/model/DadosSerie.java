package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonAlias({"name", "original_name"}) String titulo,
        @JsonAlias("vote_average") Double avaliacao,
        @JsonAlias("first_air_date") String dataLancamento,
        @JsonAlias("id") int id,
        @JsonAlias("genre_ids") List<Integer> genreIds,
        @JsonAlias("poster_path") String poster,
        @JsonAlias("overview") String sinopse,
        @JsonAlias("number_of_seasons") Integer totalTemporadas // <--- ADICIONE ESTA LINHA
) {

    public List<Integer> getGenreIds() {
        return genreIds;
    }
    // Como é um record, o método totalTemporadas() será gerado automaticamente.
}