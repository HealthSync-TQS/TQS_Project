package project.backend.unit.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.AppointmentService;
import project.backend.service.impl.AppointmentServiceImpl;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {


    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private PatientRepo patientRepo;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;


    @Test
    public void addAppointmentWithPatientTest() {
        Patient patient = new Patient(123, "John Doe", "john@example.com");
        Appointment appointment = new Appointment(patient, new Date(), "Cardiology", "Dr. Smith", "Centro de Saude Delta",  LocalTime.now(), 100.0, false);
        when(appointmentRepo.save(appointment)).thenReturn(appointment);

        Appointment addedAppointment = appointmentService.addAppointment(appointment);

        assertEquals(appointment, addedAppointment);
        verify(appointmentRepo, times(1)).save(appointment);


    }

    @Test
    public void addAppointmentWithoutPatientTest() {
        Appointment appointment = new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0);
        when(appointmentRepo.save(appointment)).thenReturn(appointment);

        Appointment addedAppointment = appointmentService.addAppointment(appointment);

        assertEquals(appointment, addedAppointment);
        verify(appointmentRepo, times(1)).save(appointment);


    }


    @Test
    public void setPatientTest() {
        Patient patient = new Patient(123456789, "John Doe", "john@example.com");
        Appointment appointment = new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0);
        when(patientRepo.findById(123456789)).thenReturn(Optional.of(patient));
        when(appointmentRepo.save(appointment)).thenReturn(appointment);

        Appointment updatedAppointment = appointmentService.setPatient(appointment, patient.getNumUtente());

        assertEquals(patient, updatedAppointment.getPatient());
        verify(patientRepo, times(1)).findById(patient.getNumUtente());
        verify(appointmentRepo, times(1)).save(appointment);
    }

    @Test
    public void setPatientNotFoundTest() {
        int patientId = 123;
        Appointment appointment = new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0);
        when(patientRepo.findById(patientId)).thenReturn(Optional.empty());

        Appointment updatedAppointment = appointmentService.setPatient(appointment, patientId);

        assertNull(updatedAppointment.getPatient());
        verify(patientRepo, times(1)).findById(patientId);
        verify(appointmentRepo, times(1)).save(appointment);
    }


    @Test
    public void getAllAppointmentsTest() {
        List<Appointment> appointments = Arrays.asList(
                new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0),
                new Appointment(new Date(124, 10, 12), "Dermatology", "Dr. Johnson", "Centro de Saude Delta", LocalTime.now(),150.0)
        );
        when(appointmentRepo.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAll();

        assertEquals(appointments, result);
        verify(appointmentRepo, times(1)).findAll();
    }

    @Test
    public void getAppointmentsWithoutPatientTest() {
        List<Appointment> appointments = Arrays.asList(
                new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0),
                new Appointment(new Date(124, 10, 12), "Dermatology", "Dr. Johnson", "Centro de Saude Delta", LocalTime.now(),150.0)
                );
        when(appointmentRepo.findByPatientIsNull()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAppointmentsWithoutPatient();

        assertEquals(appointments, result);
        verify(appointmentRepo, times(1)).findByPatientIsNull();
    }

    @Test
    public void getAppointmentByIdTest() {
        Long appointmentId = 1L;
        Appointment appointment = new Appointment(new Date(124, 10, 12), "Dermatology", "Dr. Johnson", "Centro de Saude Delta", LocalTime.now(),150.0);
        when(appointmentRepo.findById(appointmentId)).thenReturn(Optional.of(appointment));

        Appointment result = appointmentService.getAppointmentById(appointmentId);

        assertEquals(appointment, result);
        verify(appointmentRepo, times(1)).findById(appointmentId);
    }

    @Test
    public void getAppointmentByIdNotFoundTest() {
        Long appointmentId = 1L;
        when(appointmentRepo.findById(appointmentId)).thenReturn(Optional.empty());

        Appointment result = appointmentService.getAppointmentById(appointmentId);

        assertNull(result);
        verify(appointmentRepo, times(1)).findById(appointmentId);
    }

    @Test
    public void deleteAllAppointmentsTest() {
        doNothing().when(appointmentRepo).deleteAll();

        appointmentService.deleteAll();

        verify(appointmentRepo, times(1)).deleteAll();
    }

}