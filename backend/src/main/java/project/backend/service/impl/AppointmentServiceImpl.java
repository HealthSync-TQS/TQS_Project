package project.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.AppointmentService;

import java.util.List;

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
    public Appointment addAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
        return appointment;
    }

    @Override
    public Appointment setPatient(Appointment appointment, int patientId) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        appointment.setPatient(patient);
        appointmentRepo.save(appointment);
        return appointment;

    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsWithoutPatient() {
        return appointmentRepo.findByPatientIsNull();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientUtente) {
        return appointmentRepo.findByPatientNumUtente(patientUtente);
    }

    @Override
    public Appointment updatePayment(Long id, boolean paid) {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        if (appointment != null){
            appointment.setPaid(paid);
            appointmentRepo.save(appointment);
            return appointment;
        }

        return null;
    }

    @Override
    public Appointment updateCheckIn(Long id, boolean checkedIn) {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        if (appointment != null){
            appointment.setCheckedIn(checkedIn);
            appointmentRepo.save(appointment);
            return appointment;
        }

        return null;
    }
}
