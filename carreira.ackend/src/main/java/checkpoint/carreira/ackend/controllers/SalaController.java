package checkpoint.carreira.ackend.controllers;

import checkpoint.carreira.ackend.dto.DadosDetalhamentoSala;
import checkpoint.carreira.ackend.dto.SalaDTO;
import checkpoint.carreira.ackend.dto.UsuarioDetalhamentoDTO;
import checkpoint.carreira.ackend.entities.Sala;
import checkpoint.carreira.ackend.service.SalaService;
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
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<DadosDetalhamentoSala> cadastrarSala(@RequestBody @Valid SalaDTO dto,
                                        UriComponentsBuilder uriBuilder) {

        Sala sala = salaService.cadastrar(dto);

        var uri = uriBuilder.path("/salas/{id}")
                .buildAndExpand(sala.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoSala(sala));
    }

    @GetMapping("/listarSalas")
    public ResponseEntity<Page<DadosDetalhamentoSala>> listarSala(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){

        Page<DadosDetalhamentoSala> page = salaService
                .listar(paginacao)
                .map(DadosDetalhamentoSala::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Void> deletarSala(@PathVariable("id") Long id) {
        salaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarSala/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoSala> atualizarSala(
            @PathVariable Long id,
            @RequestBody DadosDetalhamentoSala dados) {

        Sala sala = salaService.atualizarInformacoes(id, dados);

        return ResponseEntity.ok(new DadosDetalhamentoSala(sala));
    }


}
