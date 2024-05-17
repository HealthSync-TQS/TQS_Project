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

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();

        Patient patient1 = new Patient(1001, "John Doe", "john.doe@example.com");
        Patient patient2 = new Patient(1002, "Jane Doe", "jane.doe@example.com");
        Patient patient3 = new Patient(1003, "Alice Johnson", "alice.johnson@example.com");
        Patient patient4 = new Patient(1004, "Bob Brown", "bob.brown@example.com");
        Patient patient5 = new Patient(1005, "Charlie Davis", "charlie.davis@example.com");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient3);
        patientService.addPatient(patient4);
        patientService.addPatient(patient5);

        Appointment appointment1 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(22, 20), 100.0);
        Appointment appointment6 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(23, 20), 100.0);
        Appointment appointment7 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(20, 20), 100.0);
        Appointment appointment8 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(19, 20), 100.0);
        Appointment appointment9 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(18, 20), 100.0);
        Appointment appointment10 = new Appointment(date, "Cardiology", "Dr. Smith", "Centro de Saúde Delta",
                LocalTime.of(16, 20), 100.0);
        Appointment appointment2 = new Appointment(patient2, new Date(), "Dermatology", "Dr. Jones", "USF Alfa",
                LocalTime.now(), 200.0);
        Appointment appointment3 = new Appointment(patient3, new Date(), "Pediatry", "Dr. White", "USF Beta",
                LocalTime.now(), 150.0);
        Appointment appointment4 = new Appointment(patient4, new Date(), "Ginecology", "Dr. Black", "USF Gama",
                LocalTime.now(), 180.0);
        Appointment appointment5 = new Appointment(patient5, new Date(), "Cardiology", "Dr. Taylor",
                "Centro de Saúde Delta", LocalTime.now(), 220.0);
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);
        appointmentService.addAppointment(appointment3);
        appointmentService.addAppointment(appointment4);
        appointmentService.addAppointment(appointment5);
        appointmentService.addAppointment(appointment6);
        appointmentService.addAppointment(appointment7);
        appointmentService.addAppointment(appointment8);
        appointmentService.addAppointment(appointment9);
        appointmentService.addAppointment(appointment10);

    }
}