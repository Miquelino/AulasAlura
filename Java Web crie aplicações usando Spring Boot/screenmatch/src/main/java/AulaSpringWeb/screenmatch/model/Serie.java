package AulaSpringWeb.screenmatch.model;

import AulaSpringWeb.screenmatch.service.MyMemoryTranslate;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrimary;
    private int id;
    @Column(unique = true)
    private String titulo;
    private Double avaliacao;
    private Integer totalTemporadas;
    // Na sua classe Serie
    @ElementCollection(fetch = FetchType.EAGER) // Adicione fetch = FetchType.EAGER
    private List<Integer> generos = new ArrayList<>();
    private String sinopse;
    @Transient
    private List<Episodio> episodios = new ArrayList<>();

    public Serie(){

    }

    // Construtor sem tradução
    public Serie(DadosSerie dados) {
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.avaliacao = dados.avaliacao();
        this.totalTemporadas = 0; // valor padrão
        this.generos = dados.getGenreIds();
        this.sinopse = dados.sinopse(); // Mantém a sinopse original
    }

    // Método para traduzir a sinopse
    public void traduzirSinopse() throws Exception {
        this.sinopse = MyMemoryTranslate.obterTraducao(this.sinopse).trim();
    }

    // Getters e Setters
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public int getIdPrimary() {
        return idPrimary;
    }

    public void setIdPrimary(int idPrimary) {
        this.idPrimary = idPrimary;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public List<Integer> getGeneros() {
        return generos;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", avaliacao=" + avaliacao +
                ", totalTemporadas=" + totalTemporadas +
                ", generos=" + generos +
                ", sinopse='" + sinopse + '\'' +
                '}';
    }
}
