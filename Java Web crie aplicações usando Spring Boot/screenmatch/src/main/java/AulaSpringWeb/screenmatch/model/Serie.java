package AulaSpringWeb.screenmatch.model;

import AulaSpringWeb.screenmatch.service.MyMemoryTranslate;

import java.util.List;

public class Serie {
    private int id;
    private String titulo;
    private Double avaliacao;
    private Integer totalTemporadas;
    private List<Integer> generos;
    private String sinopse;

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
