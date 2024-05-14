package project.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(date, that.date) &&
                Objects.equals(doctorName, that.doctorName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(medicalSpecialty, that.medicalSpecialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, date, doctorName, price, medicalSpecialty);
    }



}
