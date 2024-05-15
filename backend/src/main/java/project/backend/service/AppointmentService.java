package project.backend.service;


import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public interface AppointmentService {

    void deleteAll();

    Appointment addAppointment(Appointment appointment);

    Appointment setPatient(Appointment appointment, int patientId);

    List<Appointment> getAll();

    List<Appointment> getAppointmentsWithoutPatient();

    Appointment getAppointmentById(Long id);

    List<LocalTime> getAvailableTimes(Date date, String medicalSpeciality, String healthcareUnit);

}
