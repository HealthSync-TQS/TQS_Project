package project.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;


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

    @Temporal(TemporalType.DATE) 
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

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "checked_in")
    private boolean checkedIn;

    public Appointment() {
    }

    public Appointment(Patient patient, Date date, String medicalSpeciality, String doctorName, String healthcareUnit, LocalTime time, double price, boolean paid) {
        this.patient = patient;
        this.date = date;
        this.price = price;
        this.medicalSpeciality = medicalSpeciality;
        this.doctorName = doctorName;
        this.healthcareUnit = healthcareUnit;
        this.time = time;
        this.paid = paid;
        this.checkedIn = false;
    }

    public Appointment(Date date, String medicalSpeciality, String doctorName, String healthcareUnit, LocalTime time, double price, boolean paid) {
        this.patient = null;
        this.date = date;
        this.price = price;
        this.medicalSpeciality = medicalSpeciality;
        this.doctorName = doctorName;
        this.healthcareUnit = healthcareUnit;
        this.time = time;
        this.paid = false;
        this.checkedIn = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(doctorName, that.doctorName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(medicalSpeciality, that.medicalSpeciality)
                && Objects.equals(healthcareUnit, that.healthcareUnit)
                && Objects.equals(time, that.time);


    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctorName, price, medicalSpeciality, healthcareUnit, time);
    }



}
