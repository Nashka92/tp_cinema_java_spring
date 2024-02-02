package fr.cda.cinemacda.salle;

import fr.cda.cinemacda.realisateur.Realisateur;
import fr.cda.cinemacda.seance.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Salle {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String nomSalle;

    @Column(nullable = false)
    private int capacite;

    // Une salle a plusieurs seances
    @OneToMany(mappedBy = "salle")
    private List<Seance> seances;

}