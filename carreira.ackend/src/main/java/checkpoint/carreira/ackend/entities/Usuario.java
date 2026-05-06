package checkpoint.carreira.ackend.entities;

import checkpoint.carreira.ackend.dto.DadosAtualizacaoUsuario;
import checkpoint.carreira.ackend.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int idade;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String cpf;

    public Usuario() {
    }

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.idade = dto.idade();
        this.cpf = dto.cpf();
    }

    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (idade <= 0) {
            throw new IllegalArgumentException("Idade inválida");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
    }
}
