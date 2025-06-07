package AulaSpringWeb.screenmatch.model;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;

    private Double avaliacao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String poster;

    @Column(columnDefinition = "TEXT") // Define o tipo de coluna para texto longo
    private String sinopse;

    // ... outros campos (titulo, totalTemporadas, avaliacao, etc.) ...

    // RELACIONAMENTO MANY-TO-MANY COM GENERO
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // <-- CascadeType.MERGE para evitar duplicidade
    @JoinTable(
            name = "serie_genero", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "serie_id"), // Coluna da Série na tabela de junção
            inverseJoinColumns = @JoinColumn(name = "genero_id") // Coluna do Gênero na tabela de junção
    )
    private Set<Genero> generos = new HashSet<>(); // <-- DECLARAÇÃO ÚNICA E CORRETA DO CAMPO

    // RELACIONAMENTO MANY-TO-MANY COM ATOR (já existente e correto, mas garantindo a posição)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "serie_ator", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "serie_id"), // Coluna que referencia Serie
            inverseJoinColumns = @JoinColumn(name = "ator_id") // Coluna que referencia Ator
    )
    private Set<Ator> atores = new HashSet<>();

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    // ... (restante do seu código) ...

    public Serie() {}

    public Serie(DadosSerie dadosSerie, Categoria categoria) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = Optional.ofNullable(dadosSerie.avaliacao()).orElse(0.0);
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
        this.categoria = categoria;
    }

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    // NOVO GETTER E SETTER PARA ATORES
    public Set<Ator> getAtores() {
        return atores;
    }

    public void setAtores(Set<Ator> atores) {
        this.atores = atores;
    }

    // ESTE GETTER DEVE RETORNAR Set<Genero>
    public Set<Genero> getGeneros() {
        return generos;
    }

    // ESTE SETTER DEVE RECEBER Set<Genero>
    public void setGeneros(Set<Genero> generos) { // <--- DEVE RECEBER Set<Genero>
        this.generos = generos;
    }

    @Override
    public String toString() {
        // Inclua os atores no toString para facilitar a visualização
        String nomesAtores = atores.stream()
                .map(Ator::getNome)
                .collect(Collectors.joining(", "));

        return "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", categoria=" + categoria +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", atores=" + nomesAtores +
                ", episodios=" + episodios +
                '}';
    }
}