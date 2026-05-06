package checkpoint.carreira.ackend.dto;

import checkpoint.carreira.ackend.entities.Sala;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaDTO(@NotBlank
                      @Size(min = 3, max = 100)
                      String nome,

                      @NotNull
                      @Min(1)
                      int capacidade
                      ) {

}
