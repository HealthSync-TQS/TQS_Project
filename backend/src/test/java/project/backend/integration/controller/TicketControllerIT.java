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
public class TicketControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNewTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/newTicket"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("001"));
    }

    @Test
    public void testNewAppointmentTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/newAppointmentTicket").param("specialty", "someSpecialty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("S001"));
    }

    @Test
    public void testClearCheckInQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clearCheckIn"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Queue cleared"));
    }

    @Test
    public void testClearAppointmentQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clearAppointments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Queue cleared"));
    }

    @Test
    public void testGetNextCheckInTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nextCheckInTicket").param("balcony", "someBalcony"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("empty"));
    }

    @Test
    public void testGetNextAppointmentTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nextAppointmentTicket").param("clinic", "someClinic"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("empty"));
    }

    @Test
    public void testGetCheckInNotCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/CheckInNotCalled"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAppointmentsotCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/AppointmentsNotCalled"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCalledQueue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/called"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}