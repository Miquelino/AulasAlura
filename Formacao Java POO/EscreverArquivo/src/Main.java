import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {



        File arquivo = new File("filmes.txt");
        FileWriter escrita = new FileWriter(arquivo);
        String dados = "Conteúdo a ser gravado no arquivo.";
        escrita.write(dados);
        escrita.close();

        Scanner scanner = new Scanner(arquivo);
        System.out.println("Aqui scanner");
        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            System.out.println(linha);
        }
        scanner.close();

        Main titulo = new Main();

        Gson gson = new Gson();
        String json = gson.toJson(titulo);

        // Configuração do Gson com Pretty Printing
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        String json1 = gson1.toJson(titulo);

        System.out.println(json1);
        System.out.println(json);



        Veiculo carro = new Veiculo();
        carro.setNome("Onix");
        carro.setAno(2022);
        carro.setCor("Branco");
        //Gson gson2 = new Gson();
        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson2.toJson(carro);
        System.out.println(json2);

        File arquivo1 = new File("carro.txt");

        FileWriter escrita1 = new FileWriter(arquivo1);
        escrita1.write(carro.toString());
        escrita1.close();
        Scanner leituraCarro = new Scanner(arquivo1);
        while(leituraCarro.hasNextLine()){
            String linha = leituraCarro.nextLine();
            System.out.println(linha);
        }
        scanner.close();

    }
}