package project.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "medical_specialty")
    private String medicalSpecialty;

    @Column(name = "doctor_name")
    private String doctorName;

    public Appointment() {
    }

    public Appointment(Patient patient, Date date, String medicalSpecialty, String doctorName, double price) {
        this.patient = patient;
        this.date = date;
        this.price = price;
        this.medicalSpecialty = medicalSpecialty;
        this.doctorName = doctorName;
    }

    public Appointment(Date date, String medicalSpecialty, String doctorName, double price) {
        this.patient = null;
        this.date = date;
        this.price = price;
        this.medicalSpecialty = medicalSpecialty;
        this.doctorName = doctorName;
    }

    // Getters and Setters
    public Patient getPatient() {
        return patient;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMedicalSpecialty(String medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
