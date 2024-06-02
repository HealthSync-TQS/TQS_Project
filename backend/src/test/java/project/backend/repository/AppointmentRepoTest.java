package project.backend.repository;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import project.backend.entity.Appointment;
import project.backend.entity.Patient;

/**
 * Class for testing Appointment repository operations.
 * This class uses {@link DataJpaTest} for testing the database interactions of
 * the {@link AppointmentRepo}.
 */
@DataJpaTest
public class AppointmentRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepo appointmentRepo;

    Appointment appointment;

    Patient patient;

    /**
     * Sets up each test with a common appointment configuration.
     */
    @BeforeEach
    public void setUp() {
        Date date = new Date();
        double price = 200.00;
        String medicalSpecialty = "Cardiology";
        String doctorName = "Doctor Rafael";
        String healthcareUnit = "Central Hospital";
        LocalTime time = LocalTime.of(14, 30);

        appointment = new Appointment(date, medicalSpecialty, doctorName, healthcareUnit, time, price, false);

        patient = new Patient();
        patient.setNumUtente(123456);
    }

    /**
     * Tests finding appointments where the patient is null.
     */
    @Test
    public void testFindByPatientIsNull_thenReturnAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientIsNull();

        assertThat(found).contains(appointment);
    }

    /**
     * Tests that no appointments are returned when the patient is not null.
     */
    @Test
    public void testFindByPatientIsNull_negative_thenReturnEmpty() {

        entityManager.persist(patient);

        appointment.setPatient(patient);

        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientIsNull();

        assertThat(found).doesNotContain(appointment);
    }

    /**
     * Tests finding multiple appointments where the patient is null.
     */
    @Test
    public void testFindByPatientIsNull_mmoreThanOneAppointment_thenReturnMoreThanOneAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        Appointment appointment2 = new Appointment();
        entityManager.persist(appointment2);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientIsNull();

        assertThat(found).contains(appointment, appointment2);
    }

    /**
     * Tests finding appointments by patient's NumUtente when it exists.
     */
    @Test
    public void testFindByPatientNumUtente_thenReturnAppointment() {
        entityManager.persist(patient);

        appointment.setPatient(patient);

        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientNumUtente(123456);

        assertThat(found).contains(appointment);
    }

    /**
     * Tests that appointments are not found when an invalid NumUtente is provided.
     */
    @Test
    public void testFindByPatientNumUtente_invalidId_thenReturnEmpty() {
        entityManager.persist(patient);

        appointment.setPatient(patient);

        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientNumUtente(123457);

        assertThat(found).doesNotContain(appointment);
    }

    /**
     * Tests finding multiple appointments by patient's NumUtente.
     */
    @Test
    public void testFindByPatientNumUtente_moreThanOneAppointment_thenReturnMoreThanOneAppointment() {
        entityManager.persist(patient);

        appointment.setPatient(patient);

        entityManager.persist(appointment);
        entityManager.flush();

        Appointment appointment2 = new Appointment();
        appointment2.setPatient(patient);
        entityManager.persist(appointment2);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientNumUtente(123456);

        assertThat(found).contains(appointment, appointment2);
    }

    /**
     * Tests finding appointments by doctor's name when it exists.
     */
    @Test
    public void testFindByDoctorName_thenReturnAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByDoctorName("Doctor Rafael");

        assertThat(found).contains(appointment);
    }

    /**
     * Tests that appointments are not found when an invalid doctor name is
     * provided.
     */
    @Test
    public void testFindByDoctorName_invalidName_thenReturnEmpty() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByDoctorName("Doctor Rafaela");

        assertThat(found).doesNotContain(appointment);
    }

    /**
     * Tests finding multiple appointments by doctor's name.
     */
    @Test
    public void testFindByDoctorName_moreThanOneAppointment_thenReturnMoreThanOneAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        Appointment appointment2 = new Appointment();
        appointment2.setDoctorName("Doctor Rafael");
        entityManager.persist(appointment2);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByDoctorName("Doctor Rafael");

        assertThat(found).contains(appointment, appointment2);
    }

        @Test
    public void testFindByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit_thenReturnAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit(
                appointment.getDate(), appointment.getMedicalSpeciality(), appointment.getHealthcareUnit());

        assertThat(found).contains(appointment);
    }

    @Test
    public void testFindByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit_invalidParams_thenReturnEmpty() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit(
                appointment.getDate(), "InvalidSpeciality", appointment.getHealthcareUnit());

        assertThat(found).isEmpty();
    }

    @Test
    public void testFindByPatient_NumUtente_thenReturnAppointment() {
        entityManager.persist(patient);

        appointment.setPatient(patient);
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientNumUtente(patient.getNumUtente());

        assertThat(found).contains(appointment);
    }

    @Test
    public void testFindByPatient_NumUtente_invalidId_thenReturnEmpty() {
        entityManager.persist(patient);

        appointment.setPatient(patient);
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByPatientNumUtente(999999);

        assertThat(found).isEmpty();
    }

    @Test
    public void testFindByDate_thenReturnAppointment() {
        entityManager.persist(appointment);
        entityManager.flush();

        List<Appointment> found = appointmentRepo.findByDate(appointment.getDate());

        assertThat(found).contains(appointment);
    }

    @Test
    public void testFindByDate_invalidDate_thenReturnEmpty() {
        entityManager.persist(appointment);
        entityManager.flush();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1);
        Date invalidDate = calendar.getTime();

        List<Appointment> found = appointmentRepo.findByDate(invalidDate);

        assertThat(found).isEmpty();
    }


}
