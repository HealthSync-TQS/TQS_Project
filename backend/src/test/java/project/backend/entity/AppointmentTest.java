package project.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * Test class for the {@link Appointment} entity.
 * This class provides unit tests that validate the functionality of
 * the getters, setters, and constructors of the {@link Appointment} entity.
 */
public class AppointmentTest {

    /**
     * Tests the getters and setters of the {@link Appointment} class.
     * Ensures that each field is properly set and retrieved.
     */
    @Test
    public void testGettersAndSetters() {
        Patient mockPatient = mock(Patient.class); // Mock Patient object
        Appointment appointment = new Appointment();

        Date date = new Date();
        double price = 200.00;
        String medicalSpecialty = "Cardiology";
        String doctorName = "Doctor Rafael";
        String healthcareUnit = "Central Hospital";
        LocalTime time = LocalTime.of(14, 30);

        // Setting properties
        appointment.setPatient(mockPatient);
        appointment.setDate(date);
        appointment.setPrice(price);
        appointment.setMedicalSpecialty(medicalSpecialty);
        appointment.setDoctorName(doctorName);
        appointment.setHealthcareUnit(healthcareUnit);
        appointment.setTime(time);

        // Asserting properties
        assertEquals(mockPatient, appointment.getPatient());
        assertEquals(date, appointment.getDate());
        assertEquals(price, appointment.getPrice());
        assertEquals(medicalSpecialty, appointment.getMedicalSpecialty());
        assertEquals(doctorName, appointment.getDoctorName());
        assertEquals(healthcareUnit, appointment.getHealthcareUnit());
        assertEquals(time, appointment.getTime());
    }

    /**
     * Tests the constructor of the {@link Appointment} class with a patient
     * instance included.
     * Verifies that the constructor correctly assigns values with a patient
     * reference.
     */
    @Test
    public void testAppointmentWithPatient() {
        Patient mockPatient = mock(Patient.class);

        Date date = new Date();
        double price = 200.00;
        String medicalSpecialty = "Cardiology";
        String doctorName = "Doctor Rafael";
        String healthcareUnit = "Central Hospital";
        LocalTime time = LocalTime.of(14, 30);

        Appointment appointmentWithPatient = new Appointment(mockPatient, date, medicalSpecialty, doctorName,
                healthcareUnit, time, price);

        // Asserting constructor properties
        assertEquals(mockPatient, appointmentWithPatient.getPatient());
        assertEquals(date, appointmentWithPatient.getDate());
        assertEquals(price, appointmentWithPatient.getPrice());
        assertEquals(medicalSpecialty, appointmentWithPatient.getMedicalSpecialty());
        assertEquals(doctorName, appointmentWithPatient.getDoctorName());
        assertEquals(healthcareUnit, appointmentWithPatient.getHealthcareUnit());
        assertEquals(time, appointmentWithPatient.getTime());

    }

    /**
     * Tests the constructor of the {@link Appointment} class without a patient
     * instance.
     * Verifies that the constructor correctly assigns values when no patient is
     * linked.
     */
    @Test
    public void testAppointmentWithoutPatient() {

        Date date = new Date();
        double price = 200.00;
        String medicalSpecialty = "Cardiology";
        String doctorName = "Doctor Rafael";
        String healthcareUnit = "Central Hospital";
        LocalTime time = LocalTime.of(14, 30);

        Appointment appointmentWithoutPatient = new Appointment(date, medicalSpecialty, doctorName, healthcareUnit,
                time, price);

        // Asserting that patient is null and other properties are set correctly
        assertNull(appointmentWithoutPatient.getPatient());
        assertEquals(date, appointmentWithoutPatient.getDate());
        assertEquals(price, appointmentWithoutPatient.getPrice());
        assertEquals(medicalSpecialty, appointmentWithoutPatient.getMedicalSpecialty());
        assertEquals(doctorName, appointmentWithoutPatient.getDoctorName());
        assertEquals(healthcareUnit, appointmentWithoutPatient.getHealthcareUnit());
        assertEquals(time, appointmentWithoutPatient.getTime());

    }
}
