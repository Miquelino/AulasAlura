package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.DataListPatient;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientData;
import med.voll.api.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid Patient data){
        repository.save(new Patient());
    }

    @GetMapping
    public Page<DataListPatient> listar(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DataListPatient::new);
    }
}
