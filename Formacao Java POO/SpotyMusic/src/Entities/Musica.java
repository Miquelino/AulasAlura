package Entities;

public class Musica extends Audio{
    private String album;
    private String genero;

    public Musica(String titulo, double duracao, String album, String genero) {
        super(titulo, duracao);
        this.album = album;
        this.genero = genero;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public void exibirFicha(){
        super.exibirFicha();
        System.out.println("Album: " + album);
        System.out.println("Genero: " + genero);
        System.out.println("---------------");
    }

    @Override
    public int getClassificacao(){
        if (this.getCurtidas() > 500){
            return 10;
        } else {
            return 8;
        }
    }
}
