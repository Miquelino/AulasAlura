import Entities.Cachorro;
import Entities.Gato;
import Entities.Leao;
import Entities2.Entrada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //HashMap<Integer, String> objetos = new HashMap<>();
        Scanner leitura = new Scanner(System.in);

        System.out.println("BEM VINDO AO ZOOLOGICO DO MENININHO");
        System.out.println("------------------------------");

        System.out.println("Quantas pessoas vão entrar?");
        int numeroPessoas = leitura.nextInt();

        //int[] quantidade = new int[numeroPessoas];

        double total = 0.0; // pessoa[i].pagamento();

        Entrada[] pessoa = new Entrada[numeroPessoas];
        //numeroPessoas += numeroPessoas;
        for (int i = 0; i < numeroPessoas; i++){

            System.out.println("Nome: ");
            String nome = leitura.next();
            System.out.println("Idade: ");
            int idade = leitura.nextInt();

            pessoa[i] = new Entrada(nome, idade);

            if (idade > 18){
                System.out.println("Você é estudante? Digite 1 para sim e 2 para não: ");
                int estudante = leitura.nextInt();
                if (estudante == 1 && idade > 18){
                    pessoa[i].setCarterinha(12234344);
                } else {
                    System.out.println("Seguir...");
                }
            }

            total += pessoa[i].pagamento();
            //System.out.println(pessoa[i].toString());
            System.out.println();
        }
        for (int i = 0; i < numeroPessoas; i++){
            System.out.println(pessoa[i].toString());
        }

        System.out.println();
        System.out.println("Total a ser pago: " + total);
        System.out.println();


        System.out.println("----------- ZOOLOGICO -----------");
        //Cachorro
        Cachorro cachorro1 = new Cachorro("Tico", 2, "Pincher");
        System.out.println(cachorro1.descricao());
        System.out.println(cachorro1.emitirSom());
        System.out.println(cachorro1.abanarRabo());
        System.out.println();

        //Gato
        Gato gato1 = new Gato("Nina", 3, "Siames");
        System.out.println(gato1.descricao());
        System.out.println(gato1.emitirSom());
        System.out.println(gato1.arranharMoveis());
        System.out.println();

        //Leao
        Leao leao1 = new Leao("Simba", 8, 'M');
        Leao leao2 = new Leao("Lala", 7, 'F');
        leao1.setLiderenca("Lider");
        System.out.println(leao1.descricao());
        System.out.println(leao1.emitirSom());
        System.out.println(leao2.descricao());
        System.out.println();



    }
}