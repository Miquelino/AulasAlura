package Entities;

public class Audio {

    private String titulo;
    private double duracao;
    private int totalReproducao;
    private int curtidas;
    private int classificacao;

    public Audio(String titulo, double duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public void exibirFicha(){
        System.out.println("Titulo: " + titulo);
        System.out.println("Duração: " + duracao);
    }

    public void curtir(){
        this.curtidas++;
        System.out.println("Total de curtidas: " + this.curtidas);
    }

    public void reproduzir(){
        this.totalReproducao++;
        System.out.println("Total de reprodução: " + this.totalReproducao);
    }



}
