package med.voll.api.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Adress.Adress;
import med.voll.api.Adress.AdressData;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Adress adress;

    private Boolean active;

    public Doctor(RegisteredDoctorData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.adress = new Adress(data.adressData());
    }

    public void updateInformation(DataUpdateDoctor data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null){
            this.phone = data.phone();
        }
        if (data.adressData() != null){
            this.adress.updateInformation(data.adressData());
        }
    }

    public void delete() {
        this.active = false;
    }
}
