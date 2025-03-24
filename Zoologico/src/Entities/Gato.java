package Entities;

public class Gato extends Animal{

    private String raca;


    public Gato(String nome, int idade, String raca) {
        super(nome, idade);
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String arranharMoveis(){
        return "Arranhando moveis.";
    }

    @Override
    public String emitirSom() {
        return "Miau.";
    }

    @Override
    public String descricao() {
        return "Nome: " + this.getNome() + " idade = " + this.getIdade() + " raça = " + this.getRaca();
    }

}
