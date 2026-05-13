package checkpoint.carreira.ackend.dto;

import checkpoint.carreira.ackend.entities.Reserva;

import java.time.LocalDateTime;

public record ReservaDTO(
        Long id,
        LocalDateTime inicio,
        LocalDateTime fim,
        Long salaId,
        Long usuarioId,
        String nome
) {
    public ReservaDTO(Reserva reserva) {
        this(
                reserva.getId(), 
                reserva.getInicio(),
                reserva.getFim(),
                reserva.getSala().getId(),
                reserva.getUsuario().getId(),
                reserva.getSala().getNome()
        );
    }
}
