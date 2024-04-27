package project.backend.unit;

import org.junit.jupiter.api.Test;
import project.backend.entity.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Patient patient = new Patient();
        int numUtente = 123;
        String name = "John Doe";
        String email = "john.doe@example.com";

        // Act
        patient.setNumUtente(numUtente);
        patient.setName(name);
        patient.setEmail(email);

        // Assert
        assertEquals(numUtente, patient.getNumUtente());
        assertEquals(name, patient.getName());
        assertEquals(email, patient.getEmail());
    }

    @Test
    public void testConstructors() {
        // Arrange
        int numUtente = 123;
        String name = "John Doe";
        String email = "john.doe@example.com";

        // Act
        Patient patient = new Patient(numUtente, name, email);

        // Assert
        assertEquals(numUtente, patient.getNumUtente());
        assertEquals(name, patient.getName());
        assertEquals(email, patient.getEmail());
    }


}
