import Entities.Produto;
import Entities.Servico;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Produto produto = new Produto();
        Servico servico = new Servico();
        produto.aplicarDesconto(10);
        produto.precoTotal(10);
        servico.aplicarDesconto(10);
        servico.precoTotal(10);
    }
}