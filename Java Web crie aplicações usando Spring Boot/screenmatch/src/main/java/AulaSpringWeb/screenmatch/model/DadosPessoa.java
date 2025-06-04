package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPessoa(
        int id,
        String name // O nome do ator na API geralmente é "name"
) {}