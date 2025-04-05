package Entities;

public class Cachorro extends Animal{

    private String raca;




    public Cachorro(String nome, int idade, String raca) {
        super(nome, idade);
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String abanarRabo(){
        return "Abanando o rabo.";
    }

    @Override
    public String emitirSom() {
        return "au au au.";
    }

    @Override
    public String descricao() {
        return "Nome: " + this.getNome() + ", idade = " + this.getIdade() + ", raça = " + this.getRaca();
    }
}
