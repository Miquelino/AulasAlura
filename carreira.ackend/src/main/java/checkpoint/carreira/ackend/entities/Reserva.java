package checkpoint.carreira.ackend.entities;

import checkpoint.carreira.ackend.dto.ReservaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Getter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusReserva status = StatusReserva.ATIVA;

    public Reserva() {}

    public Reserva(LocalDateTime inicio, LocalDateTime fim, Sala sala, Usuario usuario) {
        validarDatas(inicio, fim);

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Datas obrigatórias");
        }

        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Início deve ser antes do fim");
        }

        if (sala == null || usuario == null) {
            throw new IllegalArgumentException("Sala e usuário obrigatórios");
        }

        this.inicio = inicio;
        this.fim = fim;
        this.sala = sala;
        this.usuario = usuario;
        this.status = StatusReserva.ATIVA;
    }

    private void validarDatas(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Datas são obrigatórias");
        }

        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Início deve ser antes do fim");
        }
    }

    public boolean conflitaCom(Reserva outra) {
        if (!this.estaAtiva() || !outra.estaAtiva()) {
            return false;
        }

        return this.inicio.isBefore(outra.fim) &&
                this.fim.isAfter(outra.inicio);
    }

    public boolean estaAtiva() {
        return this.status == StatusReserva.ATIVA;
    }

    public void cancelar() {
        if (this.status == StatusReserva.CANCELADA) {
            throw new IllegalStateException("Reserva já está cancelada");
        }
        this.status = StatusReserva.CANCELADA;
    }
}
