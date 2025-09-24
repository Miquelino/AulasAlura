package med.voll.api.doctor;

// isso é um DTO para retornar
public record dataListMedical(Long id, String name, String email, String crm, Specialty specialty) {

    public dataListMedical(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
