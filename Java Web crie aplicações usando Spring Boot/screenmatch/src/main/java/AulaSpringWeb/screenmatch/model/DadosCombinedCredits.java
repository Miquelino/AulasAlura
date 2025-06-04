package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCombinedCredits(
        @JsonAlias("cast") List<DadosCredito> creditos // A lista de filmes/séries que a pessoa atuou
) {}