package be.vdab.testcontainers.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "personen")
public class Persoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }
}
