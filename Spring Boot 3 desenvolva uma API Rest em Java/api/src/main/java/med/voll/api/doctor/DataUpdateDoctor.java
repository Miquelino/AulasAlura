package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Adress.Adress;
import med.voll.api.Adress.AdressData;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        AdressData adressData) {
}
