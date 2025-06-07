package AulaSpringWeb.screenmatch.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "atores")
public class Ator {
    // Usamos o ID do TMDB como ID principal para evitar duplicatas da API
    @Id
    @Column(name = "tmdb_id") // Nome da coluna no banco, se diferente do nome do campo
    private Integer id;

    private String nome;

    // Relacionamento ManyToMany: um Ator pode estar em muitas Series
    // mappedBy = "atores" indica que o lado "dono" da relação está na entidade Serie (no campo 'atores')
    // CascadeType.MERGE: ao fazer merge de um ator, séries associadas também são mescladas/atualizadas
    // FetchType.EAGER: séries associadas são carregadas junto com o ator (cuidado com performance em grandes volumes)
    @ManyToMany(mappedBy = "atores", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Serie> series = new HashSet<>();


    public Ator() {}

    // Construtor para criar Ator a partir dos dados da API (DadosAtorCreditos)
    public Ator(Integer idTmdb, String nome) {
        this.id = idTmdb;
        this.nome = nome;
    }

    // --- Getters e Setters ---
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

    public Set<Serie> getSeries() {
        return series;
    }

    public void setSeries(Set<Serie> series) {
        this.series = series;
    }

    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
        serie.getAtores().add(this); // garante sincronismo
    }


    @Override
    public String toString() {
        return "Ator{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return Objects.equals(id, ator.id); // Comparação baseada no ID do TMDB
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash baseado no ID do TMDB
    }
}