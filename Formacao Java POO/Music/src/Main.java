import Entities.Aluno;
import Entities.Carro;
import Entities.Musica;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        Musica musica1 = new Musica("So os loucos sabem ", 2005, "Charlie Brown Jr");
        Musica musica2 = new Musica("Dias melhores", 2010, "Joge e Mateus");

        System.out.println("Ficha Tecnica");
        System.out.println(musica1.fichaTecnica());
        System.out.println(musica2.fichaTecnica());

        musica1.avaliarMusica(6);
        musica1.avaliarMusica(5);
        musica1.avaliarMusica(4);
        System.out.println(musica1.MediaAvaliacao());

        Carro carro1 = new Carro("Hb20", 2019, "Preto");
        Carro carro2 = new Carro("Onix", 2015, "Cinza");

        System.out.println();
        System.out.println("Informações do carro");
        System.out.println(carro1.InfoCarro());
        System.out.println(carro2.InfoCarro());

        Aluno aluno1 = new Aluno("Andre", 30);
        Aluno aluno2 = new Aluno("Carlos", 29);

        System.out.println("");
        System.out.println("Alunos");
        System.out.println(aluno1.Infomacoes());
        System.out.println(aluno2.Infomacoes());

    }
}