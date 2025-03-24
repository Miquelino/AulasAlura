package Entities;

public class PodCast extends Audio{

    private String apresentador;
    private String descricaoEpisodio;

    public PodCast(String titulo, double duracao, String apresentador, String descricaoEpisodio) {
        super(titulo, duracao);
        this.apresentador = apresentador;
        this.descricaoEpisodio = descricaoEpisodio;
    }

    //Getters e Setters serve para encapsular;
    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    public String getDescricaoEpisodio() {
        return descricaoEpisodio;
    }

    public void setDescricaoEpisodio(String descricaoEpisodio) {
        this.descricaoEpisodio = descricaoEpisodio;
    }

    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Apresentador: " + apresentador);
        System.out.println("Descricao do episodio: " + descricaoEpisodio);
        System.out.println("---------------");
    }

    @Override
    public int getClassificacao(){
        if (this.getCurtidas() > 500){
            return 10;
        } else {
            return 7;
        }
    }
}
