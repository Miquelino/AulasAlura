package Entities;

public class MinhasPreferidas {

    public void inclui(Audio audio){
        if(audio.getClassificacao() >= 9){
            System.out.println(audio.getTitulo() + " é considerado sucesso.");
        } else {
            System.out.println(audio.getTitulo() + " também é um dos mais curtidos.");
        }
    }
}
