package project.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.AppointmentService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    public HashMap<Long, LocalTime> getAvailableAppointments(Date date, String medicalSpeciality, String healthcareUnit) {
        List<Appointment> appointments = appointmentRepo.findByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit(date, medicalSpeciality, healthcareUnit);
        System.out.println(appointments);
        HashMap<Long, LocalTime> availableTimes = new HashMap<Long, LocalTime>();
        for (Appointment appointment : appointments) {
            LocalTime time = appointment.getTime();
            Long id = appointment.getId(); 
            availableTimes.put(id, time);
        }
        return availableTimes;
    }
}
