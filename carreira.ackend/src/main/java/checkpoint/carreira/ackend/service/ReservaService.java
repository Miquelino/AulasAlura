package checkpoint.carreira.ackend.service;

import checkpoint.carreira.ackend.dto.DadosDetalhamentoSala;
import checkpoint.carreira.ackend.dto.ReservaDTO;
import checkpoint.carreira.ackend.entities.Reserva;
import checkpoint.carreira.ackend.entities.Sala;
import checkpoint.carreira.ackend.entities.StatusReserva;
import checkpoint.carreira.ackend.entities.Usuario;
import checkpoint.carreira.ackend.repository.ReservaRepository;
import checkpoint.carreira.ackend.repository.SalaRepository;
import checkpoint.carreira.ackend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public Reserva criarReserva(ReservaDTO dto) {

        Sala sala = salaRepository.findById(dto.salaId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!sala.isAtiva()){
            throw new IllegalArgumentException("Sala inativa");
        }

        boolean existeConflito = reservaRepository
                .existsBySalaIdAndStatusAndInicioLessThanAndFimGreaterThan(
                        sala.getId(),
                        StatusReserva.ATIVA,
                        dto.fim(),
                        dto.inicio()
                );

        if (existeConflito) {
            throw new IllegalArgumentException("Conflito de horário");
        }

        Reserva nova = new Reserva(
                dto.inicio(),
                dto.fim(),
                sala,
                usuario
        );

        return reservaRepository.save(nova);
    }

    public Page<Reserva> listar(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public void deletar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        reservaRepository.delete(reserva);
    }

    public Reserva atualizarInformacoes(Long id, ReservaDTO dados) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrado"));

        return reserva; // JPA faz o update automaticamente (dirty checking)
    }
}
