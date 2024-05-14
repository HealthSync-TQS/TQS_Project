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

    private List<String> nextCheckInTickets = new ArrayList<>();
    private List<String> nextAppointmentsTickets = new ArrayList<>();
    private Map<String, String> pastTickets = new LinkedHashMap<>();


    private final SimpMessagingTemplate template;

    public TicketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/newTicket")
    public ResponseEntity<String> newTicket() {
        String newTicket = String.format("%03d", nextCheckInTickets.size() + pastTickets.size() + 1);
        nextCheckInTickets.add(newTicket);
        notifyBoard();
        return ResponseEntity.ok(newTicket);
    }

    @PostMapping("/newAppointmentTicket")
    public ResponseEntity<String> newAppointmentTicket(@RequestParam("specialty") String specialty) {
        String newTicket = Character.toUpperCase(specialty.charAt(0)) + String.format("%03d", nextAppointmentsTickets.size() + 1);
        nextAppointmentsTickets.add(newTicket);
        notifyBoard();
        return ResponseEntity.ok(newTicket);
    }

    @GetMapping("/nextCheckInTicket")
    public ResponseEntity<String> getNextCheckInTicket(@RequestParam("balcony") String balcony) {
        if (!nextCheckInTickets.isEmpty()) {
            String nextTicket = nextCheckInTickets.get(0);
            nextCheckInTickets.remove(0);
            pastTickets.put(nextTicket, balcony);
            notifyBoard();
            return ResponseEntity.ok(nextTicket + " balcony=" + balcony);
        } else {
            return ResponseEntity.ok("empty");
        }
    }

    @GetMapping("/nextAppointmentTicket")
    public ResponseEntity<String> getNextTicket(@RequestParam("clinic") String clinic) {
        if (!nextAppointmentsTickets.isEmpty()) {
            String nextTicket = nextAppointmentsTickets.get(0);
            nextAppointmentsTickets.remove(0);
            pastTickets.put(nextTicket, clinic);
            notifyBoard();
            return ResponseEntity.ok(nextTicket + " clinic=" + clinic);
        } else {
            return ResponseEntity.ok("empty");
        }
    }

    @GetMapping("/CheckInNotCalled")
    public ResponseEntity<List<String>> getCheckInNotCalled() {
        return ResponseEntity.ok(nextCheckInTickets);
    }

    @GetMapping("/AppointmentsNotCalled")
    public ResponseEntity<List<String>> getAppointmentsotCalled() {
        return ResponseEntity.ok(nextAppointmentsTickets);
    }

    @GetMapping("/called")
    public ResponseEntity<Map<String,String>> getCalledQueue() {
        return ResponseEntity.ok(pastTickets);
    }

    private void notifyBoard() {
        Map<String, List<String>> queue = new HashMap<>();
        queue.put("checkin", nextCheckInTickets);
        queue.put("appointments", nextAppointmentsTickets);



        //template.convertAndSend("/topic", queue);
        template.convertAndSend("/topic", pastTickets);
    }

}
