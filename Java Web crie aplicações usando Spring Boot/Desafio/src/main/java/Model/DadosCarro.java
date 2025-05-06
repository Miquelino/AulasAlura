package Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCarro(@JsonAlias("nome") String nomeMarca,
                         @JsonAlias("codigo") int idMarca) {
}