package project.backend.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import project.backend.entity.Appointment;
import project.backend.entity.Patient;
import project.backend.repository.AppointmentRepo;
import project.backend.service.EmailService;
import project.backend.service.impl.ReminderServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReminderServiceImplTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ReminderServiceImpl reminderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(reminderService, "appointmentRepo", appointmentRepo);
        ReflectionTestUtils.setField(reminderService, "emailService", emailService);
    }

    @Test
    void sendReminder_ShouldSendEmailToPatientsWithAppointmentsTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date tomorrowDate = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Patient patient = new Patient();
        patient.setEmail("lyachan3627@gmail.com");
        patient.setName("Lia");

        Appointment appointment = new Appointment();
        appointment.setDate(tomorrowDate);
        appointment.setPatient(patient);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        when(appointmentRepo.findByDate(tomorrowDate)).thenReturn(appointments);

        reminderService.sendReminder();

        ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailService).sendEmail(toCaptor.capture(), subjectCaptor.capture(), bodyCaptor.capture());

        assertEquals("lyachan3627@gmail.com", toCaptor.getValue());
        assertEquals("Lembrete de Consulta", subjectCaptor.getValue());
        assertEquals("Olá Lia,\n\nEste é um lembrete para sua consulta marcada para amanhã, " + tomorrow + ".\n\nAtenciosamente,\nSua Clínica", bodyCaptor.getValue());
    }
}
