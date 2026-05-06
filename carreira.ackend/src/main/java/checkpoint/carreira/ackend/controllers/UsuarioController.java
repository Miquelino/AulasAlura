package checkpoint.carreira.ackend.controllers;

import checkpoint.carreira.ackend.dto.DadosAtualizacaoUsuario;
import checkpoint.carreira.ackend.dto.UsuarioDTO;
import checkpoint.carreira.ackend.dto.UsuarioDetalhamentoDTO;
import checkpoint.carreira.ackend.entities.Usuario;
import checkpoint.carreira.ackend.service.UsuarioService;
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
@RequestMapping()
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrarUsuarios")
    public ResponseEntity<UsuarioDetalhamentoDTO> cadastrar(
            @RequestBody @Valid UsuarioDTO usuarioDTO,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = service.cadastrar(usuarioDTO);

        var uri = uriBuilder.path("/reservasala/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new UsuarioDetalhamentoDTO(usuario));
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<Page<UsuarioDetalhamentoDTO>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<UsuarioDetalhamentoDTO> page = service
                .listar(paginacao)
                .map(UsuarioDetalhamentoDTO::new);

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
    public ResponseEntity<UsuarioDetalhamentoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoUsuario dados) {

        Usuario usuario = service.atualizarInformacoes(id, dados);

        return ResponseEntity.ok(new UsuarioDetalhamentoDTO(usuario));
    }

}
