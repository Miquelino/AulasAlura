package Exercicios.Exercicio1.model;


public class Tarefa {
    private String descricao;
    private boolean concluida = false;
    private String pessoaResponsavel;

    public Tarefa() {
        // necessário para o Jackson
    }

    public Tarefa(String descricao, String pessoaResponsavel) {
        this.descricao = descricao;
        this.pessoaResponsavel = pessoaResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getPessoaResponsavel() {
        return pessoaResponsavel;
    }

    public void setPessoaResponsavel(String pessoaResponsavel) {
        this.pessoaResponsavel = pessoaResponsavel;
    }

    public void concluindoTarefa(){
        System.out.println("Foi concluido a tarefa " + getDescricao()  + " pelo funcionário: " + getPessoaResponsavel());
        concluida = true;
    }


    @Override
    public String toString() {
        return "Tarefa{" +
                "descricao='" + descricao + '\'' +
                ", concluida=" + concluida +
                ", pessoaResponsavel='" + pessoaResponsavel + '\'' +
                '}';
    }
}
