package project.backend.unit.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Patient patient;
    private Date date;
    private LocalTime time;
    private Appointment appointment1;
    private Appointment appointment2;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setNumUtente(123456789);

        date = new Date();
        time = LocalTime.now();

        appointment1 = new Appointment(patient, date, "Cardiology", "Dr. Smith", "Health Unit 1", time, 150.0, true);
        appointment2 = new Appointment(date, "Dermatology", "Dr. Johnson", "Health Unit 2", time, 100.0);
    }

    @Test
    void testAppointmentCreation() {
        assertNotNull(appointment1);
        assertNotNull(appointment2);
        assertEquals("Cardiology", appointment1.getMedicalSpecialty());
        assertEquals("Dermatology", appointment2.getMedicalSpecialty());
    }

    @Test
    void testEqualsAndHashCode() {
        Appointment anotherAppointment = new Appointment(patient, date, "Cardiology", "Dr. Smith", "Health Unit 1", time, 150.0, true);

        assertEquals(appointment1, anotherAppointment);
        assertEquals(appointment1.hashCode(), anotherAppointment.hashCode());

        anotherAppointment.setDoctorName("Dr. Johnson");
        assertNotEquals(appointment1, anotherAppointment);
        assertNotEquals(appointment1.hashCode(), anotherAppointment.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        appointment2.setCheckedIn(true);
        assertTrue(appointment2.isCheckedIn());

        appointment2.setPrice(200.0);
        assertEquals(200.0, appointment2.getPrice());
    }
}