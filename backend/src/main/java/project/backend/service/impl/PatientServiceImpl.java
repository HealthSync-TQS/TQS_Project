package project.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.Patient;
import project.backend.repository.PatientRepo;
import project.backend.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public void deleteAll() {
        patientRepo.deleteAll();
    }

    @Override
    public void addPatient(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public Patient getPatientById(int id) {
        return patientRepo.findById(id).orElse(null);
    }

}
