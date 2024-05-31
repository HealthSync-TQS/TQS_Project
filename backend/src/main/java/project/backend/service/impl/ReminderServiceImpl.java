package project.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.backend.entity.Appointment;
import project.backend.repository.AppointmentRepo;
import project.backend.service.EmailService;
import project.backend.service.ReminderService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService{

    private final AppointmentRepo appointmentRepo;
    private final EmailService emailService;

    @Autowired
    public ReminderServiceImpl(AppointmentRepo appointmentRepo, EmailService emailService) {
        this.appointmentRepo = appointmentRepo;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * *")
    @Override
    public void sendReminder() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date tomorrowDate = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Appointment> appointments = appointmentRepo.findByDate(tomorrowDate);

        for (Appointment appointment : appointments) {
            if (appointment.getPatient() != null) {
                String email = appointment.getPatient().getEmail();
                String subject = "Lembrete de Consulta";
                String text = "Olá " + appointment.getPatient().getName() + ",\n\n"
                        + "Este é um lembrete para sua consulta marcada para amanhã, " + tomorrow + ".\n\n"
                        + "Atenciosamente,\n"
                        + "Sua Clínica";
                emailService.sendEmail(email, subject, text);
            }
        }
    }
}
