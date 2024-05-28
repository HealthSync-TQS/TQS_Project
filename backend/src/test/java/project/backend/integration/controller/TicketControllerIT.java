package project.backend.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNewTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/newTicket"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("001"));
    }

    @Test
    void testNewAppointmentTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/newAppointmentTicket").param("specialty", "someSpecialty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("S001"));
    }

    @Test
    void testClearCheckInQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clearCheckIn"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Queue cleared"));
    }

    @Test
    void testClearAppointmentQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clearAppointments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Queue cleared"));
    }

    @Test
    void testGetNextCheckInTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nextCheckInTicket").param("balcony", "someBalcony"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("empty"));
    }

    @Test
    void testGetNextAppointmentTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nextAppointmentTicket").param("clinic", "someClinic"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("empty"));
    }

    @Test
    void testGetCheckInNotCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/CheckInNotCalled"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAppointmentsotCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/AppointmentsNotCalled"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetCalledQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/called"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}