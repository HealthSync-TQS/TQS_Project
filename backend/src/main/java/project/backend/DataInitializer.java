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
        String speciality3 = "General Practice";
        String speciality4 = "Gynecology";

        String doctor1 = "Dr. Smith";
        String doctor2 = "Dr. Johnson";
        String doctor3 = "Dr. Brown";
        String doctor4 = "Dr. White";
                patientService.deleteAll();
                appointmentService.deleteAll();


        String healthCareUnit1 = "Centro de Saude Delta";
        String healthCareUnit2 = "USF Gama ";

        Patient patient1 = new Patient(123456789, "John", "John@example.com");
        Patient patient2 = new Patient(987654321, "Jane", "Jane@example.com");
        Patient patient3 = new Patient(245745152, "Mare", "Mare@example.com");
        Patient patient4 = new Patient(123498765, "Bob", "Bob@example.com");
        Patient patient5 = new Patient(987612345, "Alice", "Alice@example.com");

        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient3);
        patientService.addPatient(patient4);
        patientService.addPatient(patient5);

        // Create appointments with patients
        Appointment appointment1 = new Appointment(patient1, new Date(), speciality1, doctor1, healthCareUnit1,  LocalTime.now(), 100.0, false);
        Appointment appointment2 = new Appointment(patient2, new Date(), speciality2, doctor2, healthCareUnit2,  LocalTime.now(), 150.0, true);
        Appointment appointment6 = new Appointment(patient3, new Date(), speciality3, doctor3, healthCareUnit1,  LocalTime.now(), 100.0, false);
        Appointment appointment7 = new Appointment(patient4, new Date(), speciality4, doctor4, healthCareUnit2,  LocalTime.now(), 150.0, true);
        Appointment appointment8 = new Appointment(patient5, new Date(), speciality1, doctor1, healthCareUnit1,  LocalTime.now(), 100.0, false);
        Appointment appointment9 = new Appointment(patient1, new Date(), speciality2, doctor2, healthCareUnit2,  LocalTime.now(), 150.0, true);
        Appointment appointment10 = new Appointment(patient2, new Date(), speciality3, doctor3, healthCareUnit1,  LocalTime.now(), 100.0, false);
        Appointment appointment11 = new Appointment(patient3, new Date(), speciality4, doctor4, healthCareUnit2,  LocalTime.now(), 150.0, true);
        Appointment appointment12 = new Appointment(patient4, new Date(), speciality1, doctor1, healthCareUnit1,  LocalTime.now(), 100.0, false);
        Appointment appointment13 = new Appointment(patient5, new Date(), speciality2, doctor2, healthCareUnit2,  LocalTime.now(), 150.0, true);

        // Create appointments without patients
        Appointment appointment3 = new Appointment(new Date(124, Calendar.NOVEMBER, 12), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100., false);
        Appointment appointment4 = new Appointment(new Date(124, Calendar.NOVEMBER, 12), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment5 = new Appointment(new Date(124, Calendar.JUNE, 2), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment14 = new Appointment(new Date(124, Calendar.JULY, 2), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment15 = new Appointment(new Date(124, Calendar.AUGUST, 20), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment16 = new Appointment(new Date(124, Calendar.SEPTEMBER, 25), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment17 = new Appointment(new Date(124, Calendar.OCTOBER, 30), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment18 = new Appointment(new Date(124, Calendar.DECEMBER, 31), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment19 = new Appointment(new Date(124, Calendar.JULY, 1), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment20 = new Appointment(new Date(124, Calendar.JULY, 2), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment21 = new Appointment(new Date(124, Calendar.JULY, 3), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment22 = new Appointment(new Date(124, Calendar.JULY, 4), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment23 = new Appointment(new Date(124, Calendar.JULY, 5), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment24 = new Appointment(new Date(124, Calendar.JULY, 15), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment25 = new Appointment(new Date(124, Calendar.JULY, 16), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment26 = new Appointment(new Date(124, Calendar.JULY, 17), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment27 = new Appointment(new Date(124, Calendar.AUGUST, 3), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment28 = new Appointment(new Date(124, Calendar.AUGUST, 4), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment29 = new Appointment(new Date(124, Calendar.AUGUST, 5), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment30 = new Appointment(new Date(124, Calendar.AUGUST, 6), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment31 = new Appointment(new Date(124, Calendar.AUGUST, 27), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment32 = new Appointment(new Date(124, Calendar.AUGUST, 28), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment33 = new Appointment(new Date(124, Calendar.AUGUST, 29), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment34 = new Appointment(new Date(124, Calendar.AUGUST, 30), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment35 = new Appointment(new Date(124, Calendar.AUGUST, 31), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment36 = new Appointment(new Date(124, Calendar.SEPTEMBER, 1), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment37 = new Appointment(new Date(124, Calendar.SEPTEMBER, 5), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment38 = new Appointment(new Date(124, Calendar.SEPTEMBER, 10), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment39 = new Appointment(new Date(124, Calendar.SEPTEMBER, 15), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment40 = new Appointment(new Date(124, Calendar.SEPTEMBER, 20), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment41 = new Appointment(new Date(124, Calendar.SEPTEMBER, 25), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment42 = new Appointment(new Date(124, Calendar.SEPTEMBER, 30), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment43 = new Appointment(new Date(124, Calendar.OCTOBER, 5), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment44 = new Appointment(new Date(124, Calendar.OCTOBER, 10), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment45 = new Appointment(new Date(124, Calendar.OCTOBER, 15), speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment46 = new Appointment(new Date(124, Calendar.OCTOBER, 20), speciality2, doctor2, healthCareUnit1, LocalTime.now(),150.0, false);
        Appointment appointment47 = new Appointment(new Date(124, Calendar.OCTOBER, 25), speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment48 = new Appointment(new Date(124, Calendar.OCTOBER, 30), speciality4, doctor4, healthCareUnit1, LocalTime.now(),150.0, false);


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
        appointmentService.addAppointment(appointment11);
        appointmentService.addAppointment(appointment12);
        appointmentService.addAppointment(appointment13);
        appointmentService.addAppointment(appointment14);
        appointmentService.addAppointment(appointment15);
        appointmentService.addAppointment(appointment16);
        appointmentService.addAppointment(appointment17);
        appointmentService.addAppointment(appointment18);
        appointmentService.addAppointment(appointment19);
        appointmentService.addAppointment(appointment20);
        appointmentService.addAppointment(appointment21);
        appointmentService.addAppointment(appointment22);
        appointmentService.addAppointment(appointment23);
        appointmentService.addAppointment(appointment24);
        appointmentService.addAppointment(appointment25);
        appointmentService.addAppointment(appointment26);
        appointmentService.addAppointment(appointment27);
        appointmentService.addAppointment(appointment28);
        appointmentService.addAppointment(appointment29);
        appointmentService.addAppointment(appointment30);
        appointmentService.addAppointment(appointment31);
        appointmentService.addAppointment(appointment32);
        appointmentService.addAppointment(appointment33);
        appointmentService.addAppointment(appointment34);
        appointmentService.addAppointment(appointment35);
        appointmentService.addAppointment(appointment36);
        appointmentService.addAppointment(appointment37);
        appointmentService.addAppointment(appointment38);
        appointmentService.addAppointment(appointment39);
        appointmentService.addAppointment(appointment40);
        appointmentService.addAppointment(appointment41);
        appointmentService.addAppointment(appointment42);
        appointmentService.addAppointment(appointment43);
        appointmentService.addAppointment(appointment44);
        appointmentService.addAppointment(appointment45);
        appointmentService.addAppointment(appointment46);
        appointmentService.addAppointment(appointment47);
        appointmentService.addAppointment(appointment48);

        appointmentService.setPatient(appointment5, patient1.getNumUtente());



                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                date = calendar.getTime();

                String medicalSpeciality = "Cardiology";
                String doctor = "Dr. Smith";
                String healthcareUnit = "Centro de Saúde Delta";

                Patient patient100 = new Patient(1001, "John Doe", "john.doe@example.com");

                patientService.addPatient(patient100);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(2024, Calendar.JUNE, 6, 22, 20);
                Date appointmentDate = calendar2.getTime();

                // Configura a consulta
                Appointment appointment200 = new Appointment(appointmentDate, medicalSpeciality, "Dr. Taylor",
                                healthcareUnit, LocalTime.of(22, 20), 220.0, false);

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
                appointmentService.addAppointment(appointment20);
                appointmentService.addAppointment(appointment200);


        }
}