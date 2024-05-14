package project.backend.integration.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.service.impl.AppointmentServiceImpl;
import project.backend.service.impl.PatientServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AppointmentServiceIT {

    @Autowired
    private AppointmentServiceImpl appointmentService;


    @Autowired
    private PatientServiceImpl patientService;



    @BeforeEach
    public void cleanDatabase() {
        appointmentService.deleteAll();
        patientService.deleteAll();
    }


    @Test
    public void testAddAppointmentWithPatient() {
        Patient patient = new Patient(123, "John Doe", "john@example.com");
        patientService.addPatient(patient);

        Appointment appointment = new Appointment(patient, new Date(), "Cardiology", "Dr. Smith", 100.0);
        Appointment addedAppointment = appointmentService.addAppointment(appointment);

        assertNotNull(addedAppointment.getId());
        assertEquals(appointment.getPatient(), addedAppointment.getPatient());
    }


    @Test
    public void testAddAppointmentWithoutPatient() {

        Appointment appointment = new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0);
        Appointment addedAppointment = appointmentService.addAppointment(appointment);

        assertNotNull(addedAppointment.getId());
        assertNull(addedAppointment.getPatient());
    }

    @Test
    public void testSetPatient() {
        Patient patient = new Patient(123, "John Doe", "john@example.com");
        patientService.addPatient(patient);

        Appointment appointment = new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0);
        appointmentService.addAppointment(appointment);

        Appointment updatedAppointment = appointmentService.setPatient(appointment, patient.getNumUtente());

        assertEquals(patient.getNumUtente(), updatedAppointment.getPatient().getNumUtente());
        assertEquals(appointment.getDate(), updatedAppointment.getDate());

    }

    @Test
    public void testGetAllAppointments() {
        Patient patient = new Patient(123, "John Doe", "john@example.com");
        patientService.addPatient(patient);

        Appointment appointment1 = new Appointment(patient, new Date(), "Cardiology", "Dr. Smith", 100.0);
        Appointment appointment2 = new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0);

        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);

        assertEquals(2, appointmentService.getAll().size());

    }


    @Test
    public void testGetAppointments() {
        Patient patient = new Patient(123, "John Doe", "john@example.com");
        patientService.addPatient(patient);

        List<Appointment> appointments = Arrays.asList(
                new Appointment(patient, new Date(), "Cardiology", "Dr. Smith", 100.0),
                new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0),
                new Appointment(new Date(), "Neurology", "Dr. Brown", 200.0)
        );

        appointments.forEach(appointmentService::addAppointment);

        assertEquals(2, appointmentService.getAppointmentsWithoutPatient().size());


    }


    @Test
    public void testGetAppointmentById() {

        Appointment addedAppointment = appointmentService.addAppointment(new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0));

        Long appointmentId = addedAppointment.getId();
        Appointment retrievedAppointment = appointmentService.getAppointmentById(appointmentId);

        assertNotNull(retrievedAppointment);
        assertEquals(addedAppointment, retrievedAppointment);


    }

    @Test
    public void testDeleteAll() {
        appointmentService.addAppointment(new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0));
        appointmentService.addAppointment(new Appointment(new Date(), "Cardiology", "Dr. Smith", 100.0));

        appointmentService.deleteAll();

        assertEquals(0, appointmentService.getAll().size());
    }







}
