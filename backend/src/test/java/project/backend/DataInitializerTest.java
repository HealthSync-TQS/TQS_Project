package project.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import project.backend.DataInitializer;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.PatientRepo;
import project.backend.service.impl.AppointmentServiceImpl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DataInitializerIT {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() throws Exception {
        appointmentService.deleteAll();
        patientRepo.deleteAll();
        dataInitializer.run(); // Re-inicializa os dados antes de cada teste
    }

    @Test
    void testPatientsInitialized() {
        List<Patient> patients = patientRepo.findAll();
        assertEquals(5, patients.size());

        assertTrue(patients.stream().anyMatch(p -> p.getNumUtente() == 1001 && p.getName().equals("John Doe")));
        assertTrue(patients.stream().anyMatch(p -> p.getNumUtente() == 1002 && p.getName().equals("Jane Doe")));
        assertTrue(patients.stream().anyMatch(p -> p.getNumUtente() == 1003 && p.getName().equals("Alice Johnson")));
        assertTrue(patients.stream().anyMatch(p -> p.getNumUtente() == 1004 && p.getName().equals("Bob Brown")));
        assertTrue(patients.stream().anyMatch(p -> p.getNumUtente() == 1005 && p.getName().equals("Charlie Davis")));
    }

    @Test
    void testAppointmentsInitialized() {
        List<Appointment> appointments = appointmentService.getAll();
        assertEquals(12, appointments.size());

        assertTrue(appointments.stream().anyMatch(a -> a.getDate() != null && a.getMedicalSpeciality().equals("Cardiology") && a.getDoctorName().equals("Dr. Smith")));
        assertTrue(appointments.stream().anyMatch(a -> a.getMedicalSpeciality().equals("Dermatology") && a.getDoctorName().equals("Dr. Jones") && a.getHealthcareUnit().equals("USF Alfa")));
        assertTrue(appointments.stream().anyMatch(a -> a.getMedicalSpeciality().equals("Pediatry") && a.getDoctorName().equals("Dr. White") && a.getHealthcareUnit().equals("USF Beta")));
        assertTrue(appointments.stream().anyMatch(a -> a.getMedicalSpeciality().equals("Ginecology") && a.getDoctorName().equals("Dr. Black") && a.getHealthcareUnit().equals("USF Gama")));
        assertTrue(appointments.stream().anyMatch(a -> a.getMedicalSpeciality().equals("Cardiology") && a.getDoctorName().equals("Dr. Taylor") && a.getHealthcareUnit().equals("Centro de Saúde Delta")));
    }

    @Test
    void testDeleteAllData() {
        patientRepo.deleteAll();
        appointmentService.deleteAll();

        assertEquals(0, patientRepo.findAll().size());
        assertEquals(0, appointmentService.getAll().size());
    }

    @Test
    void testAddAndRetrievePatient() {
        Patient newPatient = new Patient(2001, "New Patient", "new.patient@example.com");
        patientRepo.save(newPatient);

        Patient retrievedPatient = patientRepo.findById(2001).orElse(null);
        assertNotNull(retrievedPatient);
        assertEquals("New Patient", retrievedPatient.getName());
    }

    @Test
    void testAddAndRetrieveAppointment() {
        Patient patient = patientRepo.findById(1001).orElse(null);
        assertNotNull(patient);

        Appointment newAppointment = new Appointment(patient, new Date(), "Ophthalmology", "Dr. Brown", "USF Theta", LocalTime.now(), 300.0, false);
        appointmentService.addAppointment(newAppointment);

        List<Appointment> appointments = appointmentService.getAll();
        assertTrue(appointments.stream().anyMatch(a -> a.getMedicalSpeciality().equals("Ophthalmology") && a.getDoctorName().equals("Dr. Brown")));
    }

    @Test
    void testGetAppointmentsForPatient() {
        Patient patient = patientRepo.findById(1001).orElse(null);
        assertNotNull(patient);

        List<Appointment> appointments = appointmentService.getPatientAppointments(1001);

        assertNotNull(appointments);
        assertTrue(appointments.size() > 0);
        assertTrue(appointments.stream().allMatch(a -> a.getPatient().getNumUtente() == 1001));
    }
}
