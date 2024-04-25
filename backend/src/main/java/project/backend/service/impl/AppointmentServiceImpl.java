package project.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;

    private final PatientRepo patientRepo;


    @Autowired
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, PatientRepo patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
    }

    @Override
    public void deleteAll() {
        appointmentRepo.deleteAll();
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    @Override
    public Appointment setPatient(Appointment appointment, int patientId) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        appointment.setPatient(patient);
        appointmentRepo.save(appointment);
        return appointment;

    }

}
