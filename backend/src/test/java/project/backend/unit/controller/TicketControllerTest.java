package project.backend.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.backend.rest.TicketController;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Mock
    private SimpMessagingTemplate template;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doNothing().when(template).convertAndSend(Optional.ofNullable(any()), any());
    }

    @Test
    void testNewTicket() {
        ResponseEntity<String> response = ticketController.newTicket();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("001", response.getBody());

        when(ticketController.newTicket()).thenThrow(new RuntimeException());
        ResponseEntity<String> errorResponse = ticketController.newTicket();
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());

    }

    @Test
    void testNewAppointmentTicket() {
        ResponseEntity<String> response = ticketController.newAppointmentTicket("specialty");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("S001", response.getBody());

        when(ticketController.newAppointmentTicket("specialty")).thenThrow(new RuntimeException());
        ResponseEntity<String> errorResponse = ticketController.newAppointmentTicket("specialty");
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());

    }

    @Test
    void testClearCheckInQueue() {
        ResponseEntity<String> response = ticketController.clearCheckInQueue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Queue cleared", response.getBody());

        when(ticketController.clearCheckInQueue()).thenThrow(new RuntimeException());
        ResponseEntity<String> errorResponse = ticketController.clearCheckInQueue();
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }

    @Test
    void testClearAppointmentsQueue() {
        ResponseEntity<String> response = ticketController.clearAppointmentQueue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Queue cleared", response.getBody());


        when(ticketController.clearAppointmentQueue()).thenThrow(new RuntimeException());
        ResponseEntity<String> errorResponse = ticketController.clearAppointmentQueue();
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }


}