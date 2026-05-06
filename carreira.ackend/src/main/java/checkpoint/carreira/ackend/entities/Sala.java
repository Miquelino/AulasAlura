package checkpoint.carreira.ackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sala")
@Getter
@Setter
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da sala (ex: "Sala 101")
    private String nome;

    // Capacidade máxima de pessoas
    private int capacidade;

    // Indica se a sala pode ser utilizada
    private boolean ativa = true;

    public Sala() {}

    public Sala(String nome, int capacidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }

        this.nome = nome;
        this.capacidade = capacidade;
        this.ativa = true;
    }

    public void validar() {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade inválida");
        }
    }

    public void desativar() {
        this.ativa = false;
    }

    public void ativar() {
        this.ativa = true;
    }
}
