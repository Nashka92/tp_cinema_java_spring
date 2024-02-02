package fr.cda.cinemacda.ticket;

import fr.cda.cinemacda.seance.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    // Ticket est lié a plusieurs seance
    @ManyToOne
    @JoinColumn(name = "seance_id", nullable = false)
    private Seance seance;

    @Column(nullable = false)
    private String nomClient;

    @Column(nullable = false)
    private int nombrePlaces;
}

