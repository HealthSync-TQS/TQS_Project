package project.backend.service;


import org.springframework.stereotype.Service;
import project.backend.entity.Patient;

@Service
public interface PatientService {


    void deleteAll();

    void addPatient(Patient patient);

    Patient getPatientById(int id);
}
