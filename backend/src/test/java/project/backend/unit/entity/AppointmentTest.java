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
        // Same object comparison
        assertEquals(appointment1, appointment1);

        // Null comparison
        assertNotEquals(null, appointment1);

        // Different class comparison
        assertNotEquals(appointment1, new Object());

        // Different attributes comparison
        assertNotEquals(appointment1, appointment2);

        // Same attributes comparison
        Appointment anotherAppointment = new Appointment(patient, date, "Cardiology", "Dr. Smith", "Health Unit 1", time, 150.0, true);
        assertEquals(appointment1, anotherAppointment);
        assertEquals(appointment1.hashCode(), anotherAppointment.hashCode());

        // Modify one attribute and compare again
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

        appointment2.setMedicalSpecialty("Cardiology");
        assertEquals("Cardiology", appointment2.getMedicalSpecialty());

        appointment2.setDoctorName("Dr. Smith");
        assertEquals("Dr. Smith", appointment2.getDoctorName());

        LocalTime newTime = LocalTime.now();
        appointment2.setTime(newTime);
        assertEquals(newTime, appointment2.getTime());

        appointment2.setDate(new Date());
        assertEquals(new Date(), appointment2.getDate());

        appointment2.setPatient(patient);
        assertEquals(patient, appointment2.getPatient());

        appointment2.setId(1L);
        assertEquals(1L, appointment2.getId());


        appointment2.setCheckedIn(false);
        assertFalse(appointment2.isCheckedIn());


    }
}