package project.backend.unit.entity;

import org.junit.jupiter.api.Test;
import project.backend.entity.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    @Test
    void testGettersAndSetters() {
        Patient patient = new Patient();
        int numUtente = 123;
        String name = "John Doe";
        String email = "john.doe@example.com";

        patient.setNumUtente(numUtente);
        patient.setName(name);
        patient.setEmail(email);

        assertEquals(numUtente, patient.getNumUtente());
        assertEquals(name, patient.getName());
        assertEquals(email, patient.getEmail());
    }

    @Test
    void testConstructors() {
        int numUtente = 123;
        String name = "John Doe";
        String email = "john.doe@example.com";


        Patient patient = new Patient(numUtente, name, email);


        assertEquals(numUtente, patient.getNumUtente());
        assertEquals(name, patient.getName());
        assertEquals(email, patient.getEmail());
    }


}
