package project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

    @Autowired
    public DataInitializer(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        patientService.deleteAll();
        appointmentService.deleteAll();

        Patient patient1 = new Patient(123456789, "John", "John@example.com");
        Patient patient2 = new Patient(987654321, "Jane", "Jane@example.com");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);

        Appointment appointment1 = new Appointment(patient1, new Date(), "Cardiology", "Dr. Smith", 100.0);
        Appointment appointment2 = new Appointment(patient2, new Date(), "Dermatology", "Dr. Johnson", 150.0);
        Appointment appointment3 = new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", 100.0);
        Appointment appointment4 = new Appointment(new Date(124, 10, 12), "Dermatology", "Dr. Johnson", 150.0);
        Appointment appointment5 = new Appointment(new Date(124, 5, 2), "Cardiology", "Dr. Smith", 100.0);
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);
        appointmentService.addAppointment(appointment3);
        appointmentService.addAppointment(appointment4);
        appointmentService.setPatient(appointment5, patient1.getNumUtente());

        logger.info("Appointment: " + appointment1.getId());
    }
}