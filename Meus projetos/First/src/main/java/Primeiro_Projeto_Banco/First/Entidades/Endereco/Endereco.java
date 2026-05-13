package Primeiro_Projeto_Banco.First.Entidades.Endereco;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Endereco")
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rua;
    private String bairro;
    private int numero;
    private int cep;
    private String cidade;
    private String estado;

    @OneToOne
    private Cliente cliente;


}
