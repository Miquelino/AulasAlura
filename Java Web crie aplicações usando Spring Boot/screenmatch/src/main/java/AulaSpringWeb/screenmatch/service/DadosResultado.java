package AulaSpringWeb.screenmatch.service;

import AulaSpringWeb.screenmatch.model.DadosSerie;
import AulaSpringWeb.screenmatch.model.Genero;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResultado {

    @JsonAlias("genres") // Verifique este alias com o JSON
    private List<Genero> resultsGenero; // E este nome de campo

    public List<Genero> getResultsGenero() {
        return resultsGenero;
    }

    public void setResultsGenero(List<Genero> resultsGenero) {
        this.resultsGenero = resultsGenero;
    }

    private List<DadosSerie> results;

    public List<DadosSerie> getResults() {
        return results;
    }

    public void setResults(List<DadosSerie> results) {
        this.results = results;
    }
}
