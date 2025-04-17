package Exercicios.Exercicio1.model;

import java.util.List;

public class Avaliacao<T> {

    private T item;
    private double nota;
    private String comentario;

    public Avaliacao(T item, String comentario, double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        this.item = item;
        this.comentario = comentario;
        this.nota = nota;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public static <T> double calcularMediaNotas(List<Avaliacao<T>> avaliacoes){
        if (avaliacoes.isEmpty()) {
            throw new IllegalArgumentException("A lista de avaliações está vazia.");
        }
        double soma = 0;
        for (Avaliacao<T> avaliacao : avaliacoes){
            soma += avaliacao.getNota();
        }
        return soma / avaliacoes.size();
    }

    @Override
    public String toString() {
        return "Acaliacao{" +
                "item=" + item +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
