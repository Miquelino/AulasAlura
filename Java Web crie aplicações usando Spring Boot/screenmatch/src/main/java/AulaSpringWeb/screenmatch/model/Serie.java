package AulaSpringWeb.screenmatch.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Double avaliacao;
    private String poster;
    private String sinopse;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer totalTemporadas;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    // Adicione 'cascade = CascadeType.PERSIST' aqui!
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // <--- LINHA ALTERADA
    @JoinTable(
            name = "serie_genero",
            joinColumns = @JoinColumn(name = "serie_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos = new ArrayList<>(); // Mantenha Genero no singular aqui

    public Serie() {
    }

    public Serie(DadosSerie dadosSerie, Categoria categoria) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(dadosSerie.avaliacao()).orElse(0.0);
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
        this.categoria = categoria;
    }

    // Getters e Setters (inalterados)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Double getAvaliacao() { return avaliacao; }
    public void setAvaliacao(Double avaliacao) { this.avaliacao = avaliacao; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Integer getTotalTemporadas() { return totalTemporadas; }
    public void setTotalTemporadas(Integer totalTemporadas) { this.totalTemporadas = totalTemporadas; }
    public List<Episodio> getEpisodios() { return episodios; }
    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    // LINHA 68 (e a anterior)
    public void setGeneros(List<Genero> generos) { this.generos = generos; }
    public List<Genero> getGeneros() { return generos; }

    @Override
    public String toString() {
        return "Categoria: " + categoria +
                ", Titulo: '" + titulo + '\'' +
                ", Avaliacao: " + avaliacao +
                ", Poster: '" + poster + '\'' +
                ", Sinopse: '" + sinopse + '\'' +
                ", TotalTemporadas: " + totalTemporadas +
                ", Generos: " + generos.stream().map(Genero::getNome).collect(Collectors.joining(", ")) +
                ", Episodios: " + episodios;
    }
}