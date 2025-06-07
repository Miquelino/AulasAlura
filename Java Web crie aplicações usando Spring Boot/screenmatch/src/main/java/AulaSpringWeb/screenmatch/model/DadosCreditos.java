package AulaSpringWeb.screenmatch.model; // <-- ATENÇÃO: pacote 'service'

import AulaSpringWeb.screenmatch.model.DadosAtorCreditos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCreditos(
        // O campo 'elenco' DEVE ser uma lista de DadosAtorCreditos (o DTO, não a entidade Ator)
        @JsonAlias("cast") List<DadosAtorCreditos> elenco// <-- MUITO IMPORTANTE: Garanta que é DadosAtorCreditos (do pacote service)
) {}