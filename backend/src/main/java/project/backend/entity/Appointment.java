package project.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "numUtente", nullable = true)
    private Patient patient;

    @Column(name = "date")
    private Date date;

    @Column(name="price")
    private double price;

    @Column(name = "medical_speciality")
    private String medicalSpeciality;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name="healthcare_unit")
    private String healthcareUnit;

    @Column(name = "time")
    private LocalTime time;

    public Appointment() {
    }

    public Appointment(Patient patient, Date date, String medicalSpeciality, String doctorName, String healthcareUnit, LocalTime time, double price) {
        this.patient = patient;
        this.date = date;
        this.price = price;
        this.medicalSpeciality = medicalSpeciality;
        this.doctorName = doctorName;
        this.healthcareUnit = healthcareUnit;
        this.time = time;
    }

    public Appointment(Date date, String medicalSpeciality, String doctorName, String healthcareUnit, LocalTime time, double price) {
        this.patient = null;
        this.date = date;
        this.price = price;
        this.medicalSpeciality = medicalSpeciality;
        this.doctorName = doctorName;
        this.healthcareUnit = healthcareUnit;
        this.time = time;
    }




}
