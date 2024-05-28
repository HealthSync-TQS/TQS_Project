package project.backend.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TicketController {

    private final List<String> nextCheckInTickets = new ArrayList<>();
    List<String> nextAppointmentsTickets = new ArrayList<>();
    private final Map<String, String> pastTickets = new LinkedHashMap<>();

    private String error = "Error creating ticket";


    private final SimpMessagingTemplate template;

    public TicketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/newTicket")
    public ResponseEntity<String> newTicket() {
        try {
            String newTicket = String.format("%03d", nextCheckInTickets.size() + pastTickets.size() + 1);
            nextCheckInTickets.add(newTicket);
            notifyBoard();
            return ResponseEntity.ok(newTicket);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/newAppointmentTicket")
    public ResponseEntity<String> newAppointmentTicket(@RequestParam("specialty") String specialty) {
        try {
            String newTicket = Character.toUpperCase(specialty.charAt(0)) + String.format("%03d", nextAppointmentsTickets.size() + 1);
            nextAppointmentsTickets.add(newTicket);
            notifyBoard();
            return ResponseEntity.ok(newTicket);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/clearCheckIn")
    public ResponseEntity<String> clearCheckInQueue() {
        try {
            nextCheckInTickets.clear();
            notifyBoard();
            return ResponseEntity.ok("Queue cleared");

        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/clearAppointments")
    public ResponseEntity<String> clearAppointmentQueue() {
        try {
            nextAppointmentsTickets.clear();
            notifyBoard();
            return ResponseEntity.ok("Queue cleared");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/nextCheckInTicket")
    public ResponseEntity<String> getNextCheckInTicket(@RequestParam("balcony") String balcony) {
        try {
            if (!nextCheckInTickets.isEmpty()) {
                String nextTicket = nextCheckInTickets.get(0);
                nextCheckInTickets.remove(0);
                pastTickets.put(nextTicket, balcony);
                notifyBoard();
                return ResponseEntity.ok(nextTicket + " balcony=" + balcony);
            }
            return ResponseEntity.ok("empty");

        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting ticket");
        }
    }

    @GetMapping("/nextAppointmentTicket")
    public ResponseEntity<String> getNextTicket(@RequestParam("clinic") String clinic) {
        try {
            if (!nextAppointmentsTickets.isEmpty()) {
                String nextTicket = nextAppointmentsTickets.get(0);
                nextAppointmentsTickets.remove(0);
                pastTickets.put(nextTicket, clinic);
                notifyBoard();
                return ResponseEntity.ok(nextTicket + " clinic=" + clinic);
            }

            return ResponseEntity.ok("empty");

        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting ticket");
        }
    }

    @GetMapping("/CheckInNotCalled")
    public ResponseEntity<List<String>> getCheckInNotCalled() {
        try {
            return ResponseEntity.ok(nextCheckInTickets);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/AppointmentsNotCalled")
    public ResponseEntity<List<String>> getAppointmentsotCalled() {
        try {
            return ResponseEntity.ok(nextAppointmentsTickets);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/called")
    public ResponseEntity<Map<String,String>> getCalledQueue() {
        try {
            return ResponseEntity.ok(pastTickets);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    private void notifyBoard() {
        Map<String, List<String>> queue = new HashMap<>();
        queue.put("checkin", nextCheckInTickets);
        queue.put("appointments", nextAppointmentsTickets);



        template.convertAndSend("/topic", pastTickets);
    }

}
