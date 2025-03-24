import Entities.MinhasPreferidas;
import Entities.Musica;
import Entities.PodCast;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Musica musica1 = new Musica("So os loucos sabem", 3.32, "Papo Reto", "Rock");
        musica1.exibirFicha();
        musica1.reproduzir();
        musica1.reproduzir();
        musica1.curtir();

        PodCast podCast1 = new PodCast("Alinhamento da alma", 58.10, "Jose Carlos", "Detalha como é");
        podCast1.exibirFicha();
        podCast1.reproduzir();
        podCast1.reproduzir();
        podCast1.curtir();

        MinhasPreferidas preferidas = new MinhasPreferidas();
        preferidas.inclui(musica1);
        preferidas.inclui(podCast1);

    }
}