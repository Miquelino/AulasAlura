package Entities;

public class Aluno {

    private String nome;
    private int idade;

    public Aluno(String nome, int idade) {
        this.idade = idade;
        this.nome = nome;
    }

    public String Infomacoes(){
        return "Aluno: " + this.nome + " Idade: " + this.idade;
    }
}
