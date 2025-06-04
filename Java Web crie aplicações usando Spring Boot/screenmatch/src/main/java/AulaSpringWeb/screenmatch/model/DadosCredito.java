package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCredito(
        @JsonAlias("id") int id,
        @JsonAlias("media_type") String mediaType, // "tv" para séries, "movie" para filmes
        @JsonAlias("name") String titulo // Para séries, o nome é "name"
) {}