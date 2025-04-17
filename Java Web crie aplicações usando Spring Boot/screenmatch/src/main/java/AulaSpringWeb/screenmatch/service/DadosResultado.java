package AulaSpringWeb.screenmatch.service;

import AulaSpringWeb.screenmatch.model.DadosSerie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResultado {
    private List<DadosSerie> results;

    public List<DadosSerie> getResults() {
        return results;
    }

    public void setResults(List<DadosSerie> results) {
        this.results = results;
    }
}
