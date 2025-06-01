// Este é o conteúdo que seu AulaSpringWeb.screenmatch.model.Genero.java DEVE TER
package AulaSpringWeb.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // <-- ESSA ANOTAÇÃO É CRUCIAL!
@Table(name = "generos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genero { // <-- DEVE SER 'class Genero', NÃO 'record Genero' NEM 'class Generos'
    @Id
    private Integer id;

    @JsonAlias("name")
    private String nome;

    public Genero() {} // Construtor padrão obrigatório para o JPA

    public Genero(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}