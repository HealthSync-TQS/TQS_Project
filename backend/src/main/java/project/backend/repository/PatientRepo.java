package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import project.backend.entity.Patient;


@EnableJpaRepositories
@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {



}
