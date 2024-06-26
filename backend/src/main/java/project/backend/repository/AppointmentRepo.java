package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import project.backend.entity.Appointment;

import java.util.Date;
import java.util.List;


@EnableJpaRepositories
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientIsNull();
    List<Appointment> findByDoctorName(String doctorName);
    List<Appointment> findByPatientIsNullAndDateAndMedicalSpecialityAndHealthcareUnit(Date date, String medicalSpeciality, String healthcareUnit);
    List<Appointment> findByPatientNumUtente(int numUtente);
    List<Appointment> findByDate(Date date);

}
