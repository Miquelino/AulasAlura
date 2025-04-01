import Entities.Calculavel;
import Entities.Livro;
import Entities.ProdutoFisico;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Livro livro = new Livro();
        ProdutoFisico produto = new ProdutoFisico();
        System.out.println("O preco final do livro é: " + livro.calcularPrecoFinal(100));
        System.out.println("O preco final do livro é: " + produto.calcularPrecoFinal(100));
    }
}