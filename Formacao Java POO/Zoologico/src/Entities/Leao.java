package Entities;

import Interfaces.ProibidoEntrar;

public class Leao extends Animal implements ProibidoEntrar {

    private String liderenca;
    private char sexo;

    public Leao(String nome, int idade, char sexo) {
        super(nome, idade);
        this.sexo = sexo;
    }

    public String getLiderenca() {
        return liderenca;
    }

    public void setLiderenca(String liderenca) {
        if (getSexo() == 'F'){
            System.out.println("Femea não pode ser lider.");
        } else {
            this.liderenca = liderenca;
        }
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public void AnimalPerigoso() {
        System.out.println("Cuidado, animal perigoso!");
    }

    @Override
    public String emitirSom(){
        return "Rugido.";
    }

    @Override
    public String descricao(){
        super.descricao();
        AnimalPerigoso();
        if (getLiderenca() == "Lider" && getSexo() == 'M'){
            return "Nome: " + this.getNome() + " idade = " + this.getIdade() + " é o " + this.liderenca + " do bando";
        }
        return "Nome: " + this.getNome() + " idade: " + this.getIdade() + " sexo: " + sexo;
    }

}
