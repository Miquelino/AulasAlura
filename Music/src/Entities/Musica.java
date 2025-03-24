package Entities;

public class Musica {

    private String titulo;
    private String artista;
    private int anoLancamento;
    private double avaliacao;
    private int numAvaliacao;

    public Musica(String titulo, int anoLancamento, String artista) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.artista = artista;
    }

    public String fichaTecnica(){
        return "Titulo: " + this.titulo + " Artista: " + this.artista + " Ano de lançamento: " + this.anoLancamento;
    }

    public void avaliarMusica(double nota){
        avaliacao += nota;
        numAvaliacao++;
    }

    public String MediaAvaliacao(){
        return "Media de avaliacao: " + avaliacao / numAvaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNumAvaliacao() {
        return numAvaliacao;
    }

    public void setNumAvaliacao(int numAvaliacao) {
        this.numAvaliacao = numAvaliacao;
    }
}
