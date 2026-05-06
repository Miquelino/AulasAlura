package checkpoint.carreira.ackend.dto;

import checkpoint.carreira.ackend.entities.Reserva;

import java.time.LocalDateTime;

public record ReservaDTO(
        LocalDateTime inicio,
        LocalDateTime fim,
        Long salaId,
        Long usuarioId,
        String nome
) {
    public ReservaDTO(Reserva reserva) {
        this(
                reserva.getInicio(),
                reserva.getFim(),
                reserva.getSala().getId(),
                reserva.getUsuario().getId(),
                reserva.getSala().getNome()
        );
    }
}
