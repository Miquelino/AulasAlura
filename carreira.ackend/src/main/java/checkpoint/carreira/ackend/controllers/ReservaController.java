package checkpoint.carreira.ackend.controllers;

import checkpoint.carreira.ackend.dto.DadosAtualizacaoUsuario;
import checkpoint.carreira.ackend.dto.ReservaDTO;
import checkpoint.carreira.ackend.dto.UsuarioDetalhamentoDTO;
import checkpoint.carreira.ackend.entities.Reserva;
import checkpoint.carreira.ackend.entities.Usuario;
import checkpoint.carreira.ackend.service.ReservaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservasala")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping("/reservar")
    public ResponseEntity cadastrar(
            @RequestBody @Valid ReservaDTO reservaDTO,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = service.criarReserva(reservaDTO).getUsuario();

        var uri = uriBuilder.path("/reservasala/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new UsuarioDetalhamentoDTO(usuario));
    }

    @GetMapping("/listarReservas")
    public ResponseEntity<Page<ReservaDTO>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<ReservaDTO> page = service
                .listar(paginacao)
                .map(ReservaDTO::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarUsuario/{id}")
    @Transactional
    public ResponseEntity<ReservaDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ReservaDTO dados) {

        Reserva reserva = service.atualizarInformacoes(id, dados);

        return ResponseEntity.ok(new ReservaDTO(reserva));
    }
}
