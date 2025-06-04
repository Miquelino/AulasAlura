package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultadoPessoa(
        @JsonAlias("results") List<DadosPessoa> pessoas // Lista de pessoas encontradas
) {}