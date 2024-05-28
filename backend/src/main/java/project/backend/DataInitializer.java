package project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    @Autowired
    public DataInitializer(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        patientService.deleteAll();
        appointmentService.deleteAll();

        String speciality1 = "Cardiology";
        String speciality2 = "Dermatology";
        String doctor1 = "Dr. Smith";
        String doctor2 = "Dr. Johnson";

        Patient patient1 = new Patient(123456789, "John", "John@example.com");
        Patient patient2 = new Patient(987654321, "Jane", "Jane@example.com");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);

        Appointment appointment1 = new Appointment(patient1, new Date(), speciality1, doctor1, "Centro de Saude Delta",  LocalTime.now(), 100.0, false);
        Appointment appointment2 = new Appointment(patient2, new Date(), speciality2, doctor2, "USF Gama",  LocalTime.now(), 150.0, true);
        Appointment appointment3 = new Appointment(new Date(124, Calendar.NOVEMBER, 12), speciality1, doctor1, "USF Gama", LocalTime.now(), 100.0);
        Appointment appointment4 = new Appointment(new Date(124, Calendar.NOVEMBER, 12), speciality2, doctor2, "Centro de Saude Delta", LocalTime.now(),150.0);
        Appointment appointment5 = new Appointment(new Date(124, Calendar.JUNE, 2), speciality1, doctor1, "USF Gama ", LocalTime.now(), 100.0);
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);
        appointmentService.addAppointment(appointment3);
        appointmentService.addAppointment(appointment4);
        appointmentService.setPatient(appointment5, patient1.getNumUtente());





    }
}