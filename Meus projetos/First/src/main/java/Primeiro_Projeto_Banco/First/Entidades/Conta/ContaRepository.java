package Primeiro_Projeto_Banco.First.Entidades.Conta;

import org.springframework.data.jpa.repository.Query;

public interface ContaRepository {
    @Query("SELECT MAX(c.numero) FROM Conta c")
    Integer findMaxNumero();
}
