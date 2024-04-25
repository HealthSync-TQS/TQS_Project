package project.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter

public class Patient {

    @Id
    @Column(name = "numUtente", length = 10)
    private int numUtente;

    @Column(name = "patient_name", length = 45)
    private String name;

    @Column(name = "patient_email", length = 45, unique = true)
    private String email;

    public Patient() {
    }

    public Patient(int numUtente, String name, String email) {
        this.numUtente = numUtente;
        this.name = name;
        this.email = email;
    }


}
