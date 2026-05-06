package checkpoint.carreira.ackend.service;

import checkpoint.carreira.ackend.dto.DadosAtualizacaoUsuario;
import checkpoint.carreira.ackend.dto.DadosDetalhamentoSala;
import checkpoint.carreira.ackend.dto.SalaDTO;
import checkpoint.carreira.ackend.entities.Sala;
import checkpoint.carreira.ackend.entities.Usuario;
import checkpoint.carreira.ackend.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala cadastrar(SalaDTO dto) {
        return salaRepository.save(new Sala(dto.nome(), dto.capacidade()));
    }

    public Page<Sala> listar(Pageable paginacao) {
        return salaRepository.findAll(paginacao);
    }

    public void deletar(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        salaRepository.delete(sala);
    }

    public Sala atualizarInformacoes(Long id, DadosDetalhamentoSala dados) {

        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrado"));

        if (dados.nome() != null) {
            sala.setNome(dados.nome());
        }

        if (dados.capacidade() != null) {
            sala.setCapacidade(dados.capacidade());
        }

        return sala; // JPA faz o update automaticamente (dirty checking)
    }
}
