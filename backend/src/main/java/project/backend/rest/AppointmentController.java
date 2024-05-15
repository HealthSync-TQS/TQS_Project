package project.backend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.service.AppointmentService;
import project.backend.service.PatientService;

import java.util.Collections;
import java.util.List;

import java.util.logging.Logger;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private static final Logger logger = Logger.getLogger(AppointmentController.class.getName());

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

            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.setPatient(appointment, patient.getNumUtente()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/appointment")
    public ResponseEntity<List<Appointment>> getAppointment(
            @RequestParam(value = "id", required = false) Long appointmentId,
            @RequestParam(value = "numUtente", required = false) Long numUtente) {
        try {

            if(appointmentId != null)
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(appointmentService.getAppointmentById(appointmentId)));

            if(numUtente != null)
                return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentByPatient(numUtente));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/appointment/setPaymentDone")
    public ResponseEntity<Appointment> setPayment(@RequestParam Long appointmentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.updatePayment(appointmentId, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/appointment/setCheckInDone")
    public ResponseEntity<Appointment> setCheckIn(@RequestParam Long appointmentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.updateCheckIn(appointmentId, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
