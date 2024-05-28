package project.backend.unit.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.rest.AppointmentController;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private AppointmentController appointmentController;

    private Appointment appointment;
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setNumUtente(123456789);

        appointment = new Appointment(patient, new Date(), "Cardiology", "Dr. Smith", "Health Unit 1", LocalTime.now(), 150.0, true);
        appointment.setId(1L);
    }

    @Test
    void testGetAppointments() {
        when(appointmentService.getAll()).thenReturn(Collections.singletonList(appointment));
        ResponseEntity<List<Appointment>> response = appointmentController.getAppointments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Cardiology", response.getBody().get(0).getMedicalSpecialty());

        when(appointmentService.getAll()).thenThrow(new RuntimeException());
        ResponseEntity<List<Appointment>> errorResponse = appointmentController.getAppointments();
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());


    }

    @Test
    void testCreateAppointment() {
        when(appointmentService.addAppointment(any(Appointment.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.createAppointment(appointment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cardiology", Objects.requireNonNull(response.getBody()).getMedicalSpecialty());

        when(appointmentService.addAppointment(any(Appointment.class))).thenThrow(new RuntimeException());
        ResponseEntity<Appointment> errorResponse = appointmentController.createAppointment(appointment);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }

    @Test
    void testGetAppointmentsWithoutPatient() {
        when(appointmentService.getAppointmentsWithoutPatient()).thenReturn(Collections.singletonList(appointment));

        ResponseEntity<List<Appointment>> response = appointmentController.getAppointmentsWithoutPatient();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Cardiology", response.getBody().get(0).getMedicalSpecialty());


        when(appointmentService.getAppointmentsWithoutPatient()).thenThrow(new RuntimeException());
        ResponseEntity<List<Appointment>> errorResponse = appointmentController.getAppointmentsWithoutPatient();
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());

    }

    @Test
    void testSetPatient() {
        when(appointmentService.getAppointmentById(anyLong())).thenReturn(appointment);
        when(patientService.getPatientById(any(Integer.class))).thenReturn(patient);
        when(appointmentService.setPatient(any(Appointment.class), any(Integer.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.setPatient(1L, 12345);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cardiology", Objects.requireNonNull(response.getBody()).getMedicalSpecialty());

        when(appointmentService.getAppointmentById(anyLong())).thenThrow(new RuntimeException());
        ResponseEntity<Appointment> errorResponse = appointmentController.setPatient(1L, 12345);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }

    @Test
    void testGetAppointmentById() {
        when(appointmentService.getAppointmentById(anyLong())).thenReturn(appointment);

        ResponseEntity<List<Appointment>> response = appointmentController.getAppointment(1L, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Cardiology", response.getBody().get(0).getMedicalSpecialty());

        ResponseEntity<List<Appointment>> response1 = appointmentController.getAppointment(null, 12345);

        assertEquals(HttpStatus.OK, response1.getStatusCode());

        ResponseEntity<List<Appointment>> response2 = appointmentController.getAppointment(null, null);

        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());

        when(appointmentService.getAppointmentById(anyLong())).thenThrow(new RuntimeException());
        ResponseEntity<List<Appointment>> errorResponse = appointmentController.getAppointment(1L, null);
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
    }

    @Test
    void testSetPayment() {
        when(appointmentService.updatePayment(anyLong(), any(Boolean.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.setPayment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cardiology", Objects.requireNonNull(response.getBody()).getMedicalSpecialty());

        when(appointmentService.updatePayment(anyLong(), any(Boolean.class))).thenThrow(new RuntimeException());
        ResponseEntity<Appointment> errorResponse = appointmentController.setPayment(1L);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());

    }

    @Test
    void testSetCheckIn() {
        when(appointmentService.updateCheckIn(anyLong(), any(Boolean.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.setCheckIn(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cardiology", Objects.requireNonNull(response.getBody()).getMedicalSpecialty());


        when(appointmentService.updateCheckIn(anyLong(), any(Boolean.class))).thenThrow(new RuntimeException());
        ResponseEntity<Appointment> errorResponse = appointmentController.setCheckIn(1L);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }
}
