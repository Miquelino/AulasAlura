package adopet.api.repository;

import adopet.api.model.Adocao;
import adopet.api.model.StatusAdocao;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao,Long> {

    Integer countByTutorIdAndStatus(@NotNull Long idPet, StatusAdocao statusAdocao);

    Boolean existsByPetIdAndStatus(@NotNull Long idTutor, StatusAdocao statusAdocao);
}
