package project.backend.integration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.repository.PatientRepo;
import project.backend.service.impl.AppointmentServiceImpl;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AppointmentServiceImpl appointmentService;


    private Appointment appointment;
    private Patient patient;

    @BeforeEach
    void setUp() {
        // Clear repositories before each test
        appointmentRepo.deleteAll();
        patientRepo.deleteAll();

        // Create and save a patient and an appointment
        patient = new Patient(123456789, "John Doe", "john@example.com");
        patientRepo.save(patient);

        appointment = new Appointment(new Date(124, 10, 12), "Cardiology", "Dr. Smith", "USF Gama", LocalTime.now(), 100.0);

        appointmentRepo.save(appointment);
    }



    @Test
    void testGetAppointments() throws Exception {

        List<Appointment> appointments = appointmentService.getAll();

        mockMvc.perform(get("/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(appointments.size())))
                .andExpect(jsonPath("$[0].id", is(appointments.get(0).getId().intValue())));

    }


    @Test
    void givenValidAppointment_whenCreateAppointment_thenStatus201() throws Exception {
        appointmentService.setPatient(appointment, patient.getNumUtente());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String appointmentJson = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(MockMvcRequestBuilders.post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patient.numUtente", is(patient.getNumUtente())))
                .andExpect(jsonPath("$.medicalSpecialty", is(appointment.getMedicalSpecialty())))
                .andExpect(jsonPath("$.doctorName", is(appointment.getDoctorName())))
                .andExpect(jsonPath("$.price", is(appointment.getPrice())));
    }

    @Test
    void givenInvalidAppointment_whenCreateAppointment_thenStatus400() throws Exception {

        String invalidAppointmentJson = "{\"patient\": {\"id\": 123, \"name\": \"John Doe\", \"email\": \"john@example.com\"}, \"medicalSpecialty\": \"\", \"doctor\": \"\", \"fee\": 100.0}";


        mockMvc.perform(MockMvcRequestBuilders.post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidAppointmentJson))
                .andExpect(status().isBadRequest());


    }

    @Test
    void testGetAppointmentsWithoutPatient() throws Exception {

        mockMvc.perform(get("/appointments/withoutPatient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(appointment.getId().intValue())))
                .andExpect(jsonPath("$[0].patient").doesNotExist());

    }

    @Test
    void givenValidIds_whenSetPatient_thenStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/appointments/{appointmentId}/setPatient/{patientId}", appointment.getId(), patient.getNumUtente())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patient.numUtente").value(patient.getNumUtente()))
                .andExpect(jsonPath("$.patient.name").value(patient.getName()))
                .andExpect(jsonPath("$.medicalSpecialty").value(appointment.getMedicalSpecialty()))
                .andExpect(jsonPath("$.doctorName").value(appointment.getDoctorName()))
                .andExpect(jsonPath("$.price").value(appointment.getPrice()));



    }

    @Test
    void givenInvalidPatientId_whenSetPatient_thenStatus400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/appointments/{appointmentId}/setPatient/{patientId}", appointment.getId(), 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetPayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/appointment/setPaymentDone")
                        .param("appointmentId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testSetCheckIn() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/appointment/setCheckInDone")
                        .param("appointmentId", "1"))
                .andExpect(status().isOk());
    }
}





