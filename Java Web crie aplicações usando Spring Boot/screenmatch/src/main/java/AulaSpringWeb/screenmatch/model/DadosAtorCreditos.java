package AulaSpringWeb.screenmatch.model; // <-- ATENÇÃO: pacote 'service'

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtorCreditos(
        @JsonAlias("id") Integer id,
        @JsonAlias("name") String nome,
        @JsonAlias("character") String personagem // Exemplo: pode ser útil para o futuro
) {}