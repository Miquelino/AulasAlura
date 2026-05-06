package checkpoint.carreira.ackend.dto;

import checkpoint.carreira.ackend.entities.Usuario;

public record UsuarioDetalhamentoDTO(Long id,
                                     String nome,
                                     String email,
                                     int idade,
                                     String cpf
                                     ) {

    public UsuarioDetalhamentoDTO(Usuario usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getIdade(),
                usuario.getCpf());
    }
}
