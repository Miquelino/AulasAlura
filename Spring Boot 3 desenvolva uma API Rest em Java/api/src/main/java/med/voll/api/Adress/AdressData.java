package med.voll.api.Adress;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdressData(
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        @Column(name = "zip_code", nullable = false)
        String zipCode,
        @NotBlank
        String city,
        @NotBlank
        String state,

        String complement,

        String number) {
}
