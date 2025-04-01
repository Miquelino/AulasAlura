import Entities.Cachorro;
import Entities.Gato;
import Entities.Leao;
import Entities2.Entrada;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {




        //Cachorro
        Cachorro cachorro1 = new Cachorro("Tico", 2, "Pincher");
        System.out.println(cachorro1.descricao());
        System.out.println(cachorro1.emitirSom());
        System.out.println(cachorro1.abanarRabo());

        //Gato
        Gato gato1 = new Gato("Nina", 3, "Siames");
        System.out.println(gato1.descricao());
        System.out.println(gato1.emitirSom());
        System.out.println(gato1.arranharMoveis());

        //Leao
        Leao leao1 = new Leao("Simba", 8, 'M');
        Leao leao2 = new Leao("Lala", 7, 'F');
        leao1.setLiderenca("Lider");
        System.out.println(leao1.descricao());
        System.out.println(leao1.emitirSom());
        System.out.println(leao2.descricao());

        //Cliente
        Entrada pessoa1 = new Entrada("Andre", 25);
        Entrada pessoa2 = new Entrada("Junior", 9);
        Entrada pessoa3 = new Entrada("Ariane", 20);
        pessoa1.pagamento();
        pessoa2.pagamento();
        pessoa3.setCarterinha(12365493);
        pessoa3.pagamento();

    }
}