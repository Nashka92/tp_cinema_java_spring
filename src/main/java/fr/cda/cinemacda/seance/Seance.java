package fr.cda.cinemacda.seance;

import fr.cda.cinemacda.film.Film;
import fr.cda.cinemacda.salle.Salle;
import fr.cda.cinemacda.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue
    private int id;

    // Many seances pour one film
    // il dispose d'un film
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    // Many seances pour one salle
    // il dispose d'une salle
    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = false)
    private Salle salle;

    //la date
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int placesDisponibles;

    // prix
    @Column(nullable = false)
    private float prix;

    // Une séance à plusieurs tickets
    @OneToMany(mappedBy = "seance")
    private List<Ticket> tickets;

}
