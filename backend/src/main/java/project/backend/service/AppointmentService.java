package project.backend.service;


import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;

@Service
public interface AppointmentService {

    void deleteAll();

    void addAppointment(Appointment appointment);

    Appointment setPatient(Appointment appointment, int patientId);



}
