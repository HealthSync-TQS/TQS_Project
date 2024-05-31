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

    @Test
    void testGetNextCheckInTicket() {
        ticketController.newTicket();
        ResponseEntity<String> response = ticketController.getNextCheckInTicket("balcony1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("001 balcony=balcony1", response.getBody());

        ResponseEntity<String> emptyResponse = ticketController.getNextCheckInTicket("balcony2");
        assertEquals(HttpStatus.OK, emptyResponse.getStatusCode());
        assertEquals("empty", emptyResponse.getBody());

        // Test exception
        // when(ticketController.getNextCheckInTicket("balcony1")).thenThrow(new RuntimeException());
        // ResponseEntity<String> errorResponse = ticketController.getNextCheckInTicket("balcony1");
        // assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    }

    @Test
    void testGetNextTicket() {
        ticketController.newAppointmentTicket("specialty");
        ResponseEntity<String> response = ticketController.getNextTicket("clinic1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("S001 clinic=clinic1", response.getBody());

        ResponseEntity<String> emptyResponse = ticketController.getNextTicket("clinic2");
        assertEquals(HttpStatus.OK, emptyResponse.getStatusCode());
        assertEquals("empty", emptyResponse.getBody());
    }

    @Test
    void testGetCheckInNotCalled() {
        ticketController.newTicket();
        ResponseEntity<List<String>> response = ticketController.getCheckInNotCalled();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList("001"), response.getBody());
    }

    @Test
    void testGetAppointmentsNotCalled() {
        ticketController.newAppointmentTicket("specialty");
        ResponseEntity<List<String>> response = ticketController.getAppointmentsotCalled();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList("S001"), response.getBody());
    }

    @Test
    void testGetCalledQueue() {
        ticketController.newTicket();
        ticketController.getNextCheckInTicket("balcony1");
        ResponseEntity<Map<String, String>> response = ticketController.getCalledQueue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("001", "balcony1");
        assertEquals(expectedMap, response.getBody());
    }

}