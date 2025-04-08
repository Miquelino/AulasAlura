package Entities2;

public class Entrada {

    private String nome;
    private int idade;
    private int carterinha = 0;
    private double pagar = 50;
    

    public Entrada(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCarterinha() {
        return carterinha;
    }

    public void setCarterinha(int carterinha) {
        this.carterinha = carterinha;
    }

    public double pagamento(){
        if(getIdade() <= 10){
            pagar /= 2;
            System.out.println("O " + getNome() + " é criança paga meia: " + pagar);
            return pagar;
        } else if (getCarterinha() != 0 ){
            pagar /= 2;
            System.out.println("O " + getNome() + " é Estudante paga meia: " + pagar);
            return pagar;
        } else {
            System.out.println("O " + getNome() + "  é adulto paga inteira: " + pagar);
            return pagar;
        }
    }

    @Override
    public String toString() {
        return "Nome = " + nome +
                " Idade = " + idade;
    }
}
