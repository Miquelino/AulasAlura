package checkpoint.carreira.ackend.dto;

import checkpoint.carreira.ackend.entities.Sala;

public record DadosDetalhamentoSala(Long id, String nome, Integer capacidade) {

    public DadosDetalhamentoSala(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getCapacidade());
    }
}
