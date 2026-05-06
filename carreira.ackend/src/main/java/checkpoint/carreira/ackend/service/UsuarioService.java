package checkpoint.carreira.ackend.service;

import checkpoint.carreira.ackend.dto.DadosAtualizacaoUsuario;
import checkpoint.carreira.ackend.dto.UsuarioDTO;
import checkpoint.carreira.ackend.entities.Usuario;
import checkpoint.carreira.ackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto);
        usuario.validar();
        return repository.save(usuario);
    }

    public Page<Usuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public void deletar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuario);
    }

    public Usuario atualizarInformacoes(Long id, DadosAtualizacaoUsuario dados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dados.nome() != null) {
            usuario.setNome(dados.nome());
        }

        if (dados.email() != null) {
            usuario.setEmail(dados.email());
        }

        if (dados.idade() != null) {
            usuario.setIdade(dados.idade());
        }

        return usuario; // JPA faz o update automaticamente (dirty checking)
    }
    
}
