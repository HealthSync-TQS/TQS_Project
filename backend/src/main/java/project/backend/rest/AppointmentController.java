package project.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointments() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(appointment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/appointments/withoutPatient")
    public ResponseEntity<List<Appointment>> getAppointmentsWithoutPatient() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentsWithoutPatient());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/appointments/{appointmentId}/setPatient/{patientId}")
    public ResponseEntity<Appointment> setPatient(@PathVariable Long appointmentId, @PathVariable int patientId) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            Patient patient = patientService.getPatientById(patientId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(appointmentService.setPatient(appointment, patient.getNumUtente()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/appointments/{date}/")
    public ResponseEntity<List<LocalTime>> getAvailableTimes(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam String medicalSpeciality,
            @RequestParam String healthcareUnit) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(appointmentService.getAvailableTimes(date, medicalSpeciality, healthcareUnit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
