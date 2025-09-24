package med.voll.api.Adress;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adress {

    private String street;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Adress(AdressData adressData) {
        this.street = adressData.street();
        this.neighborhood = adressData.neighborhood();
        this.zipCode = adressData.zipCode();
        this.state = adressData.state();
        this.complement = adressData.complement();
        this.city = adressData.city();
        this.number = adressData.number();
    }

    public void updateInformation(AdressData adressData) {
        if (adressData.street() != null) {
            this.street = adressData.street();
        }
        if (adressData.neighborhood() != null) {
            this.neighborhood = adressData.neighborhood();
        }
        if (adressData.zipCode() != null) {
            this.zipCode = adressData.zipCode();
        }
        if (adressData.state() != null) {
            this.state = adressData.state();
        }
        if (adressData.complement() != null) {
            this.complement = adressData.complement();
        }
        if (adressData.complement() != null) {
            this.complement = adressData.complement();
        }
        if (adressData.city() != null) {
            this.city = adressData.city();
        }
        if (adressData.number() != null) {
            this.number = adressData.number();
        }
    }
}
