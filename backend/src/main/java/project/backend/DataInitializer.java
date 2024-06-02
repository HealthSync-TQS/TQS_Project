package project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.time.LocalDate;
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

        Date date1 = Date.from(LocalDate.of(2024, 11, 12).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.of(2024, 6, 2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date3 = Date.from(LocalDate.of(2024, 7, 2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date4 = Date.from(LocalDate.of(2024, 8, 20).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date5 = Date.from(LocalDate.of(2024, 9, 25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date6 = Date.from(LocalDate.of(2024, 10, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date7 = Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date8 = Date.from(LocalDate.of(2024, 7, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date9 = Date.from(LocalDate.of(2024, 7, 3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date10 = Date.from(LocalDate.of(2024, 7, 4).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date11 = Date.from(LocalDate.of(2024, 7, 5).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date12 = Date.from(LocalDate.of(2024, 7, 15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date13 = Date.from(LocalDate.of(2024, 7, 16).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date14 = Date.from(LocalDate.of(2024, 7, 17).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date15 = Date.from(LocalDate.of(2024, 8, 3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date16 = Date.from(LocalDate.of(2024, 8, 4).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date17 = Date.from(LocalDate.of(2024, 8, 5).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date18 = Date.from(LocalDate.of(2024, 8, 6).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date19 = Date.from(LocalDate.of(2024, 8, 27).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date20 = Date.from(LocalDate.of(2024, 8, 28).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date21 = Date.from(LocalDate.of(2024, 8, 29).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date22 = Date.from(LocalDate.of(2024, 8, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date23 = Date.from(LocalDate.of(2024, 8, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date24 = Date.from(LocalDate.of(2024, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date25 = Date.from(LocalDate.of(2024, 9, 5).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date26 = Date.from(LocalDate.of(2024, 9, 10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date27 = Date.from(LocalDate.of(2024, 9, 15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date28 = Date.from(LocalDate.of(2024, 9, 20).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date29 = Date.from(LocalDate.of(2024, 9, 25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date30 = Date.from(LocalDate.of(2024, 9, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date31 = Date.from(LocalDate.of(2024, 10, 5).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date32 = Date.from(LocalDate.of(2024, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date33 = Date.from(LocalDate.of(2024, 10, 15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date34 = Date.from(LocalDate.of(2024, 10, 20).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date35 = Date.from(LocalDate.of(2024, 10, 25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date36 = Date.from(LocalDate.of(2024, 10, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Appointment appointment3 = new Appointment(date1, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment4 = new Appointment(date1, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment5 = new Appointment(date2, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment14 = new Appointment(date3, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment15 = new Appointment(date4, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment16 = new Appointment(date5, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment17 = new Appointment(date6, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment18 = new Appointment(date7, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment19 = new Appointment(date8, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment20 = new Appointment(date3, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment21 = new Appointment(date9, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment22 = new Appointment(date10, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment23 = new Appointment(date11, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment24 = new Appointment(date12, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment25 = new Appointment(date13, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment26 = new Appointment(date14, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment27 = new Appointment(date15, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment28 = new Appointment(date16, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment29 = new Appointment(date17, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment30 = new Appointment(date18, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment31 = new Appointment(date19, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment32 = new Appointment(date20, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment33 = new Appointment(date21, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment34 = new Appointment(date22, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment35 = new Appointment(date23, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment36 = new Appointment(date24, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment37 = new Appointment(date25, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment38 = new Appointment(date26, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment39 = new Appointment(date27, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment40 = new Appointment(date28, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment41 = new Appointment(date29, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment42 = new Appointment(date30, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment43 = new Appointment(date31, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment44 = new Appointment(date32, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment45 = new Appointment(date33, speciality1, doctor1, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment46 = new Appointment(date34, speciality2, doctor2, healthCareUnit1, LocalTime.now(), 150.0, false);
        Appointment appointment47 = new Appointment(date35, speciality3, doctor3, healthCareUnit2, LocalTime.now(), 100.0, false);
        Appointment appointment48 = new Appointment(date36, speciality4, doctor4, healthCareUnit1, LocalTime.now(), 150.0, false);
        
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
                String healthcareUnit = "Centro de Saúde Delta";

                Patient patient100 = new Patient(1001, "John Doe", "john.doe@example.com");

                patientService.addPatient(patient100);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(2024, Calendar.JUNE, 6, 22, 20);
                Date appointmentDate = calendar2.getTime();

                // Configura a consulta
                Appointment appointment200 = new Appointment(appointmentDate, medicalSpeciality, "Dr. Taylor",
                                healthcareUnit, LocalTime.of(22, 20), 220.0, false);

                appointmentService.addAppointment(appointment1 );
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