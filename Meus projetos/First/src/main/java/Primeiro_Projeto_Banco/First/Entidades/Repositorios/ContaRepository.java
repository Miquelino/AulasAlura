package Primeiro_Projeto_Banco.First.Entidades.Repositorios;

import Primeiro_Projeto_Banco.First.Entidades.Conta.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Page<Conta> findAll(Pageable pageable);
    @Query("SELECT MAX(c.numero) FROM Conta c")
    Integer findMaxNumero();
}
